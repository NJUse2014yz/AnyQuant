#coding=utf-8

import MySQLdb
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator, FormatStrFormatter

conn=MySQLdb.connect(host="localhost",user="root",passwd="141250175",db="anyquant")
cursor =conn.cursor()

rsisql ="select rsi from quotadata_sh600000"
cursor.execute(rsisql)
rsi=cursor.fetchall()

closesql ="select close from historydata_sh600000"
cursor.execute(closesql)
close=cursor.fetchall()

countsql="select count(*) from quotadata_sh600000"
cursor.execute(countsql)
count=cursor.fetchone()
num=count[0]

cursor.close()
conn.close()
 
x=np.linspace(0,num,num,endpoint=True)

plt.plot(x,rsi,label="rsi")
plt.plot(x,close,label="close")

plt.plot(x,100/x*x)
plt.plot(x,80/x*x)
plt.plot(x,50/x*x)
plt.plot(x,20/x*x)
plt.plot(x,0/x*x)
plt.xlim(0,900)
plt.grid(True)
plt.legend()
plt.show()