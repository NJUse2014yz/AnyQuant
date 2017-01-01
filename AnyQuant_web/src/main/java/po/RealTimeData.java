package po;

import java.sql.Date;
import java.sql.Time;

import tool.DateExchangeTool;

/**
 * @for 实时数据
 * @author YU Fan
 * @date 2016年5月9日
 */
public class RealTimeData {
	protected String gid;
	/**涨跌百分比*/
	protected double increPer;
	/**涨跌额*/
	protected double increase;
	/**股票名称*/
	protected String name;
	/**今日开盘价*/
	protected double todayStartPri;
	/**昨日收盘价*/
	protected double yestodEndPri;
	/**当前价格*/
	protected double nowPri;
	/**今日最高价*/
	protected double todayMax;
	/**今日最低价*/
	protected double todayMin;
	/**竞买价*/
	protected double competitivePri;
	/**竞卖价*/
	protected double reservePri;
	/**成交量*/
	protected long traNumber;
	/**成交金额*/
	protected double traAmount;
	/**买一*/
	protected long buyOne;
	/**买一报价*/
	protected double buyOnePri;
	/**买二*/
	protected long buyTwo;
	/**买二报价*/
	protected double buyTwoPri;
	/**买三*/
	protected long buyThree;
	/**买三报价*/
	protected double buyThreePri;
	/**买四*/
	protected long buyFour;
	/**买四报价*/
	protected double buyFourPri;
	/**买五*/
	protected long buyFive;
	/**买五报价*/
	protected double buyFivePri;
	/**卖一*/
	protected long sellOne;
	/**卖一报价*/
	protected double sellOnePri;
	/**卖二*/
	protected long sellTwo;
	/**卖二报价*/
	protected double sellTwoPri;
	/**卖三*/
	protected long sellThree;
	/**卖三报价*/
	protected double sellThreePri;
	/**卖四*/
	protected long sellFour;
	/**卖四报价*/
	protected double sellFourPri;
	/**卖五*/
	protected long sellFive;
	/**卖五报价*/
	protected double sellFivePri;
	/**日期*/
	protected Date date;
	/**时间*/
	protected Time time;
	
	public RealTimeData(){
		super();
		
	}
	public RealTimeData(String anyValue)
	{
		super();
		this.gid="";
		this.increPer = 0;
		this.increase = 0;
		this.name = "南大软院";
		this.todayStartPri = 0;
		this.yestodEndPri = 0;
		this.nowPri = 0;
		this.todayMax = 0;
		this.todayMin = 0;
		this.competitivePri = 0;
		this.reservePri = 0;
		this.traNumber = 0;
		this.traAmount = 0;
		this.buyOne = 0;
		this.buyOnePri = 0;
		this.buyTwo = 0;
		this.buyTwoPri = 0;
		this.buyThree = 0;
		this.buyThreePri = 0;
		this.buyFour = 0;
		this.buyFourPri = 0;
		this.buyFive = 0;
		this.buyFivePri = 0;
		this.sellOne = 0;
		this.sellOnePri = 0;
		this.sellTwo = 0;
		this.sellTwoPri = 0;
		this.sellThree = 0;
		this.sellThreePri = 0;
		this.sellFour = 0;
		this.sellFourPri = 0;
		this.sellFive = 0;
		this.sellFivePri = 0;
		this.date = new Date(116,4,9);
		this.time = new Time(10,0,0);
	}
	public RealTimeData(RealTimeDataString realTimeDataString)
	{
		super();
		this.gid=realTimeDataString.gid;
		this.increPer = Double.parseDouble(realTimeDataString.increPer);
		this.increase = Double.parseDouble(realTimeDataString.increase);
		this.name = realTimeDataString.name;
		this.todayStartPri = Double.parseDouble(realTimeDataString.todayStartPri);
		this.yestodEndPri = Double.parseDouble(realTimeDataString.yestodEndPri);
		this.nowPri = Double.parseDouble(realTimeDataString.nowPri);
		this.todayMax = Double.parseDouble(realTimeDataString.todayMax);
		this.todayMin = Double.parseDouble(realTimeDataString.todayMin);
		this.competitivePri = Double.parseDouble(realTimeDataString.competitivePri);
		this.reservePri = Double.parseDouble(realTimeDataString.reservePri);
		this.traNumber = Long.parseLong(realTimeDataString.traNumber);
		this.traAmount = Double.parseDouble(realTimeDataString.traAmount);
		this.buyOne = Long.parseLong(realTimeDataString.buyOne);
		this.buyOnePri = Double.parseDouble(realTimeDataString.buyOnePri);
		this.buyTwo = Long.parseLong(realTimeDataString.buyTwo);
		this.buyTwoPri = Double.parseDouble(realTimeDataString.buyTwoPri);
		this.buyThree = Long.parseLong(realTimeDataString.buyThree);
		this.buyThreePri = Double.parseDouble(realTimeDataString.buyThreePri);
		this.buyFour = Long.parseLong(realTimeDataString.buyFour);
		this.buyFourPri = Double.parseDouble(realTimeDataString.buyFourPri);
		this.buyFive = Long.parseLong(realTimeDataString.buyFive);
		this.buyFivePri = Double.parseDouble(realTimeDataString.buyFivePri);
		this.sellOne = Long.parseLong(realTimeDataString.sellOne);
		this.sellOnePri = Double.parseDouble(realTimeDataString.sellOnePri);
		this.sellTwo = Long.parseLong(realTimeDataString.sellTwo);
		this.sellTwoPri = Double.parseDouble(realTimeDataString.sellTwoPri);
		this.sellThree = Long.parseLong(realTimeDataString.sellThree);
		this.sellThreePri = Double.parseDouble(realTimeDataString.sellThreePri);
		this.sellFour = Long.parseLong(realTimeDataString.sellFour);
		this.sellFourPri = Double.parseDouble(realTimeDataString.sellFourPri);
		this.sellFive = Long.parseLong(realTimeDataString.sellFive);
		this.sellFivePri = Double.parseDouble(realTimeDataString.sellFivePri);
		this.date = DateExchangeTool.stringToSqlDate(realTimeDataString.date);
		this.time = DateExchangeTool.stringToSqlTime(realTimeDataString.time);
	}
	public RealTimeData(String gid, double increPer, double increase,
			String name, double todayStartPri, double yestodEndPri,
			double nowPri, double todayMax, double todayMin,
			double competitivePri, double reservePri, long traNumber,
			double traAmount, long buyOne, double buyOnePri, long buyTwo,
			double buyTwoPri, long buyThree, double buyThreePri, long buyFour,
			double buyFourPri, long buyFive, double buyFivePri, long sellOne,
			double sellOnePri, long sellTwo, double sellTwoPri, long sellThree,
			double sellThreePri, long sellFour, double sellFourPri,
			long sellFive, double sellFivePri, Date date, Time time) {
		super();
		this.gid = gid;
		this.increPer = increPer;
		this.increase = increase;
		this.name = name;
		this.todayStartPri = todayStartPri;
		this.yestodEndPri = yestodEndPri;
		this.nowPri = nowPri;
		this.todayMax = todayMax;
		this.todayMin = todayMin;
		this.competitivePri = competitivePri;
		this.reservePri = reservePri;
		this.traNumber = traNumber;
		this.traAmount = traAmount;
		this.buyOne = buyOne;
		this.buyOnePri = buyOnePri;
		this.buyTwo = buyTwo;
		this.buyTwoPri = buyTwoPri;
		this.buyThree = buyThree;
		this.buyThreePri = buyThreePri;
		this.buyFour = buyFour;
		this.buyFourPri = buyFourPri;
		this.buyFive = buyFive;
		this.buyFivePri = buyFivePri;
		this.sellOne = sellOne;
		this.sellOnePri = sellOnePri;
		this.sellTwo = sellTwo;
		this.sellTwoPri = sellTwoPri;
		this.sellThree = sellThree;
		this.sellThreePri = sellThreePri;
		this.sellFour = sellFour;
		this.sellFourPri = sellFourPri;
		this.sellFive = sellFive;
		this.sellFivePri = sellFivePri;
		this.date = date;
		this.time = time;
	}
	public String getGid() {
		return gid;
	}
	public void setGid(String gid) {
		this.gid = gid;
	}
	public double getIncrePer() {
		return increPer;
	}
	public void setIncrePer(double increPer) {
		this.increPer = increPer;
	}
	public double getIncrease() {
		return increase;
	}
	public void setIncrease(double increase) {
		this.increase = increase;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public double getTodayStartPri() {
		return todayStartPri;
	}
	public void setTodayStartPri(double todayStartPri) {
		this.todayStartPri = todayStartPri;
	}
	public double getYestodEndPri() {
		return yestodEndPri;
	}
	public void setYestodEndPri(double yestodEndPri) {
		this.yestodEndPri = yestodEndPri;
	}
	public double getNowPri() {
		return nowPri;
	}
	public void setNowPri(double nowPri) {
		this.nowPri = nowPri;
	}
	public double getTodayMax() {
		return todayMax;
	}
	public void setTodayMax(double todayMax) {
		this.todayMax = todayMax;
	}
	public double getTodayMin() {
		return todayMin;
	}
	public void setTodayMin(double todayMin) {
		this.todayMin = todayMin;
	}
	public double getCompetitivePri() {
		return competitivePri;
	}
	public void setCompetitivePri(double competitivePri) {
		this.competitivePri = competitivePri;
	}
	public double getReservePri() {
		return reservePri;
	}
	public void setReservePri(double reservePri) {
		this.reservePri = reservePri;
	}
	public long getTraNumber() {
		return traNumber;
	}
	public void setTraNumber(long traNumber) {
		this.traNumber = traNumber;
	}
	public double getTraAmount() {
		return traAmount;
	}
	public void setTraAmount(double traAmount) {
		this.traAmount = traAmount;
	}
	public long getBuyOne() {
		return buyOne;
	}
	public void setBuyOne(long buyOne) {
		this.buyOne = buyOne;
	}
	public double getBuyOnePri() {
		return buyOnePri;
	}
	public void setBuyOnePri(double buyOnePri) {
		this.buyOnePri = buyOnePri;
	}
	public long getBuyTwo() {
		return buyTwo;
	}
	public void setBuyTwo(long buyTwo) {
		this.buyTwo = buyTwo;
	}
	public double getBuyTwoPri() {
		return buyTwoPri;
	}
	public void setBuyTwoPri(double buyTwoPri) {
		this.buyTwoPri = buyTwoPri;
	}
	public long getBuyThree() {
		return buyThree;
	}
	public void setBuyThree(long buyThree) {
		this.buyThree = buyThree;
	}
	public double getBuyThreePri() {
		return buyThreePri;
	}
	public void setBuyThreePri(double buyThreePri) {
		this.buyThreePri = buyThreePri;
	}
	public long getBuyFour() {
		return buyFour;
	}
	public void setBuyFour(long buyFour) {
		this.buyFour = buyFour;
	}
	public double getBuyFourPri() {
		return buyFourPri;
	}
	public void setBuyFourPri(double buyFourPri) {
		this.buyFourPri = buyFourPri;
	}
	public long getBuyFive() {
		return buyFive;
	}
	public void setBuyFive(long buyFive) {
		this.buyFive = buyFive;
	}
	public double getBuyFivePri() {
		return buyFivePri;
	}
	public void setBuyFivePri(double buyFivePri) {
		this.buyFivePri = buyFivePri;
	}
	public long getSellOne() {
		return sellOne;
	}
	public void setSellOne(long sellOne) {
		this.sellOne = sellOne;
	}
	public double getSellOnePri() {
		return sellOnePri;
	}
	public void setSellOnePri(double sellOnePri) {
		this.sellOnePri = sellOnePri;
	}
	public long getSellTwo() {
		return sellTwo;
	}
	public void setSellTwo(long sellTwo) {
		this.sellTwo = sellTwo;
	}
	public double getSellTwoPri() {
		return sellTwoPri;
	}
	public void setSellTwoPri(double sellTwoPri) {
		this.sellTwoPri = sellTwoPri;
	}
	public long getSellThree() {
		return sellThree;
	}
	public void setSellThree(long sellThree) {
		this.sellThree = sellThree;
	}
	public double getSellThreePri() {
		return sellThreePri;
	}
	public void setSellThreePri(double sellThreePri) {
		this.sellThreePri = sellThreePri;
	}
	public long getSellFour() {
		return sellFour;
	}
	public void setSellFour(long sellFour) {
		this.sellFour = sellFour;
	}
	public double getSellFourPri() {
		return sellFourPri;
	}
	public void setSellFourPri(double sellFourPri) {
		this.sellFourPri = sellFourPri;
	}
	public long getSellFive() {
		return sellFive;
	}
	public void setSellFive(long sellFive) {
		this.sellFive = sellFive;
	}
	public double getSellFivePri() {
		return sellFivePri;
	}
	public void setSellFivePri(double sellFivePri) {
		this.sellFivePri = sellFivePri;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public Time getTime() {
		return time;
	}
	public void setTime(Time time) {
		this.time = time;
	}
	@Override
	public String toString() {
		return "RealTimeData [gid=" + gid + ", increPer=" + increPer
				+ ", increase=" + increase + ", name=" + name
				+ ", todayStartPri=" + todayStartPri + ", yestodEndPri="
				+ yestodEndPri + ", nowPri=" + nowPri + ", todayMax="
				+ todayMax + ", todayMin=" + todayMin + ", competitivePri="
				+ competitivePri + ", reservePri=" + reservePri
				+ ", traNumber=" + traNumber + ", traAmount=" + traAmount
				+ ", buyOne=" + buyOne + ", buyOnePri=" + buyOnePri
				+ ", buyTwo=" + buyTwo + ", buyTwoPri=" + buyTwoPri
				+ ", buyThree=" + buyThree + ", buyThreePri=" + buyThreePri
				+ ", buyFour=" + buyFour + ", buyFourPri=" + buyFourPri
				+ ", buyFive=" + buyFive + ", buyFivePri=" + buyFivePri
				+ ", sellOne=" + sellOne + ", sellOnePri=" + sellOnePri
				+ ", sellTwo=" + sellTwo + ", sellTwoPri=" + sellTwoPri
				+ ", sellThree=" + sellThree + ", sellThreePri=" + sellThreePri
				+ ", sellFour=" + sellFour + ", sellFourPri=" + sellFourPri
				+ ", sellFive=" + sellFive + ", sellFivePri=" + sellFivePri
				+ ", date=" + DateExchangeTool.dateToString1(date) 
				+ ", time=" + DateExchangeTool.timeToString(time) + "]";
	}
}
