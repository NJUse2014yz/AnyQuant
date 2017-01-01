#coding=utf-8

import MySQLdb
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator, FormatStrFormatter

conn=MySQLdb.connect(host="localhost",user="root",passwd="141250175",db="anyquant")
cursor =conn.cursor()

ksql ="select k from quotadata_sh600000"
cursor.execute(ksql)
k=cursor.fetchall()

dsql ="select d from quotadata_sh600000"
cursor.execute(dsql)
d=cursor.fetchall()

jsql ="select j from quotadata_sh600000"
cursor.execute(jsql)
j=cursor.fetchall()

countsql="select count(*) from quotadata_sh600000"
cursor.execute(countsql)
count=cursor.fetchone()
num=count[0]

m5sql ="select 10*m5 from quotadata_sh600000"
cursor.execute(m5sql)
m5=cursor.fetchall()

cursor.close()
conn.close()

print k

plotk=plt.plot(k,label='k')
plotd=plt.plot(d,label='d')
plotj=plt.plot(j,label='j')
plotm5=plt.plot(m5,label='m5')
plot0=plt.plot(tuple(np.zeros(num, np.int)),label='0')
plt.legend()
plt.xlim(0,100)
plt.show()