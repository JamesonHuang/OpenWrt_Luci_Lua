#!/bin/bash

ip="192.168.232.237"
flag=0
position=1

mpd_seek()
{
    random_num=`awk 'BEGIN{srand();print int(rand()*160)}'`
    echo "seek $random_num"
    ncat $ip 6600 << EOF
seekid $position $random_num
EOF
}

mpd_play()
{
    echo "play"
    echo $flag
    ncat $ip 6600 << EOF
play
EOF
}

mpd_pause()
{
    echo "pause"
    echo $flag
    ncat $ip 6600 << EOF
pause
EOF
}

while ((true)); do
    mpd_seek
    #if [ $flag != 0 ];then
    #    mpd_play
    #    flag=0
    #else
    #    mpd_pause
    #    flag=1
    #fi

    #sleep_num=`awk 'BEGIN{srand();print int(rand()*5)}'`
    #echo $sleep_num
    #sleep_num=$((sleep_num+4))
    #sleep $sleep_num

    #mpd_seek
    sleep 2 
done
