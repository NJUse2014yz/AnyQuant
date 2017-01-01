package vo;

import java.sql.Date;
import java.sql.Time;

public class RealTimeInf {

	/** 涨跌百分比 */
	public double increPer;
	/** 涨跌额 */
	public double increase;
	/** 股票名称 */
	public String name;
	/** 今日开盘价 */
	public double todayStartPri;
	/** 昨日收盘价 */
	public double yestodEndPri;
	/** 当前价格 */
	public double nowPri;
	/** 今日最高价 */
	public double todayMax;
	/** 今日最低价 */
	public double todayMin;
	/** 日期 */
	public String date;
	/** 时间 */
	public String time;

	public RealTimeInf() {
	}
}
