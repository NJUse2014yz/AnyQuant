package vo;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import po.StrategyRecInfString;

public class StrategyRecStocks{
	public String sid;
	public String name;
	public double p_change;  //(7,5,3,1)
	public List<StrategyRecInf> predictList;
	public StrategyRecStocks() {
		super();
		this.sid = "";
		this.name = "";
		this.p_change = 0;
		this.predictList = null;
	}
	public StrategyRecStocks(String sid, String name, double p_change,
			List<StrategyRecInf> predictlist) {
		this.sid = sid;
		this.name = name;
		this.p_change = p_change;
		this.predictList = predictlist;
	}
	public StrategyRecStocks(StrategyRecInfString strategyRecInfString) {
		this.sid = strategyRecInfString.sid;
		this.name = strategyRecInfString.name;
		this.p_change = strategyRecInfString.p_change;
		String stategyRecInf[]=strategyRecInfString.recinf.substring(1,strategyRecInfString.recinf.length()-1).split(", ");
		this.predictList = new ArrayList<StrategyRecInf>();
		for(int i=0;i<stategyRecInf.length;i++)
		{
			String statistics[]=stategyRecInf[i].split(" ");
			predictList.add(new StrategyRecInf(Integer.parseInt(statistics[0]),
					Double.parseDouble(statistics[1]),
					Double.parseDouble(statistics[2]),
					Double.parseDouble(statistics[3]),
					Double.parseDouble(statistics[4])));
		}
	}
	
	public String getSid() {
		return sid;
	}
	public void setSid(String sid) {
		this.sid = sid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getP_change() {
		return p_change;
	}
	public void setP_change(double p_change) {
		this.p_change = p_change;
	}
	public List<StrategyRecInf> getPredictList() {
		return predictList;
	}
	public void setPredictList(List<StrategyRecInf> predictlist) {
		this.predictList = predictlist;
	}
	@Override
	public String toString() {
		return "StrategyRecStocks [sid=" + sid + ", name=" + name
				+ ", p_change=" + p_change + ", predictlist=" + predictList
				+ "]";
	}
}
