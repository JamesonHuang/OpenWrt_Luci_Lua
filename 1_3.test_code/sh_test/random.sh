#!/bin/sh
random_num=$(date +%s%N)
echo $random_num

#指定范围随机数
rand(){  
    min=$1  
    max=$(($2-$min+1))  
    num=$(date +%s%N)  
    echo $(($num%$max+$min))  
}  
random_num2=$(rand 1 3)
sleep ${random_num2}
echo $random_num2
