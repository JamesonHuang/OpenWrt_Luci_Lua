#!/usr/bin/env python
# coding=utf-8
import urllib
import urllib2
import os
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

""" urllib下载  
url = 'http://www.sina.com.cn'  
#local = '/home/rh/my_code/OpenWrt_Luci_Lua/1_7.http_proxy_server/python/testCode/sina.html'  
#local = '/home/rh/my_code/OpenWrt_Luci_Lua/1_7.http_proxy_server/python/testCode/test.html' 
#local = './cache/backup'  

fileName = url.split('/')[-1]
local = os.path.join('./cache',fileName)

urllib.urlretrieve(url, local, cbk)

"""

#url = "http://download.meizu.com/Firmware/Flyme/m1_note/4.2/cmcc/20150507154711/61746475/update.zip"
url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png"
req = urllib2.Request(url)
req.add_header('Range', 'bytes=0-7000')
response = urllib2.urlopen(req)
buffer = response.read()
with open("./cache/test1.png", "a+") as fp:
    fp.write(buffer)


req = urllib2.Request(url)
req.add_header('Range', 'bytes=7001-14175')
response = urllib2.urlopen(req)
buffer = response.read()
with open("./cache/test2.png", "a+") as fp:
    fp.write(buffer)







""" urllib2下载 request失败
url = 'http://www.sina.com.cn'
req = urllib2.Request(url)
#req.add_header('range','byte:1-100')
response = urllib2.urlopen(req)
file = response.read()

with open("urllib2test.html", "a+") as fp:
   fp.write(file) 
"""
"""urlopener 失败"""
url = 'http://www.sina.com.cn'
opener = urllib2.build_opener()  
#不加头信息则出现403错误和乱码  
opener.addheaders = [('User-agent', 'Mozilla/5.0')];
#opener.addheaders = [('range', 'byte:1-100')]
htmlAll = opener.open( url ).read()  
reg1Floor = '<div class="msgfont">(.*?)</div>'  
#文件保存编码和文件编辑编码都是utf-8，所以decode一次，不然会出现乱码，但是不影响结果。  
#htmlAll.decode('utf-8') 
print(htmlAll)
 

