#encoding:utf-8
import tushare as ts
import time
#获得当前时间时间戳
import datetime
#获得当前时间
import json

now = datetime.datetime.now()
#转换为指定的格式:
otherStyleTime = now.strftime("%Y-%m-%d")
 
df=ts.top_list(otherStyleTime)
while (df is None):
    now=now-datetime.timedelta(days=1)
    otherStyleTime=now.strftime("%Y-%m-%d")
    df=ts.top_list(otherStyleTime)
while (df.to_json(orient='records',force_ascii=False)=="[]"):
    now=now-datetime.timedelta(days=1)
    otherStyleTime=now.strftime("%Y-%m-%d")
    df=ts.top_list(otherStyleTime)
      
print df.to_json(orient='records',force_ascii=False)