#!/usr/bin/env python
# coding=utf-8
import urllib2
#url = "http://download.meizu.com/Firmware/Flyme/m1_note/4.2/cmcc/20150507154711/61746475/update.zip"
url = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superplus/img/logo_white_ee663702.png"

#url = "http://www.sina.com.cn"
req = urllib2.Request(url)
req.add_header('Range', 'bytes=7001-14000')
response = urllib2.urlopen(req)
#print(response.info())

html = response.read()
with open("test4.png", "a+") as fp:
    fp.write(html)
#print html
