#!/usr/bin/env python
# coding=utf-8
import urllib
import urllib2
class CacheUtils:

    def cbk(a, b, c):  
        '''''回调函数 
        @a: 已经下载的数据块 
        @b: 数据块的大小 
        @c: 远程文件的大小 
        '''  
        per = 100.0 * a * b / c  
        if per > 100:  
            per = 100  
        print '%.2f%%' % per  

    def download(self, url, local):
        urllib.urlretrieve(url, local, cbk)

    def cache(self, url):
        fileName = url.split('/')[-1]
        req = urllib2.Request(url)
        #req.add_header('Range', 'bytes=0-7000')
        response = urllib2.urlopen(req)
        buffer = response.read()
        with open("./cache/img.png", "a+") as fp:
            fp.write(buffer)

        
