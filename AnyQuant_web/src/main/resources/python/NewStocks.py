#encoding:utf-8
import tushare as ts
df=ts.new_stocks()
print df.to_json(orient='records',force_ascii=False)