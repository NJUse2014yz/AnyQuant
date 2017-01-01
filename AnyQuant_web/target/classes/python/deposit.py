import tushare as ts
#coding=utf-8  

df=ts.get_deposit_rate();
df.to_json('src\\main\\resources\\python\\deposit.json',orient='records',force_ascii=False)