 #coding=utf-8

import MySQLdb
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator, FormatStrFormatter

conn=MySQLdb.connect(host="localhost",user="root",passwd="141250175",db="anyquant")
cursor =conn.cursor()

boll1sql ="select boll1 from quotadata_sh600000"
cursor.execute(boll1sql)
boll1=cursor.fetchall()

boll2sql ="select boll2 from quotadata_sh600000"
cursor.execute(boll2sql)
boll2=cursor.fetchall()

boll3sql ="select boll3 from quotadata_sh600000"
cursor.execute(boll3sql)
boll3=cursor.fetchall()

countsql="select count(*) from quotadata_sh600000"
cursor.execute(countsql)
count=cursor.fetchone()
num=count[0]

closesql="select close from historydata_sh600000"
cursor.execute(closesql)
close=cursor.fetchall()

highsql="select high from historydata_sh600000"
cursor.execute(highsql)
high=cursor.fetchall()

lowsql="select low from historydata_sh600000"
cursor.execute(lowsql)
low=cursor.fetchall()
  
cursor.close()
conn.close()
  
plt.plot(boll1,label="boll1")
plt.plot(boll2,label="boll2")
plt.plot(boll3,label="boll3")
plt.plot(close,label="close")
plt.plot(high,label="high")
plt.plot(low,label="low")
plt.plot(tuple(np.zeros(num, np.int)))
plt.xlim(0,300)
plt.grid(True)
plt.legend()
plt.show()