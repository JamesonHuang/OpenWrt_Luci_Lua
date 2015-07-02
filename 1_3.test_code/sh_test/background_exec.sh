#!/bin/bash
function fileNum(){
while true
do
    echo "sleep background"
    sleep 5
done
}
fileNum &
while true
do
    echo "sleep foreground"
    sleep 1
done

