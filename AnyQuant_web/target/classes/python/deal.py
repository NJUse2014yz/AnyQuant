#ecoding=utf-8
import tushare as ts

import time
#获得当前时间时间戳
import datetime
#获得当前时间
import json

txtpath="E:/python/stockId.txt"

fp=open(txtpath)
s=""
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()
now = datetime.datetime.now()
#转换为指定的格式:
otherStyleTime = now.strftime("%Y-%m-%d")
 
df = ts.get_sina_dd(s, date=otherStyleTime, vol=500)  #指定大于等于500手的数据

while (df is None):
    now=now-datetime.timedelta(days=1)
    otherStyleTime=now.strftime("%Y-%m-%d")
    df = ts.get_sina_dd(s, date=otherStyleTime, vol=500)  #指定大于等于500手的数据

while (df.to_json(orient='records',force_ascii=False)=="[]"):
    now=now-datetime.timedelta(days=1)
    otherStyleTime=now.strftime("%Y-%m-%d")
    df = ts.get_sina_dd(s, date=otherStyleTime, vol=500)  #指定大于等于500手的数据

      
print df.to_json(orient='records',force_ascii=False)