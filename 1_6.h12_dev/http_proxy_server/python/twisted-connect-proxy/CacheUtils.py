#!/usr/bin/env python
# coding=utf-8
import urllib
import urllib2
import json
class CacheUtils:
    @staticmethod
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
        urllib.urlretrieve(url, local, self.cbk)

    def cache(self, url, range):
        fileName = url.split('/')[-1]
        req = urllib2.Request(url)
        req.add_header('Range', 'bytes=' + range)
        response = urllib2.urlopen(req)
        buffer = response.read()
        with open("./cache/" + fileName + range, "a+") as fp:
            fp.write(buffer)

    def saveReq(self, url):

        # Reading data back
        with open('data.json', 'r') as fp:
            data = json.load(fp)
        data[url] = 4000
        # Writing JSON data
        with open('data.json', 'w') as fp:
            json.dump(data, fp)
   
    
    def checkReq(self):
        # Reading data back
        with open('data.json', 'r') as fp:
            data = json.load(fp)
        #print(data)
        #print(data.keys())
        print(data["www.baidu.com"])
        if data.get("key"): 
            print(data["key"])
        else:
            print("error")

"""
if __name__ == '__main__':
    cacheUtils = CacheUtils()
    
    #url = "http://www.sina.com.cn"
    #fileName = url.split('/')[-1]
    #cacheUtils.download(url, "./cache/" + fileName)
    
    #cacheUtils.cache("http://www.baidu.com")
    #cacheUtils.cache("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png", "0-7000")
    #cacheUtils.cache("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png", "7001-14175")

    cacheUtils.saveReq("http://www.sina.com.cn")
    
    #cacheUtils.loadReq()
"""
