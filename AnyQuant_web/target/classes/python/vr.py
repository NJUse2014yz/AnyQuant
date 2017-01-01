 #coding=utf-8

import MySQLdb
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator, FormatStrFormatter

conn=MySQLdb.connect(host="localhost",user="root",passwd="141250175",db="anyquant")
cursor =conn.cursor()

vrsql ="select vr from quotadata_sh600000"
cursor.execute(vrsql)
vr=cursor.fetchall()

closesql="select 20*close from historydata_sh600000"
cursor.execute(closesql)
close=cursor.fetchall()

countsql ="select count(*) from quotadata_sh600000"
cursor.execute(countsql)
count=cursor.fetchone()
num=count[0]

cursor.close()
conn.close()

x=np.linspace(0,num,num,endpoint=True)

plt.plot(vr,label="vr")
plt.plot(close,label="close")
plt.plot(x,40/x*x)
plt.plot(x,70/x*x)
plt.plot(x,160/x*x)
plt.plot(x,450/x*x)
plt.xlim(0,300)
plt.grid(True)
plt.legend()
plt.show()