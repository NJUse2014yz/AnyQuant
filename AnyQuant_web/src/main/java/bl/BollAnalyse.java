package bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.HistoryData;
import po.QuotaData;
import pr.HistoryDataPr;
import pr.QuotaDataPr;
import tool.CrossResult;
import tool.CrossTool;
import tool.MMSTool;
import tool.TrendFlag;
import tool.TrendPoint;
import tool.TrendTool;
import vo.QuotaAnalysis;
import mapper.HistoryDataMapper;
import mapper.QuotaDataMapper;

public class BollAnalyse extends QuotaAnalyse{
	private HistoryDataMapper historyDataMapper;
	private double deviation;
	
	public BollAnalyse(String siid)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		deviation=0.05;
		analysis.message="boll: ";
	}
	public BollAnalyse(String type,String siid)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		deviation=0.05;
		analysis.message="boll: ";
	}
	public BollAnalyse(String siid,double deviation)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="boll: ";
	}
	public BollAnalyse(String type,String siid,double deviation)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="boll: ";
	}
	public BollAnalyse(String siid,Date enddate)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		deviation=0.05;
		analysis.message="boll: ";
	}
	public BollAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		deviation=0.05;
		analysis.message="boll: ";
	}
	public BollAnalyse(String siid,Date enddate,double deviation)
	{
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="boll: ";
	}
	public BollAnalyse(String type,String siid,Date enddate,double deviation)
	{
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="boll: ";
	}
	/**评分标准：以5日来的趋势和交叉情况判断买卖情况
	 * 一个卖/买点加分
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		double scoreIn5=0;
		double scoreOut5=0;
		
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(enddate);
		datePack.setNum(5);
		QuotaData quotaData_new=quotaDataMapper.selectQuotaData_latest(datePack);
		datePack.setDate1(quotaData_new.getDate());
		List<Double> boll1list5=quotaDataMapper.selectBoll1_num_date(datePack);
		List<Double> boll2list5=quotaDataMapper.selectBoll2_num_date(datePack);
		List<Double> boll3list5=quotaDataMapper.selectBoll3_num_date(datePack);
		List<Double> closelist5=historyDataMapper.selectHistoryDataClose_num_date(datePack);
		List<Long> volumelist5=historyDataMapper.selectHistoryDataVolume_num_date(datePack);
		
		List<TrendPoint> closetrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> volumetrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> boll1trend5=new ArrayList<TrendPoint>();
		List<TrendPoint> boll2trend5=new ArrayList<TrendPoint>();
		List<TrendPoint> boll3trend5=new ArrayList<TrendPoint>();
		
		List<Double> closeincrease5=new ArrayList<Double>();
		List<Double> volumeincrease5=new ArrayList<Double>();
		List<Double> boll1increase5=new ArrayList<Double>();
		List<Double> boll2increase5=new ArrayList<Double>();
		List<Double> boll3increase5=new ArrayList<Double>();
		
		closetrend5.add(new TrendPoint(0,closelist5.get(0)));
		volumetrend5.add(new TrendPoint(0,volumelist5.get(0)));
		boll1trend5.add(new TrendPoint(0,boll1list5.get(0)));
		boll2trend5.add(new TrendPoint(0,boll2list5.get(0)));
		boll3trend5.add(new TrendPoint(0,boll3list5.get(0)));
		for(int i=1;i<boll1list5.size();i++)
		{
			closetrend5.add(new TrendPoint(i,closelist5.get(i)));
			boll1trend5.add(new TrendPoint(i,boll1list5.get(i)));
			boll2trend5.add(new TrendPoint(i,boll2list5.get(i)));
			boll3trend5.add(new TrendPoint(i,boll3list5.get(i)));
			volumetrend5.add(new TrendPoint(i,volumelist5.get(i)));
			closeincrease5.add(closelist5.get(i)-closelist5.get(i-1));
			boll3increase5.add(boll3list5.get(i)-boll3list5.get(i-1));
			volumeincrease5.add((double) (volumelist5.get(i)-volumelist5.get(i-1)));
		}
		
		TrendPoint min=MMSTool.min_trendpoint(closetrend5);
		TrendPoint max=MMSTool.max_trendpoint(closetrend5);

		double closeStandard=MMSTool.absmean_double(closeincrease5);
		TrendFlag closeflag5=new TrendTool(closeStandard,closetrend5).trend();
		TrendFlag boll1flag5=new TrendTool(MMSTool.absmean_double(boll1increase5),boll1trend5).trend();
		TrendFlag boll2flag5=new TrendTool(MMSTool.absmean_double(boll2increase5),boll2trend5).trend();
		TrendFlag boll3flag5=new TrendTool(MMSTool.absmean_double(boll3increase5),boll3trend5).trend();
		TrendFlag volumeflag5=new TrendTool(MMSTool.absmean_double(volumeincrease5),volumetrend5).trend();
/*下轨走平或上涨时，股价下跌到下轨获得支撑为买入信号，
	成交量温和放大，看涨信号更可靠
	如果股价突破中轨，上涨趋势形成,由下轨支撑到突破中轨所耗费的时间越短，看涨信号越强烈*/
		if(boll3flag5.trend>=0&&min.index>=1&&min.index<=closelist5.size()-1)
		{
			TrendFlag closeflag5_front=new TrendTool(closeStandard,closetrend5.subList(0, min.index+1)).trend();
			TrendFlag closeflag5_back=new TrendTool(closeStandard,closetrend5.subList(min.index,closetrend5.size())).trend();
			if(closeflag5_front.trend<0&&closeflag5_back.trend<=0&&min.value>=boll3list5.get(min.index)*(1-deviation))
			{
				//股价跌到下轨获得支撑
				scoreIn5+=60;
				analysis.message+="Boll下轨走平或上涨时股价跌到下轨获得支撑,建议买入.\n";
				if(volumeflag5.trend>0&&volumeflag5.trend<Math.PI/6)
				{
					//成交量温和放大
					scoreIn5+=10;
					analysis.message+="成交量温和放大，买入信号更可信.\n";
				}
				CrossResult crossResult=new CrossTool(closelist5,boll2list5).cross();
				if(crossResult.cross>0&&crossResult.crosspoint-min.index!=0)
				{
					analysis.message+="股价突破中轨，上涨趋势形成，买入信号更可信.\n";
					scoreIn5+=1.0/(crossResult.crosspoint-min.index)*20;
				}
			}
		}
/*下跌到下轨时，股价沿下轨一起下跌应继续观望*/
		if(closeflag5.trend<0&&boll3flag5.trend<0&&closelist5.get(closelist5.size()-1)<boll3list5.get(boll3list5.size()-1)*(1+deviation))
		{
			scoreIn5=0;
			analysis.message+="下跌到下轨时，股价沿下轨一起下跌应继续观望.\n";
		}
/*股价在上轨遇阻回调，轻仓观望，股价跌破中轨，下跌趋势已经形成，尽快卖出，间隔时间越短，看跌信号越强烈
	当股价遇阻回调时，如果KDJ指标中K和D在高位出现死叉，是对看跌信号的确认，尽快卖出*/
		if(max.index>=1&&max.index<=closelist5.size()-1)
		{
			TrendFlag closeflag5_front=new TrendTool(closeStandard,closetrend5.subList(0, min.index+1)).trend();
			TrendFlag closeflag5_back=new TrendTool(closeStandard,closetrend5.subList(min.index,closetrend5.size())).trend();
			if(closeflag5_front.trend>0&&closeflag5_back.trend<=0&&max.value<=boll1list5.get(min.index)*(1+deviation))
			{
				//股价在上轨遇阻回调
				scoreOut5+=60;
				analysis.message+="股价在上轨遇阻回调，轻仓观望.\n";
				if(volumeflag5.trend<0&&volumeflag5.trend>-Math.PI/6)
				{
					//成交量温和减少
					scoreOut5+=10;
					analysis.message+="成交量温和减少，卖出信号更可信.\n";
				}
				CrossResult crossResult=new CrossTool(closelist5,boll2list5).cross();
				if(crossResult.cross<0&&crossResult.crosspoint-max.index!=0)
				{
					analysis.message+="股价跌破中轨，下跌趋势已经形成，尽快卖出.\n";
					scoreIn5+=1.0/(crossResult.crosspoint-max.index)*20;
				}
			}
		}
/*如股价上涨到上轨附近没有遇到阻力下跌，而是沿上轨上涨，是上涨行情还将继续的信号，可放心持股*/
		if(closeflag5.trend>0&&boll1flag5.trend>0&&closelist5.get(closelist5.size()-1)>boll1list5.get(boll1list5.size()-1)*(1-deviation))
		{
			scoreOut5=0;
			analysis.message+="股价上涨到上轨附近没有遇到阻力下跌，而是沿上轨上涨，是上涨行情还将继续的信号，可放心持股.\n";
		}
/*股价突破上轨表示进入短暂狂热状态，当跌破上轨时，有超买回调的危险，尽快卖出
	如果跌回轨道内后仍沿着上轨继续上涨，则未来还可能有一定上涨空间，可以轻仓观望
	当股价突破上轨，KDJ中J进入100以上区域，如果J重新跌落到100以下时，形成另一个卖点*/
		if(closelist5.get(closelist5.size()-1)>boll1list5.get(boll1list5.size()-1))
		{
			scoreOut5=80;
			analysis.message+="股价跌破上轨，有超买回调的危险，尽快卖出.\n";
		}
		analysis.scoreIn=DataHelper.controldigit(scoreIn5);
		analysis.scoreOut=DataHelper.controldigit(scoreOut5);
		return analysis;
	}

}
