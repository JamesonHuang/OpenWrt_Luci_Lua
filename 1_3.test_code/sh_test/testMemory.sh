#!/bin/sh
var=1
obj=40
while ((${var} < ${obj})); do
        ./testMemory.sh #call self
        var=$((var+1))
        echo "unlimited recursive call..."
done

