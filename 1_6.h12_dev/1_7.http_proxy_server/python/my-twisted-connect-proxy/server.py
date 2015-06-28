#!/usr/bin/env python
# coding=UTF-8
# Copyright (c) 2014, Peter Ruibal.  All rights reserved.
#
# This source code is licensed under the BSD-style license found in the
# LICENSE file in the root directory of this source tree.
#
from twisted.internet.protocol import Protocol, ClientFactory
from twisted.web.proxy import Proxy, ProxyRequest
from twisted.python import log
from CacheUtils import CacheUtils

import urlparse

cacheUtils = CacheUtils()

class ConnectProxyRequest(ProxyRequest):
    """HTTP ProxyRequest handler (factory) that supports CONNECT"""

    connectedProtocol = None

    def process(self):
        #download all
        #fileName = cacheUtils.parseUrl2FileName(self.path)            
        #cacheUtils.download(self.path, "./download/" + fileName)    
        
        #download cache
        range = "0-7000"
        cacheUtils.cache(self.path, range)
        
        #checkReq & save url & range
        if False == cacheUtils.checkReq(self.path):
            cacheUtils.saveReq(self.path, range)
            #cacheUtils.saveReq(self.path, str(self.getHeader("Range")))
        
        # CONNECT另写函数processConnectRequest实现
        if self.method == 'CONNECT':
            self.processConnectRequest()
        else:
            ProxyRequest.process(self)

    def fail(self, message, body):
        cacheUtils.delReq(self.path)

        self.setResponseCode(501, message)
        self.responseHeaders.addRawHeader("Content-Type", "text/html")
        self.write(body)
        self.finish()

    def splitHostPort(self, hostport, default_port):
        port = default_port
        parts = hostport.split(':', 1)
        if len(parts) == 2:
            try:
                port = int(parts[1])
            except ValueError:
                pass
        return parts[0], port

    def processConnectRequest(self):
        parsed = urlparse.urlparse(self.uri)
        default_port = self.ports.get(parsed.scheme)

        host, port = self.splitHostPort(parsed.netloc or parsed.path,
                                        default_port)
        if port is None:
            self.fail("Bad CONNECT Request",
                      "Unable to parse port from URI: %s" % repr(self.uri))
            return

        clientFactory = ConnectProxyClientFactory(host, port, self)

        # TODO provide an API to set proxy connect timeouts
        self.reactor.connectTCP(host, port, clientFactory)

#类似protocol，在这里作为客户端的角色
class ConnectProxy(Proxy):
    """HTTP Server Protocol that supports CONNECT"""
    requestFactory = ConnectProxyRequest
    connectedRemote = None

    def requestDone(self, request):
        """connect请求 && 属于远程客户端的请求，则将该客户端改成当前代理服务器"""
        if request.method == 'CONNECT' and self.connectedRemote is not None:
            self.connectedRemote.connectedClient = self
        else:
            Proxy.requestDone(self, request)

    def connectionLost(self, reason):
        """代理服务器请求web服务器时，连接断开了
            ，也要通知并断开代理服务器与客户端的连接"""
        if self.connectedRemote is not None:
            self.connectedRemote.transport.loseConnection()
        Proxy.connectionLost(self, reason)

    def dataReceived(self, data):
        # 数据收到后，如果代理服务器自己的请求，自己接收，
        if self.connectedRemote is None:
            Proxy.dataReceived(self, data)
        else:
            # Once proxy is connected, forward all bytes received
            # from the original client to the remote server.
            # 如果是远程客户端的请求，则将数据传给远程客户端
            self.connectedRemote.transport.write(data)

#作为普通server角色
class ConnectProxyClient(Protocol):
    connectedClient = None

    def connectionMade(self):
        self.factory.request.channel.connectedRemote = self
        self.factory.request.setResponseCode(200, "CONNECT OK")
        self.factory.request.setHeader('X-Connected-IP',
                                       self.transport.realAddress[0])
        self.factory.request.setHeader('Content-Length', '0')
        self.factory.request.finish()

    def connectionLost(self, reason):
        if self.connectedClient is not None:
            self.connectedClient.transport.loseConnection()

    def dataReceived(self, data):
        if self.connectedClient is not None:
            # Forward all bytes from the remote server back to the
            # original connected client
            self.connectedClient.transport.write(data)
        else:
            log.msg("UNEXPECTED DATA RECEIVED:", data)

#数据收到后会激活该对象
class ConnectProxyClientFactory(ClientFactory):
    protocol = ConnectProxyClient

    def __init__(self, host, port, request):
        self.request = request
        self.host = host
        self.port = port

    def clientConnectionFailed(self, connector, reason):
        self.request.fail("Gateway Error", str(reason))


if __name__ == '__main__':
    import sys
    log.startLogging(sys.stderr)

    import argparse
    ap = argparse.ArgumentParser()
    ap.add_argument('port', default=8080, nargs='?', type=int)
    ap.add_argument('--ssl-cert', type=str)
    ap.add_argument('--ssl-key', type=str)
    ns = ap.parse_args()

    import twisted.web.http
    factory = twisted.web.http.HTTPFactory()
    factory.protocol = ConnectProxy

    import twisted.internet
    if ns.ssl_key and not ns.ssl_cert:
        log.msg("--ssl-key must be used with --ssl-cert")
        sys.exit(1)
    if ns.ssl_cert:
        from twisted.internet import ssl
        with open(ns.ssl_cert, 'rb') as fp:
            ssl_cert = fp.read()
        if ns.ssl_key:
            from OpenSSL import crypto
            with open(ns.ssl_key, 'rb') as fp:
                ssl_key = fp.read()
            certificate = ssl.PrivateCertificate.load(
                    ssl_cert,
                    ssl.KeyPair.load(ssl_key, crypto.FILETYPE_PEM),
                    crypto.FILETYPE_PEM)
        else:
            certificate = ssl.PrivateCertificate.loadPEM(ssl_cert)
        twisted.internet.reactor.listenSSL(ns.port, factory,
                                           certificate.options())
    else:
        twisted.internet.reactor.listenTCP(ns.port, factory)
    twisted.internet.reactor.run()
