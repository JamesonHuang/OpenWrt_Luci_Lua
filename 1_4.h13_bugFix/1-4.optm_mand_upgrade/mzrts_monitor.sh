#!/bin/sh
#===============================================================================
#
#          FILE:  mzrts_monitor.sh
#
#         USAGE:  ./mzrts_monitor.sh
#
#   DESCRIPTION:
#
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR: Karl Zheng (), ZhengKarl#gmail.com
#       COMPANY: Meizu
#       CREATED: 2015年01月14日 21时43分00秒 CST
#      REVISION:  ---
#===============================================================================

check_mandatory_upgrade()
{
    echo "check_mandatory_upgrade"
    #gen random_num
    random_num=`awk 'BEGIN{srand();print int(rand()*3600)}'`
    #end
    sleep ${random_num}
    lua -e 'require("meizu.upgdfs").check_mandatory_upgrade()'
    echo "mandatory_upgrade success!"
}

mzrts_monitor()
{
	local MZRTSMONITORLOG="/tmp/mzrts_monitor.log"
	local MZRTSLOG="/tmp/mzrts.log"
	local mzrts_cpu_usage=$(top -n 1 |sed -n -e '/% \/usr.*mzrt/s#%##gp'|awk '{print $7}')
	if [ "x${mzrts_cpu_usage}" != "x" ];then
		if [ ${mzrts_cpu_usage} -ge 30 ];then
			echo "mzrts_cpu_usage: ${mzrts_cpu_usage}"
			echo "killall mzrts"
			killall mzrts
			sleep 1
		fi
	fi
	local is_mzrts_run="$(ps w|grep /usr/bin/mzrts|grep -v 'mzrts_monitor'|grep -v 'grep'| wc -l)"
	if [ ${is_mzrts_run} -lt 1 ];then
		echo "" >> ${MZRTSMONITORLOG}
		date >> ${MZRTSMONITORLOG}
		echo "/usr/bin/mzrts -llon | tee -a ${MZRTSLOG} &"
		/usr/bin/mzrts -llon | tee -a ${MZRTSLOG} &
		echo "" >> ${MZRTSMONITORLOG}
	else
		echo "is_mzrts_run: ${is_mzrts_run}"
	fi
    local timers=$(date +%H)
    if [ ${timers} -ge 18 ] && [ ${timers} -le 19 ];then
        if [ ! -f "/tmp/is_upgrading" ];then
	        touch /tmp/is_upgrading
            check_mandatory_upgrade & 
            rm /tmp/is_upgrading
        fi
    fi
	 [ -e /tmp/testTimeout.html ] && rm /tmp/testTimeout.html
	 sh -c '/usr/bin/timeout -t 3 /usr/bin/wget "http://127.0.0.1/cgi-bin/luci/bs/testTimeout" -q -O /tmp/testTimeout.html'
	 [ ! -e /tmp/testTimeout.html ] && echo "/etc/init.d/uhttpd restart" && /etc/init.d/uhttpd restart
}

while [ true ];
do
	sleep 3
	mzrts_monitor
done
