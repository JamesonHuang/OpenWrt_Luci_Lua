#!/usr/bin/env python
# coding=utf-8

#遍历字符串每个字符
word = 'helloworld!'
str = "strcat method4:  "
for letter in word:
    print "strcat method1:  " + letter
    print "strcat method2:  ", letter
    print "strcat method3:  %s" %(letter)
    #print str.join(letter)                     #fail

#遍历数组元素
words = ['hello', 'world']
for word in words:
    print "word:",word

#for（int i = 0； i < 10; i += 2）
for num in range(0, 10, 2):
    print num

#index
for index in range(len(words)):
    print words[index]

