
check_mandatory_upgrade()
{
    echo "check_mandatory_upgrade"
    touch /tmp/is_upgrading
    #gen random_num
    min=1  
    max=$((3600-$min+1))  
    num=$(date +%s)
    random_num=$(($num%$max+$min))
    #end
    sleep ${random_num}
	lua -e 'require("meizu.upgdfs").check_mandatory_upgrade()'
    echo "mandatory_upgrade success!"
    rm /tmp/is_upgrading
}

mzrts_monitor()
{
	
    if [ ${timers} -ge 18 ] && [ ${timers} -le 19 ];then
        if [ ! -f "/tmp/is_upgrading" ];then
            check_mandatory_upgrade & 
        fi
    fi

}


