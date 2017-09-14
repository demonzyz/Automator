#!/bin/bash
# 开启服务
$echo screen -ls | grep Detached | awk '{print $1}' | awk -F\. '{print $1}' > screenPid.txt
pid=$(cat screenPid.txt)
$echo screen -X -S $pid quit
$echo killall -9 node