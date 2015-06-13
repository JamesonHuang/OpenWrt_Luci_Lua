#include "auth.h"
#include "connect.h"
#include "config.h"
#include <openssl/md5.h>
#include "str.h"
#include "string.h"
#include "connect.h"
#include <unistd.h>

char* g_token = "";

int NebulaAuth1()
{
    Nebula__AuthRequest request;
    nebula__auth_request__init(&request);
    request.uid = g_device_tag;
    request.username = g_device_tag;
    return NebulaSend(MSG_SET_REQUEST(NEBULA_MSG_AUTH), (ProtobufCMessage*)&request);
}

int NebulaAuth2(char* token, char* nonce)
{
    Nebula__AuthRequest request;
    nebula__auth_request__init(&request);
    request.uid = g_device_tag;
    request.username = g_device_tag;
    request.token = token;
    request.nonce = nonce;
    request.has_algorithm = 1;
    request.algorithm = NEBULA__AUTH_REQUEST__ALGORITHM__MD5_DAA;
    return NebulaSend(MSG_SET_REQUEST(NEBULA_MSG_AUTH), (ProtobufCMessage*)&request);
}

void NebulaOnAuth(NebulaHeader *head, const char* data, int len)
{
    Nebula__AuthResponse *response = nebula__auth_response__unpack(NULL, len, (const uint8_t*)data);
    if (!response) {
        log_error("parse auth response error. data_len=%d", len);
        return ;
    }

    if (response->status == 200) {
        // auth success.
        next_status();
    } else {
        if (Nebulaget_status() == eStatus_Auth2) {
            // auth failed, retry redirect.
            log_error("auth failed, rsp.status() == %d. sleep 1 and retry redirect.", response->status);
            nebula__auth_response__free_unpacked(response, NULL);
            sleep(1);
            PopFront(g_server_list);
            jump_status(eStatus_Connect);
            return ;
        }

        static const char hex[] = "0123456789abcdef";
        int i;
        unsigned char ha1_b[16], ha1[32], token_b[16], token[33];
        struct str buf;
        string_init(&buf);
        string_append_fast(&buf, g_salt, strlen(g_salt));
        string_append_fast(&buf, ":", 1);
        string_append_fast(&buf, g_device_tag, strlen(g_device_tag));
        string_append_fast(&buf, ":", 1);
        string_append_fast(&buf, response->nonce, strlen(response->nonce));
        MD5((unsigned char*)buf.ptr, buf.len, ha1_b);
        for (i = 0; i < MD5_DIGEST_LENGTH; i++)
        {
            ha1[i * 2] = hex[ha1_b[i] >> 4];
            ha1[i * 2 + 1] = hex[ha1_b[i] & 0xf];
        }

        string_reset(&buf);
        char *rule = g_rule;
        while (*rule) {
            if (!string_empty(&buf)) {
                string_append_fast(&buf, ":", 1);
            }

            if (*rule == 'h') {
                string_append_fast(&buf, (char*)ha1, 32);
            } else if (*rule == 's') {
                string_append_fast(&buf, g_salt, strlen(g_salt));
            } else {
                log_error("unkown rule: %s", g_rule);
            }

            rule++;
        }
        MD5((unsigned char*)buf.ptr, buf.len, token_b);

        for (i = 0; i < MD5_DIGEST_LENGTH; i++)
        {
            token[i * 2] = hex[token_b[i] >> 4];
            token[i * 2 + 1] = hex[token_b[i] & 0xf];
        }
        token[32] = '\0';

        //log_debug("auth. md5buf:%s, token:%s", buf.ptr, token);

        string_deinit(&buf);
        if (-1 == NebulaAuth2((char*)token, response->nonce)) {
            jump_status(eStatus_Redirect);
        } else {
            if (strlen(g_token)) free(g_token);
            g_token = strdup((char*)token);
            next_status();
        }
    }

    nebula__auth_response__free_unpacked(response, NULL);
}
