#!/bin/bash
#以下代码中(())可替换为[]

a="up"

if(($a=="down")); then
    echo "if"
elif(($a=="up")); then
    echo "else" 
else
    echo "helo"
    echo $a
fi
