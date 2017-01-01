package vo;

public class Scale {
	public String siid;
	/**0~100的一个数*/
	public double percent;
	
	public Scale(String siid) {
		super();
		this.siid = siid;
		this.percent = 100;
	}
	public Scale(String siid, double percent) {
		super();
		this.siid = siid;
		this.percent = percent;
	}
	public String getSiid() {
		return siid;
	}
	public void setSiid(String siid) {
		this.siid = siid;
	}
	public double getPercent() {
		return percent;
	}
	public void setPercent(double percent) {
		this.percent = percent;
	}
	@Override
	public String toString() {
		return "Scale [siid=" + siid + ", percent=" + percent + "]";
	}
}
