/*
    "parse.h"
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


#ifndef __PARSE_H
#define __PARSE_H

struct ip_list {
    char ipstr[16];
    struct ip_list *next;
};

extern struct ip_list *parse_addr (char const *hostname);
extern void free_ip_list (struct ip_list *ips);

#endif /* __PARSE_H */
