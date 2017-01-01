package po;

import java.util.List;

import vo.StrategyRecInf;

public class StrategyRecInfString {
	public String sid;
	public String name;
	public double p_change;  //(7,5,3,1)
	public String recinf;
	
	public StrategyRecInfString() {
		super();
		this.sid = "";
		this.name = "";
		this.p_change = 0;
		this.recinf = "";
	}
	public StrategyRecInfString(String sid, String name, double p_change,
			List<StrategyRecInf> predictlist) {
		super();
		this.sid = sid;
		this.name = name;
		this.p_change = p_change;
		this.recinf = predictlist.toString();
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
	public String getRecinf() {
		return recinf;
	}
	public void setRecinf(String predictlist) {
		this.recinf = predictlist;
	}
	@Override
	public String toString() {
		return "StrategyRecStocks [sid=" + sid + ", name=" + name
				+ ", p_change=" + p_change + ", recinf=" + recinf + "]";
	}
}
