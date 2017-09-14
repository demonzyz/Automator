#!/bin/bash
# 查看一个apk文件的相关信息
#$echo aapt dump badging /Users/zhi/Desktop/rimet_10006126.apk
#查看package
$echo aapt dump badging /Users/zhi/Desktop/rimet_10006126.apk | grep "package"
#查看activity
$echo aapt dump badging /Users/zhi/Desktop/rimet_10006126.apk | grep "launchable-activity"
#查看当前应用的包名和activity
$echo aadb shell dumpsys activity | grep Focuse

