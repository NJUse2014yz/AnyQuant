#encoding:utf-8
import numpy as np
from scipy import stats
import matplotlib.pyplot as plt
import pandas as pd

import time
import datetime
import json
# from sklearn import datasets, linear_model
import tushare as ts
from pandas.lib import list_to_object_array
txtpath = "E:/python/strategy.txt"
  
fp = open(txtpath)
s = ""
s = s + fp.read()
s = s + fp.read()
s = s + fp.read()
s = s + fp.read()
s = s + fp.read()
s = s + fp.read()
  
endDay = datetime.datetime.now()
  
endDate = endDay.strftime("%Y-%m-%d")
startDay = endDay - datetime.timedelta(days=150)
startDate = startDay.strftime("%Y-%m-%d")
  
# print startDate,endDate
  
df = ts.get_hist_data(s, start=startDate, end=endDate)
while df.index.size < 120:
    startDay = startDay - datetime.timedelta(days=30)
    startDate = startDay.strftime("%Y-%m-%d")
    df = ts.get_hist_data(s, start=startDate, end=endDate)
# print df.to_json(orient='records',force_ascii=False)
# df.to_csv("E:/a.csv",orient='records',force_ascii=False)
length = df.index.size
# change df to array
arrayAll = np.nan_to_num(df.values)

# the y
resultlist = (df.iloc[0:length - 40, :]).values

openlist = resultlist[:, 0]
closelist = resultlist[:, 2]
volumnlist = resultlist[:, 4]
turnoverlist = resultlist[:, 13]

# the x
x = np.eye(160, length - 40, 0, dtype=float)  

for i in range(0, 40):
    temp = df.iloc[i:length - 40 + i, :].values
    x[i] = temp[:, 0]
    x[i + 40] = temp[:, 2]
    x[i + 80] = temp[:, 4]
    x[i + 120] = temp[:, 13]

w_open = np.linalg.lstsq(x.T, openlist)[0]
w_close = np.linalg.lstsq(x.T, closelist)[0]
w_volumn = np.linalg.lstsq(x.T, volumnlist)[0]
w_turnover = np.linalg.lstsq(x.T, turnoverlist)[0]
  
# print w_open
# print w_close
# print w_volumn
# print w_turnover

base = np.eye(160, 1, 0, dtype=float) 
# the newst on top
for k in range(0, 40):
    temp = df.iloc[k, :].values
    base[k] = np.array([temp[0]])
    base[k + 40] = np.array([temp[2]])
    base[k + 80] = np.array([temp[4]])
    base[k + 120] = np.array([temp[13]])
   
print base   
    
for count in range(0, 7):
    
    open = np.dot(w_open, base)
    close = np.dot(w_close, base)
    volumn = np.dot(w_volumn, base)
    turnover = np.dot(w_turnover, base)
    
    print open,close,volumn,turnover
    
    base = np.delete(base, 159, 0)
    base = np.delete(base, 119, 0)
    base = np.delete(base, 79, 0)
    base = np.delete(base, 39, 0)

    base = np.insert(base, 0, [open], 0)
    base = np.insert(base, 40, [close], 0)
    base = np.insert(base, 80, [volumn], 0)
    base = np.insert(base, 120, [turnover], 0)
    
    
result = np.eye(7, 4, 0, dtype=float) 
for t in range(0, 7):
    result[t] = np.array([base[6-t][0],base[46-t][0],base[86-t][0],base[126-t][0]])
    
# print result
lis=pd.DataFrame(result,columns=['open', 'close', 'volumn', 'turnover'])
print lis.to_json(orient='records',force_ascii=False)

    
