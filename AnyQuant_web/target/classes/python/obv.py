#coding=utf-8

import MySQLdb
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator, FormatStrFormatter

conn=MySQLdb.connect(host="localhost",user="root",passwd="141250175",db="anyquant")
cursor =conn.cursor()

obvsql ="select 5*obv/(1e7) from quotadata_sh600000"
cursor.execute(obvsql)
obv=cursor.fetchall()

closesql ="select close from historydata_sh600000"
cursor.execute(closesql)
close=cursor.fetchall()

countsql="select count(*) from quotadata_sh600000"
cursor.execute(countsql)
count=cursor.fetchone()
num=count[0]

cursor.close()
conn.close()

plt.plot(obv,label="obv")
plt.plot(close,label="close")

plt.xlim(0,900)
plt.grid(True)
plt.legend()
plt.show()