package po;

import java.sql.Date;
import java.sql.Time;
import java.util.List;
/**
 * @for 实时数据的包装
 * @member 要用的代码 siid,要用的实时基本数据列表 list,要用的单条实时数据 或 下界 realTimeStockData1,上界 realTimeStockData2
 * @author YU Fan
 * @date 2016年5月19日
 */
public class RealTimeDataPack {
	/**要用的代码*/
	private String siid;
	/**要用的单条实时数据 或 下界*/
	private RealTimeData realTimeData1;
	/**上界*/
	private RealTimeData realTimeData2;
	/**列表*/
	private List<RealTimeData> list;
	
	public RealTimeDataPack() {
		super();
		this.siid = "";
		this.realTimeData1 = null;
		this.realTimeData2 = null;
		this.list=null;
	}
	public RealTimeDataPack(String siid, RealTimeData realTimeData1,
			RealTimeData realTimeData2, List<RealTimeData> list) {
		super();
		this.siid = siid;
		this.realTimeData1 = realTimeData1;
		this.realTimeData2 = realTimeData2;
		this.list = list;
	}
	public String getSiid() {
		return siid;
	}
	public void setSiid(String siid) {
		this.siid = siid;
	}
	public RealTimeData getRealTimeData1() {
		return realTimeData1;
	}
	public void setRealTimeData1(RealTimeData realTimeData1) {
		this.realTimeData1 = realTimeData1;
	}
	public RealTimeData getRealTimeData2() {
		return realTimeData2;
	}
	public void setRealTimeData2(RealTimeData realTimeData2) {
		this.realTimeData2 = realTimeData2;
	}
	public List<RealTimeData> getList() {
		return list;
	}
	public void setList(List<RealTimeData> list) {
		this.list = list;
	}
	@Override
	public String toString() {
		return "RealTimeDataPack [siid=" + siid + ", realTimeData1="
				+ realTimeData1 + ", realTimeData2=" + realTimeData2
				+ ", list=" + list + "]";
	}
	
}
