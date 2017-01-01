#coding=utf-8

import MySQLdb
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator, FormatStrFormatter

conn=MySQLdb.connect(host="localhost",user="root",passwd="141250175",db="anyquant")
cursor =conn.cursor()

dihsql ="select dih12 from quotadata_sh600000"
cursor.execute(dihsql)
dih=cursor.fetchall()

dilsql ="select dil12 from quotadata_sh600000"
cursor.execute(dilsql)
dil=cursor.fetchall()

countsql="select count(*) from quotadata_sh600000"
cursor.execute(countsql)
count=cursor.fetchone()
num=count[0]

cursor.close()
conn.close()

plt.plot(dih,label="dih")
plt.plot(dil,label="dil")

plt.xlim(0,900)
plt.grid(True)
plt.legend()
plt.show()