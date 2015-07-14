#!/bin/bash -
#===============================================================================
#
#          FILE:  cfe.sh
#
#         USAGE:  ./cfe.sh
#
#   DESCRIPTION:
#
#       OPTIONS:  ---
#  REQUIREMENTS:  ---
#          BUGS:  ---
#         NOTES:  ---
#        AUTHOR: Karl Zheng (), ZhengKarl#gmail.com
#       COMPANY: Meizu
#       CREATED: 2014年11月03日 10时27分13秒 CST
#      REVISION:  ---
#===============================================================================

#set -o nounset                              # Treat unset variables as an error

function flash_trx_img()
{
	function find_img_file()
	{
		local fn=openwrt-bcm53xx-bcm4709-meizu-r10-squashfs.trx
		local buildDirFn=build_dir/target-arm-openwrt-linux-uclibcgnueabi/linux-bcm53xx/${fn}
		dlf=${fn}
		if [ ! -f ${fn} ];then
			if [ -f ${buildDirFn} ];then
				dlf=${buildDirFn}
			else
				dlf=`find -name ${fn} | head -n 1`
			fi
		fi
		if [ ! -f "${dlf}" ];then
			fn=openwrt-ramips-mt7628-mt7628-squashfs-sysupgrade.bin
			buildDirFn=build_dir/target-mipsel_24kec+dsp_uClibc-0.9.33.2/linux-ramips_mt7628/${fn}
			if [ ! -f ${fn} ];then
				if [ -f ${buildDirFn} ];then
					dlf=${buildDirFn}
				else
					dlf=`find -name ${fn} | head -n 1`
				fi
			else
				dlf=${fn}
			fi
		fi
		echo "dlf:"
		echo "${dlf} "
		type xclip
		if [ $? == 0 ];then
			echo -n ${dlf} | xclip
		fi
	}

	find_img_file
	local flaship="192.168.233.1"
	ping -W 2 -c 1 ${flaship}
	if [ $? != 0 ];then
		ping -W 2 -c 1 "192.168.232.1"
		if [ $? != 0 ];then
			flaship="192.168.1.1"
		else
			flaship="192.168.232.1"
		fi
	fi
	echo "flaship: ${flaship}"

	function downloadfw_by_cfe()
	{
		local p=`cat <<-EOF
		-F "Upload=Upload"
		EOF`
		curl -F files=@${dlf} ${p} --referer "http://${flaship}/" \
			http://${flaship}/f2.htm
	}

	function downloadfw_by_openwrt()
	{
		local sf="/tmp/luci_session.file"
		curl "http://${flaship}/cgi-bin/luci/bs/token" 2>/dev/null > ${sf}
		local token=$(cat ${sf} | json_xs -t yaml |grep token |awk '{print $2}')
		local sysauth=$(cat ${sf} | json_xs -t yaml |grep sysauth |awk '{print $2}')
		local clean_param=""
		if [ $# -gt 0 ];then
			if [ $1 == "clean" ];then
				#clean_param='-F "clean=1"'
				clean_param='-F clean=1'
				echo clean_param="${clean_param}"
			fi
		fi
		echo curl -b "sysauth=${sysauth}; sysauth=" ${clean_param} -F f=@${dlf} "http://${flaship}/cgi-bin/luci/;stok=${token}/api/localupgrade"
		curl -b "sysauth=${sysauth}; sysauth=" ${clean_param} -F f=@${dlf} "http://${flaship}/cgi-bin/luci/;stok=${token}/api/localupgrade"
	}

	if [ "x${dlf}" == "x" ];then
		echo "Not found firmware image in cur dir."
		exit 1;
	fi

	echo "Are you really want to download file: "
	echo "${dlf} "
	echo "?"
	read -p "y|n" c
	if [ "x${c}" == "xy" -o "x${c}" == "xY" -o "x${c}" == "x" ];then
		curl -m 5 "http://${flaship}/cgi-bin/luci/bs/token" |grep token
		if [ $? == 0 ];then
			downloadfw_by_openwrt "$@"
		else
			downloadfw_by_cfe
		fi
	else
		echo "Cancled download!!"
	fi
}

flash_trx_img "$@"
