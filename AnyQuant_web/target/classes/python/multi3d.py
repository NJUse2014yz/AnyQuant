import numpy as np
from matplotlib import cm
import matplotlib.pyplot as plt
import mpl_toolkits.mplot3d
import math

slowspeed=np.pi/6

# x=np.arange(-np.pi/2,-np.pi/6,0.05)
# y=np.arange(np.pi/6,np.pi/2,0.05)
# y=y[::-1]
# x,y=np.meshgrid(x,y)
# p=(x-y)/2
# z=(np.sqrt((1-(p-(-np.pi/2))**2/(-slowspeed-(-np.pi/2))**2)*1600)+60)/100

a=np.arange(-np.pi/6,np.pi/6,0.05)
b=np.arange(-np.pi/6,np.pi/6,0.05)
b=b[::-1]
q=(a-b)/2
c=-np.sqrt((1-(q-(slowspeed))**2/(slowspeed*2)**2)*3600)+60

ax=plt.subplot(111,projection='3d')
# ax.plot_surface(x,y,z,rstride=4,cstride=4,cmap=plt.cm.coolwarm)
ax.plot_surface(a,b,c,rstride=4,cstride=4,cmap=plt.cm.coolwarm)
# cset = ax.contourf(x, y, z, zdir='x', offset=-1.6, cmap=cm.coolwarm)
# cset = ax.contourf(x, y, z, zdir='y', offset=1.6, cmap=cm.coolwarm)
# cset = ax.contourf(x, y, z, zdir='z', offset=0.6, cmap=cm.coolwarm)

ax.set_xlabel('x')
ax.set_ylabel('y')
ax.set_zlabel('z')

# ax.set_xlim(-np.pi/2,np.pi/2)
# ax.set_ylim(-np.pi/2,np.pi/2)
ax.set_xlim(-np.pi/6,np.pi/6)
ax.set_ylim(np.pi/6,-np.pi/6)

plt.show()