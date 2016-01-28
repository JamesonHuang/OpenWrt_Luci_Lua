#!/bin/sh

#---------------------------1st---------------------
printf "helloworld\n";

#(1) $PATH定义路径: /etc/environment & /etc/profile & ~/.bashrc

#(2) 输出字符串长度
var=123456789
echo ${#var}

#(3)var运算
num1=10
num2=10
result=$((num1 + num2))
echo $result
result=$(($num1 + $num2))
echo $result

#(4)输出stderr & stdout
ls 2>&1 > test.txt
echo $?

#(5)tee -a XXX 输出并重定向到文件
#ps: -a: 追加到文件末尾


#---------------------------2nd--------------------
arr=(1 2 3 4 5 6)
echo ${#arr[*]}
echo ${arr[*]}

#sh -x XXX      debug shell



<<EOF
let result=num1+num2
echo $result
echo "res1 add: $result"

let result++
echo "res1 ++: $result"

let result--
echo "res1 --: $result"

let result+=5
echo "res1 +=: $result"

let result-=5
echo "res1 -=: $result"
EOF

