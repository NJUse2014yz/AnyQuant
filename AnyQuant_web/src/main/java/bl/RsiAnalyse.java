package bl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.QuotaData;
import pr.QuotaDataPr;
import vo.QuotaAnalysis;
import mapper.QuotaDataMapper;

/**
 * 强弱指标的分析
 * @author YU Fan
 * @date 2016年5月31日
 */
public class RsiAnalyse extends QuotaAnalyse{
	private Date startdate;
	private double up;
	private double mid;
	private double down;
	private double deviation;
	
	public RsiAnalyse(String siid)
	{
		super(siid);
		this.startdate=new Date(0,0,1);
		this.up=0.8;
		this.mid=0.5;
		this.down=0.2;
		this.deviation=0.05;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	public RsiAnalyse(String type,String siid)
	{
		super(type,siid);
		this.startdate=new Date(0,0,1);
		this.up=0.8;
		this.mid=0.5;
		this.down=0.2;
		this.deviation=0.05;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	public RsiAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		this.startdate=new Date(0,0,1);
		this.up=0.8;
		this.mid=0.5;
		this.down=0.2;
		this.deviation=0.05;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	public RsiAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		this.startdate=new Date(0,0,1);
		this.up=0.8;
		this.mid=0.5;
		this.down=0.2;
		this.deviation=0.05;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	public RsiAnalyse(String siid,Date startdate,double up,double mid,double down,double deviation)
	{
		super(siid);
		this.startdate=startdate;
		this.up=down;
		this.mid=mid;
		this.down=down;
		this.deviation=deviation;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	public RsiAnalyse(String type,String siid,Date startdate,double up,double mid,double down,double deviation)
	{
		super(type,siid);
		this.startdate=startdate;
		this.up=down;
		this.mid=mid;
		this.down=down;
		this.deviation=deviation;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	public RsiAnalyse(String siid,Date startdate,Date enddate,double up,double mid,double down,double deviation)
	{
		super(siid,enddate);
		this.startdate=startdate;
		this.up=down;
		this.mid=mid;
		this.down=down;
		this.deviation=deviation;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	public RsiAnalyse(String type,String siid,Date enddate,Date startdate,double up,double mid,double down,double deviation)
	{
		super(type,siid,enddate);
		this.startdate=startdate;
		this.up=down;
		this.mid=mid;
		this.down=down;
		this.deviation=deviation;
		analysis=new QuotaAnalysis();
		analysis.message="rsi: ";
	}
	
	/**评分标准：用当日的超短期(5日)、短期(10日)、中期(20日)相对强弱指标的数值大小判断买入卖出情况
	 * scoreOut=(rsi-up)/(100-up)*20+80 ,up<=rsi<=100 
	 * 			(rsi-mid)/(up-mid)*60+20 ,mid<=rsi<=up
	 * 			(rsi-down)/(mid-down)*20+0 ,down<=rsi<=mid
	 * 			0 ,0<=rsi<=down
	 * scoreIn=	(rsi-down)/(0-down)*20+80 ,0<=rsi<=down
	 * 
	 * 			(rsi-(down+deviation*(mid-down)))/(down-(down+deviation*(mid-down)))*70+10		,down<=rsi<=down+deviation*(mid-down)
	 * 			(rsi-((down+mid)/2))/((down+deviation*(mid-down))-((down+mid)/2))*10+0			,down+deviation*(mid-down)<=rsi<=(mid+down)/2
	 * 			(rsi-((down+mid)/2))/((mid-deviation*(mid-down))-((down+mid)/2))*10+0			,(mid+down)/2<=rsi<=mid-deviation*(mid-down)
	 * 			(rsi-(mid-deviation*(mid-down)))/(mid-(mid-deviation*(mid-down)))*70+10			,mid-deviation*(mid-down)<=rsi<=mid
	 * 			(rsi-mid)/((mid+up)/2-mid)*10+80												,mid<=rsi<=(mid+up)/2
	 * 			(rsi-up)/((mid+up)/2-up)*10+80													,(mid+up)/2<=rsi<=up
	 * 
	 * 			(rsi-(up+deviation*(100-up)))/(up-(up+deviation*(100-up)))*70+10	,up<=rsi<=up+deviation*(100-up)
	 * 			(rsi-100)/((up+deviation*(100-up))-100)*10+0						,up+deviation*(100-up)<=rsi<=100
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(startdate);
		datePack.setDate2(enddate);
		List<QuotaData> quotaData_list=quotaDataMapper.selectQuotaData_b_date(datePack);
		List<Double> list5=quotaDataMapper.selectRsi5_12_b_date(datePack);
		List<Double> list10=quotaDataMapper.selectRsi10_12_b_date(datePack);
		List<Double> list20=quotaDataMapper.selectRsi20_12_b_date(datePack);
		
		double rsi5_up=80;
		double rsi5_mid=50;
		double rsi5_down=20;
		
		double rsi10_up=80;
		double rsi10_mid=50;
		double rsi10_down=20;
		
		double rsi20_up=80;
		double rsi20_mid=50;
		double rsi20_down=20;
		
		if(list5.size()>4)
		{
			if(list5.get((int)(list5.size()*up))<100&&list5.get((int)(list5.size()*up))>0)
				rsi5_up=list5.get((int)(list5.size()*up));
			if(list5.get((int)(list5.size()*mid))<100&&list5.get((int)(list5.size()*mid))>0)
				rsi5_mid=list5.get((int)(list5.size()*mid));
			if(list5.get((int)(list5.size()*down))<100&&list5.get((int)(list5.size()*down))>0)
				rsi5_down=list5.get((int)(list5.size()*down));
		}
		if(list10.size()>4)
		{
			if(list10.get((int)(list10.size()*up))<100&&list10.get((int)(list10.size()*up))>0)
				rsi10_up=list10.get((int)(list10.size()*up));
			if(list10.get((int)(list10.size()*mid))<100&&list10.get((int)(list10.size()*mid))>0)
				rsi10_mid=list10.get((int)(list10.size()*mid));
			if(list10.get((int)(list10.size()*down))<100&&list10.get((int)(list10.size()*down))>0)
				rsi10_down=list10.get((int)(list10.size()*down));
		}
		if(list20.size()>4)
		{
			if(list20.get((int)(list20.size()*up))<100&&list20.get((int)(list20.size()*up))>0)
				rsi20_up=list20.get((int)(list20.size()*up));
			if(list20.get((int)(list20.size()*mid))<100&&list20.get((int)(list20.size()*mid))>0)
				rsi20_mid=list20.get((int)(list20.size()*mid));
			if(list20.get((int)(list20.size()*down))<100&&list20.get((int)(list20.size()*down))>0)
				rsi20_down=list20.get((int)(list20.size()*down));
		}
		
		QuotaData quotaData_new=new QuotaDataPr().getQuotaData_new(type, siid);
		double rsi5_new=quotaData_new.getRsi5();
		double rsi10_new=quotaData_new.getRsi10();
		double rsi20_new=quotaData_new.getRsi20();
		
		double scoreIn5=0;
		double scoreIn10=0;
		double scoreIn20=0;
		double scoreOut5=0;
		double scoreOut10=0;
		double scoreOut20=0;
		
		if(rsi5_new>=0&&rsi5_new<rsi5_down)
		{
			scoreIn5=(rsi5_new-rsi5_down)/(0-rsi5_down)*20+80;
			scoreOut5=0;
			analysis.message+="rsi5 处于极弱市场，进入超卖区，建议买入，不建议卖出.\n";
		}
		else if(rsi5_new>=rsi5_down&&rsi5_new<rsi5_mid)
		{
			if(rsi5_new>=rsi5_down&&rsi5_new<=rsi5_down+deviation*(rsi5_mid-rsi5_down))
			{
				if(rsi5_down-(rsi5_down+deviation*(rsi5_mid-rsi5_down))!=0)
					scoreIn5=(rsi5_new-(rsi5_down+deviation*(rsi5_mid-rsi5_down)))/(rsi5_down-(rsi5_down+deviation*(rsi5_mid-rsi5_down)))*70+10;
			}
			else if(rsi5_new>=rsi5_down+deviation*(rsi5_mid-rsi5_down)&&rsi5_new<=(rsi5_down+rsi5_mid)/2)
			{
				if(rsi5_down+deviation*(rsi5_mid-rsi5_down)!=0)
					scoreIn5=(rsi5_new-((rsi5_down+rsi5_mid)/2))/((rsi5_down+deviation*(rsi5_mid-rsi5_down))-((rsi5_down+rsi5_mid)/2))*10+0;
			}
			else if(rsi5_new>=(rsi5_mid+rsi5_down)/2&&rsi5_new<=rsi5_mid-deviation*(rsi5_mid-rsi5_down))
			{
				if((rsi5_mid-deviation*(rsi5_mid-rsi5_down))-((rsi5_down+rsi5_mid)/2)!=0)
					scoreIn5=(rsi5_new-((rsi5_down+rsi5_mid)/2))/((rsi5_mid-deviation*(rsi5_mid-rsi5_down))-((rsi5_down+rsi5_mid)/2))*10+0;
			}
			else
			{
				if(rsi5_mid-(rsi5_mid-deviation*(rsi5_mid-rsi5_down))!=0)
					scoreIn5=(rsi5_new-(rsi5_mid-deviation*(rsi5_mid-rsi5_down)))/(rsi5_mid-(rsi5_mid-deviation*(rsi5_mid-rsi5_down)))*70+10;
			}
			scoreOut5=(rsi5_new-rsi5_down)/(rsi5_mid-rsi5_down)*20+0;
			analysis.message+="rsi5 处于弱势市场，建议观望.\n";
		}
		else if(rsi5_new>=rsi5_mid&&rsi5_new<rsi5_up)
		{
			if(rsi5_new>=rsi5_mid&&rsi5_new<=(rsi5_mid+rsi5_up)/2)
			{
				if((rsi5_mid+rsi5_up)/2-rsi5_mid!=0)
					scoreIn5=(rsi5_new-rsi5_mid)/((rsi5_mid+rsi5_up)/2-rsi5_mid)*10+80;
			}
			else
			{
				if((rsi5_mid+rsi5_up)/2-rsi5_up!=0)
					scoreIn5=(rsi5_new-rsi5_up)/((rsi5_mid+rsi5_up)/2-rsi5_up)*10+80;
			}
			scoreOut5=(rsi5_new-rsi5_mid)/(rsi5_up-rsi5_mid)*60+20;
			analysis.message+="rsi5 处于强势市场，更建议买入.\n";
		}
		else if(rsi5_new>=rsi5_up&&rsi5_new<=100)
		{
			if(rsi5_new>=rsi5_up+deviation*(100-rsi5_up)&&rsi5_new<=100)
			{
				if((rsi5_up+deviation*(100-rsi5_up))-100!=0)
					scoreIn5=(rsi5_new-100)/((rsi5_up+deviation*(100-rsi5_up))-100)*10+0;
			}
			else
			{
				if(rsi5_up-(rsi5_up+deviation*(100-rsi5_up))!=0)
					scoreIn5=(rsi5_new-(rsi5_up+deviation*(100-rsi5_up)))/(rsi5_up-(rsi5_up+deviation*(100-rsi5_up)))*70+10;
			}
			scoreOut5=(rsi5_new-rsi5_up)/(100-rsi5_up)*80+20;
			analysis.message+="rsi5 处于极强市场，进入超买区，建议卖出，不建议买入.\n";
		}
		
		if(rsi10_new>=0&&rsi10_new<rsi10_down)
		{
			if(0-rsi10_down!=0)
			{
				scoreIn10=(rsi10_new-rsi10_down)/(0-rsi10_down)*20+80;
				scoreOut10=0;
				analysis.message+="rsi10 处于极弱市场，进入超卖区，建议买入，不建议卖出.\n";
			}
		}
		else if(rsi10_new>=rsi10_down&&rsi10_new<rsi10_mid)
		{
			if(rsi10_new>=rsi10_down&&rsi10_new<=rsi10_down+deviation*(rsi10_mid-rsi10_down))
			{
				if(rsi10_down-(rsi10_down+deviation*(rsi10_mid-rsi10_down))!=0)
					scoreIn10=(rsi10_new-(rsi10_down+deviation*(rsi10_mid-rsi10_down)))/(rsi10_down-(rsi10_down+deviation*(rsi10_mid-rsi10_down)))*70+10;
			}
			else if(rsi10_new>=rsi10_down+deviation*(rsi10_mid-rsi10_down)&&rsi10_new<=(rsi10_down+rsi10_mid)/2)
			{
				if((rsi10_down+deviation*(rsi10_mid-rsi10_down))-((rsi10_down+rsi10_mid)/2)!=0)
					scoreIn10=(rsi10_new-((rsi10_down+rsi10_mid)/2))/((rsi10_down+deviation*(rsi10_mid-rsi10_down))-((rsi10_down+rsi10_mid)/2))*10+0;
			}
			else if(rsi10_new>=(rsi10_mid+rsi10_down)/2&&rsi10_new<=rsi10_mid-deviation*(rsi10_mid-rsi10_down))
			{
				if((rsi10_mid-deviation*(rsi10_mid-rsi10_down))-((rsi10_down+rsi10_mid)/2)!=0)
					scoreIn10=(rsi10_new-((rsi10_down+rsi10_mid)/2))/((rsi10_mid-deviation*(rsi10_mid-rsi10_down))-((rsi10_down+rsi10_mid)/2))*10+0;
			}
			else
			{
				if(rsi10_mid-(rsi10_mid-deviation*(rsi10_mid-rsi10_down))!=0)
					scoreIn10=(rsi10_new-(rsi10_mid-deviation*(rsi10_mid-rsi10_down)))/(rsi10_mid-(rsi10_mid-deviation*(rsi10_mid-rsi10_down)))*70+10;
			}
			scoreOut10=(rsi10_new-rsi10_down)/(rsi10_mid-rsi10_down)*20+0;
			analysis.message+="rsi10 处于弱势市场，建议观望.\n";
		}
		else if(rsi10_new>=rsi10_mid&&rsi10_new<rsi10_up)
		{
			if(rsi10_new>=rsi10_mid&&rsi10_new<=(rsi10_mid+rsi10_up)/2)
			{
				if((rsi10_mid+rsi10_up)/2-rsi10_mid!=0)
					scoreIn10=(rsi10_new-rsi10_mid)/((rsi10_mid+rsi10_up)/2-rsi10_mid)*10+80;
			}
			else
			{
				if((rsi10_mid+rsi10_up)/2-rsi10_up!=0)
					scoreIn10=(rsi10_new-rsi10_up)/((rsi10_mid+rsi10_up)/2-rsi10_up)*10+80;
			}
			if(rsi10_up-rsi10_mid!=0)
				scoreOut10=(rsi10_new-rsi10_mid)/(rsi10_up-rsi10_mid)*60+20;
			analysis.message+="rsi10 处于强势市场，更建议买入.\n";
		}
		else if(rsi10_new>=rsi10_up&&rsi10_new<=100)
		{
			if(rsi10_new>=rsi10_up+deviation*(100-rsi10_up)&&rsi10_new<=100)
			{
				if((rsi10_up+deviation*(100-rsi10_up))-100!=0)
					scoreIn10=(rsi10_new-100)/((rsi10_up+deviation*(100-rsi10_up))-100)*10+0;
			}
			else
			{
				if(rsi10_up-(rsi10_up+deviation*(100-rsi10_up))!=0)
					scoreIn10=(rsi10_new-(rsi10_up+deviation*(100-rsi10_up)))/(rsi10_up-(rsi10_up+deviation*(100-rsi10_up)))*70+10;
			}
			if(100-rsi10_up!=0)
				scoreOut10=(rsi10_new-rsi10_up)/(100-rsi10_up)*80+20;
			analysis.message+="rsi10 处于极强市场，进入超买区，建议卖出，不建议买入.\n";
		}
		
		if(rsi20_new>=0&&rsi20_new<rsi20_down)
		{
			if(0-rsi20_down!=0)
			{
				scoreIn20=(rsi20_new-rsi20_down)/(0-rsi20_down)*20+80;
				scoreOut20=0;
				analysis.message+="rsi20 处于极弱市场，进入超卖区，建议买入，不建议卖出.\n";
			}
		}
		else if(rsi20_new>=rsi20_down&&rsi20_new<rsi20_mid)
		{
			if(rsi20_new>=rsi20_down&&rsi20_new<=rsi20_down+deviation*(rsi20_mid-rsi20_down))
			{
				if(rsi20_down-(rsi20_down+deviation*(rsi20_mid-rsi20_down))!=0)
					scoreIn20=(rsi20_new-(rsi20_down+deviation*(rsi20_mid-rsi20_down)))/(rsi20_down-(rsi20_down+deviation*(rsi20_mid-rsi20_down)))*70+10;
			}
			else if(rsi20_new>=rsi20_down+deviation*(rsi20_mid-rsi20_down)&&rsi20_new<=(rsi20_down+rsi20_mid)/2)
			{
				if((rsi20_down+deviation*(rsi20_mid-rsi20_down))-((rsi20_down+rsi20_mid)/2)!=0)
					scoreIn20=(rsi20_new-((rsi20_down+rsi20_mid)/2))/((rsi20_down+deviation*(rsi20_mid-rsi20_down))-((rsi20_down+rsi20_mid)/2))*10+0;
			}
			else if(rsi20_new>=(rsi20_mid+rsi20_down)/2&&rsi20_new<=rsi20_mid-deviation*(rsi20_mid-rsi20_down))
			{
				if((rsi20_mid-deviation*(rsi20_mid-rsi20_down))-((rsi20_down+rsi20_mid)/2)!=0)
					scoreIn20=(rsi20_new-((rsi20_down+rsi20_mid)/2))/((rsi20_mid-deviation*(rsi20_mid-rsi20_down))-((rsi20_down+rsi20_mid)/2))*10+0;
			}
			else
			{
				if(rsi20_mid-(rsi20_mid-deviation*(rsi20_mid-rsi20_down))!=0)
					scoreIn20=(rsi20_new-(rsi20_mid-deviation*(rsi20_mid-rsi20_down)))/(rsi20_mid-(rsi20_mid-deviation*(rsi20_mid-rsi20_down)))*70+10;
			}
			if(rsi20_mid-rsi20_down!=0)
				scoreOut20=(rsi20_new-rsi20_down)/(rsi20_mid-rsi20_down)*20+0;
			analysis.message+="rsi20 处于弱势市场，建议观望.\n";
		}
		else if(rsi20_new>=rsi20_mid&&rsi20_new<rsi20_up)
		{
			if(rsi20_new>=rsi20_mid&&rsi20_new<=(rsi20_mid+rsi20_up)/2)
			{
				if((rsi20_mid+rsi20_up)/2-rsi20_mid!=0)
					scoreIn20=(rsi20_new-rsi20_mid)/((rsi20_mid+rsi20_up)/2-rsi20_mid)*10+80;
			}
			else
			{
				if((rsi20_mid+rsi20_up)/2-rsi20_up!=0)
					scoreIn20=(rsi20_new-rsi20_up)/((rsi20_mid+rsi20_up)/2-rsi20_up)*10+80;
			}
			if(rsi20_up-rsi20_mid!=0)
				scoreOut20=(rsi20_new-rsi20_mid)/(rsi20_up-rsi20_mid)*60+20;
			analysis.message+="rsi20 处于强势市场，更建议买入.\n";
		}
		else if(rsi20_new>=rsi20_up&&rsi20_new<=100)
		{
			if(rsi20_new>=rsi20_up+deviation*(100-rsi20_up)&&rsi20_new<=100)
			{
				if((rsi20_up+deviation*(100-rsi20_up))-100!=0)
					scoreIn20=(rsi20_new-100)/((rsi20_up+deviation*(100-rsi20_up))-100)*10+0;
			}
			else
			{
				if(rsi20_up-(rsi20_up+deviation*(100-rsi20_up))!=0)
					scoreIn20=(rsi20_new-(rsi20_up+deviation*(100-rsi20_up)))/(rsi20_up-(rsi20_up+deviation*(100-rsi20_up)))*70+10;				
			}
			if(100-rsi20_up!=0)
				scoreOut20=(rsi20_new-rsi20_up)/(100-rsi20_up)*80+20;
			analysis.message+="rsi20 处于极强市场，进入超买区，建议卖出，不建议买入.\n";
		}
		
		analysis.scoreIn=DataHelper.controldigit((scoreIn5+scoreIn10+scoreIn20)/3);
		analysis.scoreOut=DataHelper.controldigit((scoreOut5+scoreOut10+scoreOut20)/3);
		
//		System.out.println("ris5_new "+rsi5_new);
//		System.out.println("ris10_new "+rsi10_new);
//		System.out.println("ris20_new "+rsi20_new);
//		System.out.println("rsi5_up "+rsi5_up);
//		System.out.println("rsi5_mid "+rsi5_mid);
//		System.out.println("rsi5_down "+rsi5_down);
//		System.out.println("rsi10_up "+rsi10_up);
//		System.out.println("rsi10_mid "+rsi10_mid);
//		System.out.println("rsi10_down "+rsi10_down);
//		System.out.println("rsi20_up "+rsi20_up);
//		System.out.println("rsi20_mid "+rsi20_mid);
//		System.out.println("rsi20_down "+rsi20_down);
//		System.out.println("scoreIn5 "+scoreIn5);
//		System.out.println("scoreIn10 "+scoreIn10);
//		System.out.println("scoreIn20 "+scoreIn20);
//		System.out.println("scoreOut5 "+scoreOut5);
//		System.out.println("scoreOut10 "+scoreOut10);
//		System.out.println("scoreOut20 "+scoreOut20);
//		System.out.println(analysis);
		
		return analysis;
	}
	public static void main(String[] args)
	{
		try {
			new RsiAnalyse("sh600000").pro_quo();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
