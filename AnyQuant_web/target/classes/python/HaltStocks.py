#encoding:utf-8
import tushare as ts
df=ts.get_suspended()
print df.to_json(orient='records',force_ascii=False)