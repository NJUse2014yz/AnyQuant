import tushare as ts
#coding=utf-8  

df=ts.get_area_classified()
# df.to_json('src\\main\\resources\\python\\area.json',orient='records',force_ascii=False)
print df.to_json()