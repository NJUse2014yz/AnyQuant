#encoding:utf-8
import tushare as ts
df=ts.get_latest_news(top=100)#打印出新闻内容
print df.to_json(orient='records',force_ascii=False)