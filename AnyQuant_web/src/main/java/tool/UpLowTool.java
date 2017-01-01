package tool;

import java.util.List;

public class UpLowTool {
	public List<Double> activelist;
	public List<Double> passivelist;
	public double deviation;
	public UpLowTool(List<Double> activelist,List<Double> passivelist,double deviation)
	{
		this.activelist=activelist;
		this.passivelist=passivelist;
		this.deviation=deviation;
	}
	/**主动线在上方返回1，主动线在下方返回-1，波动情况返回0*/
	public int uplow()
	{
		int result=0;
		
		if(activelist.get(0)>passivelist.get(0)*(1-deviation))
		{
			result=1;
			for(int i=0;i<activelist.size();i++)
			{
				if(activelist.get(i)<passivelist.get(i)*(1-deviation))
				{
					result=0;
					return result;
				}
			}
		}

		if(activelist.get(0)<passivelist.get(0)*(1+deviation))
		{
			result=-1;
			for(int i=0;i<activelist.size();i++)
			{
				if(activelist.get(i)>passivelist.get(i)*(1+deviation))
				{
					result=0;
					return result;
				}
			}
		}
		
		return result;
	}
}
