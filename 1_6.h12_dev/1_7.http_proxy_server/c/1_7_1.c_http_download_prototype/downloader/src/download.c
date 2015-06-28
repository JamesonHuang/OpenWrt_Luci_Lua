/*
    "download.c"
    Copyright (C) <2012>  <"Mark Deng" 2010.tpk@gmail.com>

    This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License as published by
    the Free Software Foundation, either version 3 of the License, or
    (at your option) any later version.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


#include <string.h>
#include <stdlib.h>
#include "http.h"
#include "parse.h"
#include "download.h"
#include "dbg.h"

static struct path * parse_path (char **argv);
static void free_path (struct path *download_path);

int main(int argc, char *argv[])
{
    struct path *download_path;
    struct ip_list *ips;
    if (argc != 2) exit (1);

    download_path = parse_path (argv);

    ips = parse_addr (download_path->hostname);
    
    download (ips->ipstr, download_path->port, download_path->path, download_path->file_name);
    
    free_path (download_path);
    free_ip_list (ips);
    return 0;
}


static struct path * parse_path (char **argv)
{
    struct path *download_path = NULL;
    volatile int len;
    char *path_start = NULL;
    char *path_end = NULL;

    len = strlen (argv[1]);
    
    download_path = (struct path *) malloc (sizeof (struct path));
    if (download_path == NULL) exit (1);
    bzero (download_path, sizeof (struct path));
    
    download_path->hostname = (char *) calloc ((size_t)len, sizeof (char));
    download_path->path = (char *) calloc ((size_t)len, sizeof (char));
    download_path->file_name = (char *) calloc ((size_t)len, sizeof(char));
    
    CHECK_MEM(download_path->hostname);
    CHECK_MEM(download_path->path);
    CHECK_MEM(download_path->file_name);
    
    path_start = strchr (argv[1], '/');
    path_end = strrchr (argv[1], '/');
    
    if (path_start == NULL || path_end == NULL)
    {
        strncpy (download_path->hostname, argv[1], strlen (argv[1]));
        strncpy (download_path->path, "/", strlen ("/"));
        strncpy (download_path->file_name, "index.html", strlen ("index.html"));
        return download_path;
    }
    else
    {
        memcpy (download_path->hostname, argv[1], len - strlen (path_start));
        memcpy (download_path->path, path_start, strlen (path_start) - strlen (path_end)+1);
        memcpy (download_path->file_name, path_end + 1, strlen (path_end));
        return download_path;
    }
    

error:
    return NULL;
}

static void free_path (struct path *download_path)
{
    free (download_path->hostname);
    free (download_path->path);
    free (download_path->file_name);
    
    download_path->hostname = NULL;
    download_path->path = NULL;
    download_path->file_name = NULL;
    
    free (download_path);
    download_path = NULL;
}
