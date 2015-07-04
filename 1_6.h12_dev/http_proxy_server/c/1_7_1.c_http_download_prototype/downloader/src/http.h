/*
    "http.h"
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


#ifndef __HTTP_H
#define __HTTP_H 

#define PORT 80
#define BUFFER 1024
#define NAME_LEN 255

extern int download (char const *ipstr, int port, char const *path, char const *file_name);

#endif /* __HTTP_H */
