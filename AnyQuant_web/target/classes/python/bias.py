#coding=utf-8

import MySQLdb
import numpy as np
import matplotlib.pyplot as plt
from matplotlib.ticker import MultipleLocator, FormatStrFormatter

conn=MySQLdb.connect(host="localhost",user="root",passwd="141250175",db="anyquant")
cursor =conn.cursor()

bias5sql ="select bias5 from quotadata_sh600000"
cursor.execute(bias5sql)
bias5=cursor.fetchall()

bias10sql ="select bias10 from quotadata_sh600000"
cursor.execute(bias10sql)
bias10=cursor.fetchall()

bias20sql ="select bias20 from quotadata_sh600000"
cursor.execute(bias20sql)
bias20=cursor.fetchall()

# m5sql ="select m5 from quotadata_sh600000"
# cursor.execute(m5sql)
# m5=cursor.fetchall()

# m10sql ="select m10 from quotadata_sh600000"
# cursor.execute(m10sql)
# m10=cursor.fetchall()

# m30sql ="select m30 from quotadata_sh600000"
# cursor.execute(m30sql)
# m30=cursor.fetchall()

countsql="select count(*) from quotadata_sh600000"
cursor.execute(countsql)
count=cursor.fetchone()
num=count[0]

closesql="select close from historydata_sh600000"
cursor.execute(closesql)
close=cursor.fetchall()
  
cursor.close()
conn.close()
 
x=np.linspace(0,num,num,endpoint=True)

plt.plot(x,bias5,label="bias5")
plt.plot(x,bias10,label="bias10")
plt.plot(x,bias20,label="bias20")

plt.plot(x,close,label="close")
# plt.plot(x,m5,label="m5")
# plt.plot(x,m10,label="m10")
# plt.plot(x,m30,label="m30")

# plt.plot(x,tuple(np.zeros(num, np.int)))

plt.plot(x,-2.7/x*x)
plt.plot(x,3.3/x*x)
plt.plot(x,-4/x*x)
plt.plot(x,5/x*x)
plt.plot(x,-6/x*x)
plt.plot(x,8/x*x)
plt.plot(x,0*x)
plt.xlim(0,900)
plt.grid(True)
plt.legend()
plt.show()
