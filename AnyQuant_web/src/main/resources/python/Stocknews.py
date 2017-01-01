#encoding:utf-8
import tushare as ts
txtpath="src/main/resources/python/stockId.txt"

fp=open(txtpath)
s=""
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()
s=s+fp.read()

df=ts.get_notices(code=s)
df.to_json('E:/stocknews.json',orient='records',force_ascii=False)
#
print df.to_json(orient='records',force_ascii=False)
