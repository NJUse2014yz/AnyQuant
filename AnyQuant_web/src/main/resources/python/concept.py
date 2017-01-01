import tushare as ts
#coding=utf-8  

df=ts.get_concept_classified()
df.to_json('src\\main\\resources\\python\\concept.json',orient='records',force_ascii=False)
