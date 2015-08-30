#!/bin/bash
#以下代码中(())可替换为[]


#--------------------------------------
echo "var define"

var="helo"          #定义变量不需加$
echo ${var}         #使用变量时加$
echo "---------------------------"
echo ""

#--------------------------------------
echo "echo about str"

#单引号中任何字符均原样输出
#单引号里不能出现单引号（使用转义符也不行）
single_str='helo'

#双引号里可以有变量
#双引号里可以出现转义字符
double_str="hello,\" ${single_str} \" "
echo $single_str
echo $double_str
echo "---------------------------"
echo ""

#--------------------------------------
echo "字符串连接："
#字符串连接
#method 1
echo "method1: ","helo", "helo"

#method 2
str="strcat"
str2="helo"
echo "method2:  ${str},${str2}"

#method 3
echo "method3:" ${str} ${str2}
echo "---------------------------"
echo ""

#--------------------------------------
echo "get strlen:"
echo "strlen: ", "${#str}"
echo "---------------------------"
echo ""

#--------------------------------------
echo "substr:"
substr="hello Meizu!"
echo ${substr:1:4}
echo "---------------------------"
echo ""


#--------------------------------------
echo "查找子串: error"
echo 'expr Meizu "${substr}"'
echo "---------------------------"
echo ""

#--------------------------------------
echo "for sh format:"

for((i=0; i<10; i++)) do
    echo $i
done
echo "---------------------------"
echo ""

#--------------------------------------
echo "if-else:"
min=10
max=10
if ((${min} < ${max})); then
    echo "min < max"
elif ((${min} > ${max})); then
    echo "min > max"
else
    echo "min = max"
fi
echo "---------------------------"
echo ""

#--------------------------------------
echo "while"
var=10
while ((${var} > 0)); do
    var=$((var-1))
    echo $var
done
echo "----------------------;-----"
echo ""

#--------------------------------------
echo "执行shell命令，将结果赋给变量:"
#var=`date`
var=$(date)
echo $var
echo "---------------------------"
echo ""

#--------------------------------------
echo "echo不换行:"
# -e 开启转义 \c 不换行
echo -e "OK! \c oo"    
echo "---------------------------"
echo ""

#--------------------------------------
echo -e "前台运行shell:\t\c"
ls
echo "后台运行shell："
ls &
echo "---------------------------"
echo ""
#--------------------------------------
echo "/dev/null:"
echo "helo" > /dev/null
echo "---------------------------"
echo ""

#--------------------------------------
echo '$?:'
ls
echo $?
echo "---------------------------"
echo ""

#--------------------------------------
echo HELLO'X'
echo "---------------------------"
echo ""
