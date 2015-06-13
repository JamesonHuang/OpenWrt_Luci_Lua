#include "redirect.h"
#include <stdio.h>
#include <stdlib.h>
#include <arpa/inet.h>
#include <curl/curl.h>
#include <openssl/md5.h>
#include <json-c/json.h>
#include "config.h"
#include "str.h"

List *g_redirect_result = NULL;
time_t g_last_redirect = 0;
char* g_salt = NULL;
char* g_rule = NULL;

ssize_t NebulaRedirectOnRead(const void *ptr, size_t size, size_t nmemb, FILE *stream)
{
    (void)size, (void)stream;
    struct str *result = (struct str *)stream;
    string_append_fast(result, (const char*)ptr, nmemb);
    return nmemb;
}

int NebulaCloneRedirectResult()
{
    if (!g_redirect_result) return -1;
    if (g_redirect_result->size == 0) return -1;

    if (g_server_list)
        ClearList(g_server_list);
    else
        g_server_list = CreateList();

    if (!g_server_list) goto on_error;

    ListIterator it;
    for (it = ListBegin(g_redirect_result); it; it = ListNext(it)) {
        struct sockaddr_in *r_addr = (struct sockaddr_in *)(*it);
        struct sockaddr_in *addr = (struct sockaddr_in *)malloc(sizeof(struct sockaddr_in));
        if (!addr) {
            goto on_error;
        }
        addr->sin_port = r_addr->sin_port;
        addr->sin_addr.s_addr = r_addr->sin_addr.s_addr;
        if (-1 == PushBack(g_server_list, addr)) {
            free(addr);
            goto on_error;
        }
    }

    return 0;

on_error:
    if (g_server_list)
        ClearList(g_server_list);
    return -1;
}

int NebulaRedirect()
{
    time_t now = time(0);
    if (now - g_last_redirect < 300) {
        if (0 == NebulaCloneRedirectResult()) {
            log_info("not timeout, use last redirect result always.");
            return 0;
        }
    }

    g_last_redirect = now;

    static const char* key = "key=89d15f8b716d4b16fae9feaa09bc4fc5&";
    struct str result;
    string_init(&result);
    struct json_object* response = NULL;
    List *server_list = NULL;

    // post parameters.
    struct str params;
    string_init(&params);
    string_append_fast(&params, "uid=", 4);
    string_append_fast(&params, g_device_tag, strlen(g_device_tag));
    string_append_fast(&params, "&nonce=", 7);
    const char* nonce = Nebularand_string();
    string_append_fast(&params, nonce, strlen(nonce));
    string_append_fast(&params, "&ts=", 4);
    time_t t = time(0);
    char ts[32];
    snprintf(ts, sizeof(ts), "%ld", t);
    string_append_fast(&params, ts, strlen(ts));

    // sign
    unsigned char sign_str[MD5_DIGEST_LENGTH], sign[MD5_DIGEST_LENGTH * 2];
    char md5buf[512];
    snprintf(md5buf, sizeof(md5buf), "%s%s", key, params.ptr);
    MD5((const unsigned char*)md5buf, strlen(md5buf), sign_str);
    static const char hex[] = "0123456789abcdef";
    int i;
    for (i = 0; i < MD5_DIGEST_LENGTH; i++)
    {
        sign[i * 2] = hex[sign_str[i] >> 4];
        sign[i * 2 + 1] = hex[sign_str[i] & 0xf];
    }
    string_append_fast(&params, "&sign=", 6);
    string_append_fast(&params, (const char*)sign, sizeof(sign));

    CURL *curl = curl_easy_init();
    char url[128] = {};
    snprintf(url, sizeof(url), "%s/push/redirect", g_nebula_host);
    //const char* url = "https://p.meizu.com/push/redirect";
    curl_easy_setopt(curl, CURLOPT_URL, url);
    curl_easy_setopt(curl, CURLOPT_SSL_VERIFYPEER, 0L);
    curl_easy_setopt(curl, CURLOPT_SSL_VERIFYHOST, 0L);
    curl_easy_setopt(curl, CURLOPT_WRITEFUNCTION, &NebulaRedirectOnRead);
    curl_easy_setopt(curl, CURLOPT_WRITEDATA, &result);
    curl_easy_setopt(curl, CURLOPT_POSTFIELDS, params.ptr);
    curl_easy_setopt(curl, CURLOPT_POSTFIELDSIZE, params.len);
    curl_easy_setopt(curl, CURLOPT_TIMEOUT, 10); 
    curl_easy_setopt(curl, CURLOPT_NOSIGNAL, 1L);
    log_info("HTTPS POST. url=%s, params=%s", url, params.ptr);
    CURLcode res = curl_easy_perform(curl);
    if (res != CURLE_OK) {
        log_error("curl_easy_perform(redirect) error. code=%d, info=%s",
                (int)res, curl_easy_strerror(res));
        goto on_error;
    } else {
        log_info("curl_easy_perform success. response=%s", result.ptr);
    }

    // parse response json.
    response = json_tokener_parse(result.ptr);
    string_deinit(&result);
    if (!response) {
        log_error("json parse error.");
        goto on_error;
    }

    struct json_object* code_node = json_object_object_get(response, "code");
    if (!code_node) {
        log_error("json parse error, hasnot \"code\" node.");
        goto on_error;
    }

    int code = json_object_get_int(code_node);
    if (code != 200) {
        // redirect failed.
        log_error("json response code error. code=%d", code);
        goto on_error;
    }

    struct json_object *md5_daa = json_object_object_get(response, "md5_daa");
    if (!md5_daa) {
        log_error("json response has no md5_daa field.");
        goto on_error;
    }
    struct json_object *salt_node = json_object_object_get(md5_daa, "salt");
    if (!salt_node) {
        log_error("json response has no md5_daa.salt field.");
        goto on_error;
    }
    const char* salt = json_object_get_string(salt_node);
    if (!salt) {
        log_error("json response has no md5_daa.salt field.");
        goto on_error;
    }
    if (g_salt) free(g_salt);
    g_salt = strdup(salt);

    struct json_object *rule_node = json_object_object_get(md5_daa, "rule");
    if (!rule_node) {
        log_error("json response has no md5_daa.rule field.");
        goto on_error;
    }
    const char* rule = json_object_get_string(rule_node);
    if (!rule) {
        log_error("json response has no md5_daa.rule field.");
        goto on_error;
    }
    if (g_rule) free(g_rule);
    g_rule = strdup(rule);

    struct json_object* host_list_node = json_object_object_get(response, "hostlist");
    if (!host_list_node) {
        log_error("json parse error, hasnot \"hostlist\" node.");
        goto on_error;
    }

    struct array_list* host_list = json_object_get_array(host_list_node);
    if (!host_list) {
        log_error("json_object_get_array error.");
        goto on_error;
    }

    int host_count = array_list_length(host_list);
    if (!host_count) {
        log_error("host list is empty.");
        goto on_error;
    }

    server_list = CreateList();
    if (!server_list) {
        log_error("create server list error, maybe memory was deplete.");
        goto on_error;
    }

    for (i = 0; i < host_count; ++i)
    {
        struct json_object* elem = (struct json_object*)array_list_get_idx(host_list, i);
        if (!elem) {
            log_error("host list get idx error, i=%d.", i);
            goto on_error;
        }

        struct json_object* ip_node = json_object_object_get(elem, "ip");
        if (!ip_node) {
            log_error("host list get ip error, i=%d.", i);
            goto on_error;
        }
        const char* ip = json_object_get_string(ip_node);
        int ip_len = json_object_get_string_len(ip_node);
        if (!ip || !ip_len) {
            log_error("host list get ip error, i=%d.", i);
            goto on_error;
        }

        struct json_object* port_node = json_object_object_get(elem, "port");
        int port = json_object_get_int(port_node);
        if (!port) {
            log_error("host list get port error, i=%d.", i);
            goto on_error;
        }

        struct sockaddr_in *addr = (struct sockaddr_in *)malloc(sizeof(struct sockaddr_in));
        if (!addr) {
            log_error("malloc sockaddr_in failed.");
            goto on_error;
        }

        addr->sin_port = htons(port);
        addr->sin_addr.s_addr = inet_addr(ip);
        if (-1 == PushBack(server_list, addr)) {
            log_error("PushBack to server_list error, maybe memory was deplete.");
            goto on_error;
        }
    }

    if (g_redirect_result)
        DestroyList(g_redirect_result);

    g_redirect_result = server_list;
    server_list = NULL;
    return NebulaCloneRedirectResult();

on_error:
    string_deinit(&result);
    if (response)
        json_object_put(response);
    if (server_list)
        DestroyList(server_list);
    return -1;
}

