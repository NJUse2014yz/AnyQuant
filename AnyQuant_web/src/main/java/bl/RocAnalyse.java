package bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mapper.HistoryDataMapper;
import mapper.QuotaDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.HistoryData;
import po.QuotaData;
import pr.HistoryDataPr;
import pr.QuotaDataPr;
import tool.MMSTool;
import tool.TrendFlag;
import tool.TrendPoint;
import tool.TrendTool;
import vo.QuotaAnalysis;

public class RocAnalyse extends QuotaAnalyse{
	private HistoryDataMapper historyDataMapper;
	private double deviation;
	private Date startdate;
	private double up1;
	private double down1;
	
	public RocAnalyse(String siid)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		this.startdate=new Date(0,0,1);
		this.up1=0.75;
		this.down1=0.25;
		analysis.message="roc: ";
	}
	public RocAnalyse(String type,String siid)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		this.startdate=new Date(0,0,1);
		this.up1=0.75;
		this.down1=0.25;
		analysis.message="roc: ";
	}
	public RocAnalyse(String siid,Date startdate,double deviation,
			double up1,double down1)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.startdate=startdate;
		this.deviation=deviation;
		this.up1=up1;
		this.down1=down1;
		analysis.message="roc: ";
	}
	public RocAnalyse(String type,String siid,Date startdate,double deviation,
			double up1,double down1)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.startdate=startdate;
		this.deviation=deviation;
		this.up1=up1;
		this.down1=down1;
		analysis.message="roc: ";
	}
	
	public RocAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		this.startdate=new Date(0,0,1);
		this.up1=0.75;
		this.down1=0.25;
		analysis.message="roc: ";
	}
	public RocAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.deviation=0.05;
		this.startdate=new Date(0,0,1);
		this.up1=0.75;
		this.down1=0.25;
		analysis.message="roc: ";
	}
	public RocAnalyse(String siid,Date startdate,Date enddate,
			double deviation,double up1,double down1)
	{
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.startdate=startdate;
		this.deviation=deviation;
		this.up1=up1;
		this.down1=down1;
		analysis.message="roc: ";
	}
	public RocAnalyse(String type,String siid,Date enddate,
			Date startdate,double deviation,double up1,double down1)
	{
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.startdate=startdate;
		this.deviation=deviation;
		this.up1=up1;
		this.down1=down1;
		analysis.message="roc: ";
	}
	/**评分标准：用当日roc的数值大小判断买入卖出情况
	 * scoreOut=0	,roc<=up1-deviation*up1
	 * 			(roc-(up1-deviation*up1))/(up1-(up1-deviation*up1))*60+0	,up1-deviation*up1<=roc<=up1
	 * 			(roc-up1)/(up2-up1)*30+60	,up1<=roc<=up2
	 * 			(roc-up3)/(up2-up3)*70+20	,up2<=roc<=up3
	 * 			20	,roc>=up3
	 * scoreIn=	20	,roc<=down3
	 * 			(roc-down3)/(down2-down3)*70+20	,down3<=roc<=down2
	 * 			(roc-down1)/(down2-down1)*30+60	,down1<=roc<=down2
	 * 			(roc-(down1+deviation*down1))/(down1+deviation*down1-down1)*60+0	,down1<=roc<=down1+down1*deviation
	 * 			0	,roc>=down1+down1*deviation
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		double scoreIn12=0;
		double scoreOut12=0;
		double scoreIn25=0;
		double scoreOut25=0;
		
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(enddate);
		datePack.setId1(quotaDataMapper.selectQuotaData_latest(datePack).getId());
		
		datePack.setDate1(startdate);
		datePack.setDate2(enddate);
		List<Double> roc12list_12=quotaDataMapper.selectRoc12_12_b_date(datePack);
		List<Double> roc25list_12=quotaDataMapper.selectRoc25_12_b_date(datePack);
		
		datePack.setNum(5);
		List<Double> roc12list5=quotaDataMapper.selectRoc12_num_id(datePack);
		List<Double> roc25list5=quotaDataMapper.selectRoc25_num_id(datePack);
		List<Double> roc12increase5=new ArrayList<Double>();
		List<Double> roc25increase5=new ArrayList<Double>();
		List<TrendPoint> roc12trend5=new ArrayList<TrendPoint>();
		List<TrendPoint> roc25trend5=new ArrayList<TrendPoint>();
		roc12trend5.add(new TrendPoint(0,roc12list5.get(0)));
		roc25trend5.add(new TrendPoint(0,roc25list5.get(0)));
		for(int i=1;i<roc12list5.size();i++)
		{
			roc12trend5.add(new TrendPoint(i,roc12list5.get(i)));
			roc25trend5.add(new TrendPoint(i,roc25list5.get(i)));
			roc12increase5.add(roc12list5.get(i)-roc12list5.get(i-1));
			roc25increase5.add(roc25list5.get(i)-roc25list5.get(i-1));
		}
		TrendFlag roc12flag5=new TrendTool(MMSTool.absmean_double(roc12increase5),roc12trend5).trend();
		TrendFlag roc25flag5=new TrendTool(MMSTool.absmean_double(roc25increase5),roc25trend5).trend();
		
		double roc12_up1=20;
		double roc12_up2=40;
		double roc12_up3=60;
		double roc12_down1=-20;		
		double roc12_down2=-40;
		double roc12_down3=-60;
		
		double roc25_up1=20;
		double roc25_up2=40;
		double roc25_up3=60;
		double roc25_down1=-20;
		double roc25_down2=-40;
		double roc25_down3=-60;
		
		if(roc12list_12.size()>4)
		{
			roc12_up1=roc12list_12.get((int)(roc12list_12.size()*up1));
			roc12_up2=2*roc12_up1;
			roc12_up3=3*roc12_up1;
			roc12_down1=roc12list_12.get((int)(roc12list_12.size()*down1));		
			roc12_down2=2*roc12_down1;
			roc12_down3=3*roc12_down1;
		}
		if(roc25list_12.size()>4)
		{
			roc25_up1=roc25list_12.get((int)(roc25list_12.size()*up1));
			roc25_up2=2*roc25_up1;
			roc25_up3=3*roc25_up1;
			roc25_down1=roc25list_12.get((int)(roc25list_12.size()*down1));
			roc25_down2=2*roc25_down1;
			roc25_down3=3*roc25_down1;
		}
		
		double roc12=roc12list5.get(roc12list5.size()-1);
		if(roc12<=roc12_down3)
		{
			scoreIn12=20;
			analysis.message+="ROC12向下穿越第三条超卖线，属于崩溃性空头行情，反弹之后还要跌，应克制不轻易买进股票.\n";
		}
		else if(roc12>=roc12_down3&&roc12<=roc12_down2)
		{
			if(roc12_down2-roc12_down3!=0)
			{
				scoreIn12=(roc12-roc12_down3)/(roc12_down2-roc12_down3)*70+20;
				analysis.message+="ROC12下降至第二条超卖线，跌热多半将停止.\n";
			}
		}
		else if(roc12>=roc12_down2&&roc12<=roc12_down1)
		{
			if(roc12_down2-roc12_down1!=0)
			{
				scoreIn12=(roc12-roc12_down1)/(roc12_down2-roc12_down1)*30+60;
				analysis.message+="ROC12下降至第一条超卖线，建议买入.\n";
			}
		}
		else if(roc12<=roc12_down1+roc12_down1*deviation&&roc12>=roc12_down1)
		{
			if(roc12_down1-(roc12_down1+deviation*roc12_down1)!=0)
			{
				scoreIn12=(roc12-(roc12_down1+deviation*roc12_down1))/(roc12_down1-(roc12_down1+deviation*roc12_down1))*60+0;
				analysis.message+="ROC12下降至第一条超卖线，建议买入.\n";
			}
		}
		else if(roc12>=roc12_up1*(1-deviation)&&roc12<=roc12_up1)
		{
			if(roc12_up1-(roc12_up1-deviation*roc12_up1)!=0)
			{
				scoreOut12=(roc12-(roc12_up1-deviation*roc12_up1))/(roc12_up1-(roc12_up1-deviation*roc12_up1))*60+0;
				analysis.message+="ROC12上升至第一条超买线，建议卖出.\n";
			}
		}
		else if(roc12>=roc12_up1&&roc12<=roc12_up2)
		{
			if(roc12_up2-roc12_up1!=0)
			{
				scoreIn12=(roc12-roc12_up1)/(roc12_up2-roc12_up1)*30+60;
				analysis.message+="ROC12上升至第一条超买线，建议卖出.\n";
			}
		}
		else if(roc12>=roc12_up2&&roc12<=roc12_up3)
		{
			if(roc12_up2-roc12_up3!=0)
			{
				scoreIn12=(roc12-roc12_up3)/(roc12_up2-roc12_up3)*70+20;
				analysis.message+="ROC12上升至第二条超买线，涨势多半将结束.\n";
			}
		}
		else if(roc12>=roc12_up3)
		{
			scoreIn12=20;
			analysis.message+=" ROC12向上穿越第三条超买线，属于疯狂性多头行情，回档之后还要涨，应尽量不轻易卖出持股.\n";
		}
		
		double roc25=roc25list5.get(roc25list5.size()-1);
		if(roc25<=roc25_down3)
		{
			scoreIn25=20;
			analysis.message+="ROC25向下穿越第三条超卖线，属于崩溃性空头行情，反弹之后还要跌，应克制不轻易买进股票.\n";
		}
		else if(roc25>=roc25_down3&&roc25<=roc25_down2)
		{
			if(roc25_down2-roc25_down3!=0)
			{
				scoreIn25=(roc25-roc25_down3)/(roc25_down2-roc25_down3)*70+20;
				analysis.message+="ROC25下降至第二条超卖线，跌热多半将停止.\n";
			}
		}
		else if(roc25>=roc25_down2&&roc25<=roc25_down1)
		{
			if(roc25_down2-roc25_down1!=0)
			{
				scoreIn25=(roc25-roc25_down1)/(roc25_down2-roc25_down1)*30+60;
				analysis.message+="ROC25下降至第一条超卖线，建议买入.\n";
			}
		}
		else if(roc25<=roc25_down1+roc25_down1*deviation&&roc25>=roc25_down1)
		{
			if(roc25_down1-(roc25_down1+deviation*roc25_down1)!=0)
			{
				scoreIn25=(roc25-(roc25_down1+deviation*roc25_down1))/(roc25_down1-(roc25_down1+deviation*roc25_down1))*60+0;
				analysis.message+="ROC25下降至第一条超卖线，建议买入.\n";
			}
		}
		else if(roc25>=roc25_up1*(1-deviation)&&roc25<=roc25_up1)
		{
			if(roc25_up1-(roc25_up1-deviation*roc25_up1)!=0)
			{
				scoreOut25=(roc25-(roc25_up1-deviation*roc25_up1))/(roc25_up1-(roc25_up1-deviation*roc25_up1))*60+0;
				analysis.message+="ROC25上升至第一条超买线，建议卖出.\n";
			}
		}
		else if(roc25>=roc25_up1&&roc25<=roc25_up2)
		{
			if(roc25_up2-roc25_up1!=0)
			{
				scoreIn25=(roc25-roc25_up1)/(roc25_up2-roc25_up1)*30+60;
				analysis.message+="ROC25上升至第一条超买线，建议卖出.\n";
			}
		}
		else if(roc25>=roc25_up2&&roc25<=roc25_up3)
		{
			if(roc25_up2-roc25_up3!=0)
			{
				scoreIn25=(roc25-roc25_up3)/(roc25_up2-roc25_up3)*70+20;
				analysis.message+="ROC25上升至第二条超买线，涨势多半将结束.\n";
			}
		}
		else if(roc25>=roc25_up3)
		{
			scoreIn25=20;
			analysis.message+=" ROC25向上穿越第三条超买线，属于疯狂性多头行情，回档之后还要涨，应尽量不轻易卖出持股.\n";
		}
		
		analysis.scoreIn=DataHelper.controldigit((scoreIn12+scoreIn25)/2);
		analysis.scoreOut=DataHelper.controldigit((scoreOut12+scoreOut25)/2);
		return analysis;
	}
}
