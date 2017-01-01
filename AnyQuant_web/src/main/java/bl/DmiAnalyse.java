package bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import mapper.HistoryDataMapper;
import mapper.QuotaDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.QuotaData;
import pr.QuotaDataPr;
import tool.CrossResult;
import tool.CrossTool;
import tool.MMSTool;
import tool.TrendFlag;
import tool.TrendPoint;
import tool.TrendTool;
import tool.UpLowTool;
import vo.QuotaAnalysis;

public class DmiAnalyse extends QuotaAnalyse{
	private HistoryDataMapper historyDataMapper;
	private double deviation;
	
	public DmiAnalyse(String siid)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		analysis.message="dmi: ";
	}
	public DmiAnalyse(String type,String siid)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		analysis.message="dmi: ";
	}
	public DmiAnalyse(String siid,double deviation)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="dmi: ";
	}
	public DmiAnalyse(String type,String siid,double deviation)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="dmi: ";
	}
	public DmiAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		analysis.message="dmi: ";
	}
	public DmiAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		analysis.message="dmi: ";
	}
	public DmiAnalyse(String siid,Date enddate,double deviation)
	{
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="dmi: ";
	}
	public DmiAnalyse(String type,String siid,Date enddate,double deviation)
	{
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=deviation;
		analysis.message="dmi: ";
	}
	/**评分标准：用一段时间的趋势和交叉判断买卖情况
	 * 多空指标
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(enddate);
		datePack.setNum(5);
		QuotaData quotaData_new=quotaDataMapper.selectQuotaData_latest(datePack);
		datePack.setDate1(quotaData_new.getDate());
		List<Double> dihlist5=quotaDataMapper.selectDih_num_date(datePack);
		List<Double> dillist5=quotaDataMapper.selectDil_num_date(datePack);
		List<Double> closelist5=historyDataMapper.selectHistoryDataClose_num_date(datePack);
		List<Double> adxlist5=quotaDataMapper.selectAdx_num_date(datePack);
		List<Double> adxrlist5=quotaDataMapper.selectAdxr_num_date(datePack);
		
		List<Double> closeincrease5=new ArrayList<Double>();
		List<Double> dihincrease5=new ArrayList<Double>();
		List<Double> dilincrease5=new ArrayList<Double>();
		List<Double> adxincrease5=new ArrayList<Double>();
		List<Double> adxrincrease5=new ArrayList<Double>();
		List<TrendPoint> closetrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> dihtrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> diltrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> adxtrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> adxrtrend5=new ArrayList<TrendPoint>();
		
		closetrend5.add(new TrendPoint(0,closelist5.get(0)));
		dihtrend5.add(new TrendPoint(0,dihlist5.get(0)));
		diltrend5.add(new TrendPoint(0,dillist5.get(0)));
		adxtrend5.add(new TrendPoint(0,dillist5.get(0)));
		adxrtrend5.add(new TrendPoint(0,dillist5.get(0)));
		for(int i=1;i<closelist5.size();i++)
		{
			closeincrease5.add(closelist5.get(i)-closelist5.get(i-1));
			closetrend5.add(new TrendPoint(i,closelist5.get(i)));
			dihincrease5.add(dihlist5.get(i)-dihlist5.get(i-1));
			dihtrend5.add(new TrendPoint(i,dihlist5.get(i)));
			dilincrease5.add(dillist5.get(i)-dillist5.get(i-1));
			diltrend5.add(new TrendPoint(i,dillist5.get(i)));
			adxincrease5.add(adxlist5.get(i)-adxlist5.get(i-1));
			adxtrend5.add(new TrendPoint(i,adxlist5.get(i)));
			adxrincrease5.add(adxrlist5.get(i)-adxrlist5.get(i-1));
			adxrtrend5.add(new TrendPoint(i,adxrlist5.get(i)));
		}
		
		double scoreIn5=0;
		double scoreOut5=0;

		CrossResult dihl5=new CrossTool(dihlist5,dillist5).cross();
		TrendFlag closeflag5=new TrendTool(MMSTool.absmean_double(closeincrease5),closetrend5).trend();
		TrendFlag dihflag5=new TrendTool(MMSTool.absmean_double(dihincrease5),dihtrend5).trend();
		TrendFlag dilflag5=new TrendTool(MMSTool.absmean_double(dilincrease5),diltrend5).trend();
		TrendFlag adxflag5=new TrendTool(MMSTool.absmean_double(adxincrease5),adxtrend5).trend();
		TrendFlag adxrflag5=new TrendTool(MMSTool.absmean_double(adxrincrease5),adxrtrend5).trend();
		int uplowflag5=new UpLowTool(dihlist5,dillist5,deviation).uplow();
		if(uplowflag5>0)
		{
			analysis.message+="+DI在-DI上方,股票行情以上涨为主.\n";
		}
		if(uplowflag5<0)
		{
			analysis.message+="+DI在-DI下方，股票行情以下跌为主.\n";
		}

		if(closeflag5.trend>0)
		{
			if(dihl5.cross>0)
			{
				scoreIn5+=80;
				analysis.message+="股票价格上涨，+DI向上交叉-DI，建议买入.\n";
			}
			if(dihl5.cross<0)
			{
				scoreOut5+=80;
				analysis.message+="股票价格上涨，+DI向下交叉-DI，建议卖出.\n";
			}
		}

		if(dilflag5.trend>0&&dillist5.get(0)<20&&dillist5.get(dillist5.size()-1)>50)
		{
			scoreOut5=Math.max(scoreOut5+10,80);
			analysis.message+="-DI从20以下上升到50以上,股票价格很有可能会有一波中级下跌行情，建议卖出.\n";
		}
		if(dihflag5.trend>0&&dihlist5.get(0)<20&&dihlist5.get(dihlist5.size()-1)>50)
		{
			scoreIn5=Math.max(scoreIn5+10,80);
			analysis.message+="-DI从20以下上升到50以上,股票价格很有可能会有一波中级上涨行情，建议买入.\n";
		}
		if(MMSTool.mean(dillist5)<20*(1+deviation)&&MMSTool.mean(dillist5)>20*(1-deviation)
				&&MMSTool.mean(dihlist5)<20*(1+deviation)&&MMSTool.mean(dihlist5)>20*(1-deviation)
				&&Math.abs(dihflag5.trend)<Math.PI/20&&Math.abs(dilflag5.trend)<Math.PI/20)
		{
			scoreIn5=0;
			scoreOut5=0;
			analysis.message+="+DI和-DI以20为基准线上下波动时，该股票多空双方拉剧战,股票价格以箱体整理为主.\n";
		}
//		当ADX在50以上反转向下，不管股票价格是上涨还是下跌,都即将反转。
		if(adxlist5.get(0)>50&&adxlist5.get(adxlist5.size()-1)<50&&adxflag5.trend<0)
		{
			if(closeflag5.trend>0)
			{
				scoreOut5=Math.min(Math.max(scoreOut5+10,80),100);
				analysis.message+="股票价格上涨，但ADX从50以上反转向下，即将反转，建议卖出.\n";
			}
			if(closeflag5.trend<0)
			{
				scoreIn5=Math.min(Math.max(scoreIn5+10,80),100);
				analysis.message+="股票价格下跌，但ADX从50以上反转向下，即将反转，建议买入.\n";
			}
		}

		CrossResult crossxr=new CrossTool(adxlist5,adxrlist5).cross();
		if(crossxr.cross<0&&closeflag5.trend>0)
		{
			scoreOut5=Math.min(Math.max(scoreOut5+10,80),100);
			analysis.message+="ADX从上面下穿ADXR时形成死叉，股票上涨行情将终结.\n";
		}
		if(Math.abs(adxflag5.trend)<Math.PI/20&&Math.abs(adxrflag5.trend)<Math.PI/20
				&&Math.abs(MMSTool.mean(adxlist5)-20)/20<=deviation
				&&Math.abs(MMSTool.mean(adxrlist5)-20)/20<=deviation)
		{
			scoreIn5=Math.max(0,scoreIn5-10);
			analysis.message+="ADX和ADXR下行至20左右并交织波动，股票将横盘整理,没有上涨行情.\n";
		}
//		当ADX从下面上穿ADXR形成金叉,预示着股票将出现一波上涨行情，ADX的ADXR运行至50以上时，将可能产生一轮中级以上的行情,
//		ADX和ADXR上行至80以上时，那么市场将很有可能是翻倍以上的大行情。
		if(crossxr.cross>0)
		{
			scoreIn5=Math.min(Math.max(scoreIn5+10,80),100);
			analysis.message+="ADX从下面上穿ADXR形成金叉,预示着股票将出现一波上涨行情.\n";
		}
		if(adxlist5.get(adxlist5.size()-1)>=80&&adxrlist5.get(adxlist5.size()-1)>=80)
		{
			scoreIn5=Math.min(100,Math.max(scoreIn5+20,60));
			analysis.message+="ADX和ADXR上行至80以上，市场将很有可能是翻倍以上的大行情.\n";
		}
		
		analysis.scoreIn=DataHelper.controldigit(scoreIn5);
		analysis.scoreOut=DataHelper.controldigit(scoreOut5);
		return analysis;
	}
}
