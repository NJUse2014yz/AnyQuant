import tushare as ts
#coding=utf-8  

df=ts.get_industry_classified()
df.to_json('src\\main\\resources\\python\\industry.json',orient='records',force_ascii=False)

