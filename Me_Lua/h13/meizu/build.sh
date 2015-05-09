#!/bin/sh
/etc/init.d/uhttpd stop
rm -rf /tmp/luci-indexcache /tmp/luci-modulecache
rm -rf /tmp/luci-sessions
/etc/init.d/uhttpd start
