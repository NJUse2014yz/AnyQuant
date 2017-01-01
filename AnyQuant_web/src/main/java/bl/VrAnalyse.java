package bl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import mapper.QuotaDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import pr.QuotaDataPr;
import vo.QuotaAnalysis;

public class VrAnalyse extends QuotaAnalyse{
	private double in1;
	private double in2;
	private double wait1;
	private double wait2;
	private double out1;
	private double out2;
	private Date startdate;
	private Date enddate;
	private double deviation;
	
	public VrAnalyse(String siid)
	{
		super(siid);
		in1=40;
		in2=70;
		wait1=80;
		wait2=150;
		out1=160;
		out2=450;
		startdate=new Date(0,0,1);
		enddate=new Date(Calendar.getInstance().getTimeInMillis());
		deviation=0.05;
		analysis.message="vr: ";
	}
	public VrAnalyse(String type,String siid)
	{
		super(type,siid);
		in1=40;
		in2=70;
		wait1=80;
		wait2=150;
		out1=160;
		out2=450;
		startdate=new Date(0,0,1);
		enddate=new Date(Calendar.getInstance().getTimeInMillis());
		deviation=0.05;
		analysis.message="vr: ";
	}
	
	public VrAnalyse(String siid, double in1, double in2, double wait1, double wait2,
			double out1, double out2,Date startdate,Date enddate,double deviation) {
		super(siid);
		this.in1 = in1;
		this.in2 = in2;
		this.wait1 = wait1;
		this.wait2 = wait2;
		this.out1 = out1;
		this.out2 = out2;
		this.startdate=startdate;
		this.enddate=enddate;
		this.deviation=deviation;
		analysis.message="vr: ";
	}
	public VrAnalyse(String type,String siid, double in1, double in2, double wait1, double wait2,
			double out1, double out2,Date startdate,Date enddate,double deviation) {
		super(type,siid);
		this.in1 = in1;
		this.in2 = in2;
		this.wait1 = wait1;
		this.wait2 = wait2;
		this.out1 = out1;
		this.out2 = out2;
		this.startdate=startdate;
		this.enddate=enddate;
		this.deviation=deviation;
		analysis.message="vr: ";
	}
	
	public VrAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		in1=40;
		in2=70;
		wait1=80;
		wait2=150;
		out1=160;
		out2=450;
		startdate=new Date(0,0,1);
		enddate=new Date(Calendar.getInstance().getTimeInMillis());
		deviation=0.05;
		analysis.message="vr: ";
	}
	public VrAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		in1=40;
		in2=70;
		wait1=80;
		wait2=150;
		out1=160;
		out2=450;
		startdate=new Date(0,0,1);
		enddate=new Date(Calendar.getInstance().getTimeInMillis());
		deviation=0.05;
		analysis.message="vr: ";
	}
	
	public VrAnalyse(String siid,Date enddate1, double in1, double in2, double wait1, double wait2,
			double out1, double out2,Date startdate,Date enddate,double deviation) {
		super(siid,enddate1);
		this.in1 = in1;
		this.in2 = in2;
		this.wait1 = wait1;
		this.wait2 = wait2;
		this.out1 = out1;
		this.out2 = out2;
		this.startdate=startdate;
		this.enddate=enddate;
		this.deviation=deviation;
		analysis.message="vr: ";
	}
	public VrAnalyse(String type,Date enddate1,String siid, double in1, double in2, double wait1, double wait2,
			double out1, double out2,Date startdate,Date enddate,double deviation) {
		super(type,siid,enddate1);
		this.in1 = in1;
		this.in2 = in2;
		this.wait1 = wait1;
		this.wait2 = wait2;
		this.out1 = out1;
		this.out2 = out2;
		this.startdate=startdate;
		this.enddate=enddate;
		this.deviation=deviation;
		analysis.message="vr: ";
	}
	/**评分标准：用当日
	 * scoreOut=100								,vr>=out2
	 * 			(vr-out1)/(out2-out1)*20+80		,out1<=vr<=out2
	 * 			(vr-wait2)/(out1-wait2)*30+50	,wait2<=vr<=out1
	 * 			(vr-wait1)/(wait2-wait1)*30+20	,wait1<=vr<=wait2
	 * 			(vr-in1)/(wait1-in1)*20+0		,in1<=vr<=wait1
	 * 			(vr-in1)/(0-in1)*60+0			,0<=vr<=in1
	 * scoreIn=	0	,vr>=out1
	 * 			(vr-out1)/(wait1-out1)*20+0						,wait1<=vr<=out1
	 * 			(vr-wait1)/(in2-wait1)*20+40					,in2<=vr<=wait1
	 * 			(vr-in2)/(in2-deviation*(in2-in1)-in2)*20+60	,in2-deviation*(in2-in1)<=vr<=in2
	 * 			(vr-(in2-deviation*(in2-in1)))/((in1+in2)/2-(in2-deviation*(in2-in1)))*10+80	,(in1+in2)/2<=vr<=in2-deviation*(in2-in1)
	 * 			(vr-(in1+deviation*(in2-in1)))/((in1+in2)/2-(in1+deviation*(in2-in1)))*10+80	,in1+deviation*(in2-in1)<=vr<=(in1+in2)/2
	 * 			(vr-in1)/(in1+deviation*(in2-in1)-in1)*20+60	,in1<=vr<=in1+deviation*(in2-in1)
	 * 			60												,0<=vr<=in1
	 * A.低价区域：70~40——为可买进区域
	 * B.安全区域：150~80——正常分布区域
	 * C.获利区域：450~160——应考虑获利了结
	 * D.警戒区域：450以上——股价已过高
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(enddate);
		double vr_new=quotaDataMapper.selectQuotaData_latest(datePack).getVr();
		datePack.setDate1(startdate);
		datePack.setDate2(enddate);
		
		double scoreIn=0;
		double scoreOut=0;
		
		if(vr_new>=0&&vr_new<=in1)
		{
			if(0-in1!=0)
			{
				scoreIn=60;
				scoreOut=(vr_new-in1)/(0-in1)*60+0;
				analysis.message+="vr处于超低价区域，不建议卖出，买入有风险谨慎操作\n";
			}
		}
		else if(vr_new>=in1&&vr_new<=wait1)
		{
			if(vr_new>=in1&&vr_new<=in1+deviation*(in2-in1))
			{
				if(in1+deviation*(in2-in1)-in1!=0)
				{
					scoreIn=(vr_new-in1)/(in1+deviation*(in2-in1)-in1)*20+60;
					analysis.message+="vr处于低价区域，建议买入\n";
				}
			}
			else if(vr_new>=in1+deviation*(in2-in1)&&vr_new<=(in1+in2)/2)
			{
				if((in1+in2)/2-(in1+deviation*(in2-in1))!=0)
				{
					scoreIn=(vr_new-(in1+deviation*(in2-in1)))/((in1+in2)/2-(in1+deviation*(in2-in1)))*10+80;
					analysis.message+="vr处于低价区域，建议买入\n";
				}
			}
			else if(vr_new>=(in1+in2)/2&&vr_new<=in2-deviation*(in2-in1))
			{
				if((in1+in2)/2-(in2-deviation*(in2-in1))!=0)
				{
					scoreIn=(vr_new-(in2-deviation*(in2-in1)))/((in1+in2)/2-(in2-deviation*(in2-in1)))*10+80;
					analysis.message+="vr处于低价区域，建议买入\n";
				}
			}
			else if(vr_new>=in2-deviation*(in2-in1)&&vr_new<=in2)
			{
				if(in2-deviation*(in2-in1)-in2!=0)
				{
					scoreIn=(vr_new-in2)/(in2-deviation*(in2-in1)-in2)*20+60;
					analysis.message+="vr处于低价区域，建议买入\n";
				}
			}
			else
			{
				if(in2-wait1!=0)
					scoreIn=(vr_new-wait1)/(in2-wait1)*20+40;
			}
			if(wait1-in1!=0)
				scoreOut=(vr_new-in1)/(wait1-in1)*20+0;
		}
		else if(vr_new>=wait1&&vr_new<=out1)
		{
			if(vr_new>=wait1&&vr_new<=wait2)
			{
				if(wait2-wait1!=0)
				{
					scoreOut=(vr_new-wait1)/(wait2-wait1)*30+20;
					analysis.message+="vr处于安全区，建议观望\n";
				}
			}
			else
			{
				if(out1-wait2!=0)
					scoreOut=(vr_new-wait2)/(out1-wait2)*30+50;
			}
			if(wait1-out1!=0)
				scoreIn=(vr_new-out1)/(wait1-out1)*20+0;
		}
		else
		{
			if(vr_new>=out1&&vr_new<=out2)
			{
				if(out2-out1!=0)
				{
					scoreOut=(vr_new-out1)/(out2-out1)*20+80;
					analysis.message+="vr处于获利区，建议伺机卖出，获利了结\n";
				}
			}
			else
			{
				scoreOut=100;
				analysis.message+="vr处于警戒区，尽快卖出\n";
			}
			scoreIn=0;
		}
		
		analysis.scoreIn=DataHelper.controldigit(scoreIn);
		analysis.scoreOut=DataHelper.controldigit(scoreOut);
		
		return analysis;
	}
}
