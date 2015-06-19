#!/usr/bin/env python
# coding=utf-8
import urllib
import urllib2
import json
class CacheUtils:
    def download(self, url, local):
        #urllib.urlretrieve(url, local, self.cbk)
        urllib.urlretrieve(url, local)

    def parseUrl2FileName(self, url):
        fileName = url.split('/')[-1]
        #deal with pure url
        if fileName == "":
            fileName = url.split("//")[-1].replace('/', '')
        return fileName

    def cache(self, url, range):
        fileName = self.parseUrl2FileName(url)
        req = urllib2.Request(url)
        req.add_header('Range', 'bytes=' + range)
        response = urllib2.urlopen(req)
        buffer = response.read()
        with open("./cache/" + fileName + range, "a+") as fp:
            fp.write(buffer)

    def saveReq(self, url, range):
        # Reading data back
        with open('data.json', 'r') as fp:
            data = json.load(fp)
        data[url] = range
        # Writing JSON data
        with open('data.json', 'w') as fp:
            json.dump(data, fp)
    
    def delReq(sel, url):
        # Reading data back
        with open('data.json', 'r') as fp:
            data = json.load(fp)
        if data.get(url):
            del data[url]
        # Writing JSON data
        with open('data.json', 'w') as fp:
            json.dump(data, fp)

    def checkReq(self, url):
        # Reading data back
        with open('data.json', 'r') as fp:
            data = json.load(fp)
        if data.get(url):
            fileName = url.split('/')[-1]
            with open('GotIt.txt', 'a+') as fp:
                if data[url] == "None":
                    fp.write("the file you request has been downloaded: ./download/" + fileName + "\n")
                else:
                    fp.write("the file you request has been cached: ./cache/" + fileName + "  " + data[url] + "\n")
            return True
        else:
            return False


"""
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

if __name__ == '__main__':
    cacheUtils = CacheUtils()
    cacheUtils.delReq("http://static.youku.com/v1.0.1029/cms/img/zy.png")
    #url = "http://www.sina.com.cn"
    #fileName = url.split('/')[-1]
    #cacheUtils.download(url, "./cache/" + fileName)
    
    #cacheUtils.cache("http://www.baidu.com")
    #cacheUtils.cache("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png", "0-7000")
    #cacheUtils.cache("https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png", "7001-14175")

    
    #cacheUtils.saveReq("http://www.sina.com.cn")
    
    #cacheUtils.loadReq()
"""
