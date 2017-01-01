package po;

import java.sql.Date;

public class BlockHistoryData {
	/**股票代码*/
	private String sid;
	private String name;
	private String block;
	/**编号*/
	private int id;
	/**日期*/
	private Date date;
	/**周*/
	private int weekid;
	/**月*/
	private int monthid;
	/**年*/
	private int yearid;
	/**开盘价*/
	private double open;
	/**收盘价*/
	private double close;
	/**涨跌额*/
	private double increase;
	/**涨跌百分比*/
	private double incrPer;
	/**最低价*/
	private double low;
	/**最高价*/
	private double high;
	/**成交量*/
	private long volume;
	/**成交额*/
	private double amount;
	/**换手率*/
	private double turnover;
	
	public BlockHistoryData() {
		this.sid = "";
		this.name="";
		this.block="";
		this.id = 0;
		this.date = null;
		this.weekid = 0;
		this.monthid = 0;
		this.yearid = 0;
		this.open = 0;
		this.close = 0;
		this.increase = 0;
		this.incrPer = 0;
		this.low = 0;
		this.high = 0;
		this.volume = 0;
		this.amount = 0;
		this.turnover = 0;
	}

	public BlockHistoryData(String sid, String name, String block, int id,
			Date date, int weekid, int monthid, int yearid, double open,
			double close, double increase, double incrPer, double low,
			double high, long volume, double amount, double turnover) {
		super();
		this.sid = sid;
		this.name = name;
		this.block = block;
		this.id = id;
		this.date = date;
		this.weekid = weekid;
		this.monthid = monthid;
		this.yearid = yearid;
		this.open = open;
		this.close = close;
		this.increase = increase;
		this.incrPer = incrPer;
		this.low = low;
		this.high = high;
		this.volume = volume;
		this.amount = amount;
		this.turnover = turnover;
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

	public String getBlock() {
		return block;
	}

	public void setBlock(String block) {
		this.block = block;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getWeekid() {
		return weekid;
	}

	public void setWeekid(int weekid) {
		this.weekid = weekid;
	}

	public int getMonthid() {
		return monthid;
	}

	public void setMonthid(int monthid) {
		this.monthid = monthid;
	}

	public int getYearid() {
		return yearid;
	}

	public void setYearid(int yearid) {
		this.yearid = yearid;
	}

	public double getOpen() {
		return open;
	}

	public void setOpen(double open) {
		this.open = open;
	}

	public double getClose() {
		return close;
	}

	public void setClose(double close) {
		this.close = close;
	}

	public double getIncrease() {
		return increase;
	}

	public void setIncrease(double increase) {
		this.increase = increase;
	}

	public double getIncrPer() {
		return incrPer;
	}

	public void setIncrPer(double incrPer) {
		this.incrPer = incrPer;
	}

	public double getLow() {
		return low;
	}

	public void setLow(double low) {
		this.low = low;
	}

	public double getHigh() {
		return high;
	}

	public void setHigh(double high) {
		this.high = high;
	}

	public long getVolume() {
		return volume;
	}

	public void setVolume(long volume) {
		this.volume = volume;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}

	public double getTurnover() {
		return turnover;
	}

	public void setTurnover(double turnover) {
		this.turnover = turnover;
	}

	@Override
	public String toString() {
		return "BlockHistoryData [sid=" + sid + ", name=" + name + ", block="
				+ block + ", id=" + id + ", date=" + date + ", weekid="
				+ weekid + ", monthid=" + monthid + ", yearid=" + yearid
				+ ", open=" + open + ", close=" + close + ", increase="
				+ increase + ", incrPer=" + incrPer + ", low=" + low
				+ ", high=" + high + ", volume=" + volume + ", amount="
				+ amount + ", turnover=" + turnover + "]";
	}
	
}
