#!/usr/bin/env python
# coding=utf-8
"""
#fileIO read & print
with open("py.txt") as fp:
    #data = fp.read()
    data = fp.readline()
    print(data)
"""


"""
#fileIO foreach write
with open("py.txt", 'a+') as fp:
    for num in range(1,10):
        #fp.write("test" + str(num) + "\n" )
        fp.writelines("test" + str(num))
"""


with open("py.txt", 'a+') as fp:
    for line in fp.readlines():
        line = line.strip()     #去除首尾空白
        print(line)
