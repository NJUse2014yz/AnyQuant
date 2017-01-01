package bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import mapper.HistoryDataMapper;
import mapper.QuotaDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.HistoryData;
import po.QuotaData;
import pr.QuotaDataPr;
import tool.MMSTool;
import tool.TrendFlag;
import tool.TrendPoint;
import tool.TrendTool;
import vo.QuotaAnalysis;

public class ObvAnalyse extends QuotaAnalyse{
	private HistoryDataMapper historyDataMapper;
	private double obvfastspeed5;
	private double obvslowspeed5;
	private double closefastspeed5;
	private double closeslowspeed5;
	private double obvfastspeed10;
	private double obvslowspeed10;
	private double closefastspeed10;
	private double closeslowspeed10;
	private double obvfastspeed20;
	private double obvslowspeed20;
	private double closefastspeed20;
	private double closeslowspeed20;
	
	public ObvAnalyse(String siid)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		obvfastspeed5=Math.PI/3;
		obvslowspeed5=Math.PI/6;
		closefastspeed5=Math.PI/3;
		closeslowspeed5=Math.PI/6;
		obvfastspeed10=Math.PI/3;
		obvslowspeed10=Math.PI/6;
		closefastspeed10=Math.PI/3;
		closeslowspeed10=Math.PI/6;
		obvfastspeed20=Math.PI/3;
		obvslowspeed20=Math.PI/6;
		closefastspeed20=Math.PI/3;
		closeslowspeed20=Math.PI/6;
		analysis.message="obv: ";
	}
	public ObvAnalyse(String type,String siid)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		obvfastspeed5=Math.PI/3;
		obvslowspeed5=Math.PI/6;
		closefastspeed5=Math.PI/3;
		closeslowspeed5=Math.PI/6;
		obvfastspeed10=Math.PI/3;
		obvslowspeed10=Math.PI/6;
		closefastspeed10=Math.PI/3;
		closeslowspeed10=Math.PI/6;
		obvfastspeed20=Math.PI/3;
		obvslowspeed20=Math.PI/6;
		closefastspeed20=Math.PI/3;
		closeslowspeed20=Math.PI/6;
		analysis.message="obv: ";
	}
	
	
	public ObvAnalyse(String siid, double obvfastspeed5, double obvslowspeed5, double closefastspeed5, double closeslowspeed5,
			double obvfastspeed10, double obvslowspeed10, double closefastspeed10, double closeslowspeed10,
			double obvfastspeed20, double obvslowspeed20, double closefastspeed20, double closeslowspeed20) {
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.obvfastspeed5 = obvfastspeed5;
		this.obvslowspeed5 = obvslowspeed5;
		this.closefastspeed5 = closefastspeed5;
		this.closeslowspeed5 = closeslowspeed5;
		this.obvfastspeed10 = obvfastspeed10;
		this.obvslowspeed10 = obvslowspeed10;
		this.closefastspeed10 = closefastspeed10;
		this.closeslowspeed10 = closeslowspeed10;
		this.obvfastspeed20 = obvfastspeed20;
		this.obvslowspeed20 = obvslowspeed20;
		this.closefastspeed20 = closefastspeed20;
		this.closeslowspeed20 = closeslowspeed20;
		analysis.message="obv: ";
	}
	public ObvAnalyse(String type,String siid, double obvfastspeed5, double obvslowspeed5, double closefastspeed5, double closeslowspeed5,
			double obvfastspeed10, double obvslowspeed10, double closefastspeed10, double closeslowspeed10,
			double obvfastspeed20, double obvslowspeed20, double closefastspeed20, double closeslowspeed20) {
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.obvfastspeed5 = obvfastspeed5;
		this.obvslowspeed5 = obvslowspeed5;
		this.closefastspeed5 = closefastspeed5;
		this.closeslowspeed5 = closeslowspeed5;
		this.obvfastspeed10 = obvfastspeed10;
		this.obvslowspeed10 = obvslowspeed10;
		this.closefastspeed10 = closefastspeed10;
		this.closeslowspeed10 = closeslowspeed10;
		this.obvfastspeed20 = obvfastspeed20;
		this.obvslowspeed20 = obvslowspeed20;
		this.closefastspeed20 = closefastspeed20;
		this.closeslowspeed20 = closeslowspeed20;
		analysis.message="obv: ";
	}
	
	public ObvAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		obvfastspeed5=Math.PI/3;
		obvslowspeed5=Math.PI/6;
		closefastspeed5=Math.PI/3;
		closeslowspeed5=Math.PI/6;
		obvfastspeed10=Math.PI/3;
		obvslowspeed10=Math.PI/6;
		closefastspeed10=Math.PI/3;
		closeslowspeed10=Math.PI/6;
		obvfastspeed20=Math.PI/3;
		obvslowspeed20=Math.PI/6;
		closefastspeed20=Math.PI/3;
		closeslowspeed20=Math.PI/6;
		analysis.message="obv: ";
	}
	public ObvAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		obvfastspeed5=Math.PI/3;
		obvslowspeed5=Math.PI/6;
		closefastspeed5=Math.PI/3;
		closeslowspeed5=Math.PI/6;
		obvfastspeed10=Math.PI/3;
		obvslowspeed10=Math.PI/6;
		closefastspeed10=Math.PI/3;
		closeslowspeed10=Math.PI/6;
		obvfastspeed20=Math.PI/3;
		obvslowspeed20=Math.PI/6;
		closefastspeed20=Math.PI/3;
		closeslowspeed20=Math.PI/6;
		analysis.message="obv: ";
	}
	
	
	public ObvAnalyse(String siid,Date enddate, double obvfastspeed5, double obvslowspeed5, double closefastspeed5, double closeslowspeed5,
			double obvfastspeed10, double obvslowspeed10, double closefastspeed10, double closeslowspeed10,
			double obvfastspeed20, double obvslowspeed20, double closefastspeed20, double closeslowspeed20) {
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.obvfastspeed5 = obvfastspeed5;
		this.obvslowspeed5 = obvslowspeed5;
		this.closefastspeed5 = closefastspeed5;
		this.closeslowspeed5 = closeslowspeed5;
		this.obvfastspeed10 = obvfastspeed10;
		this.obvslowspeed10 = obvslowspeed10;
		this.closefastspeed10 = closefastspeed10;
		this.closeslowspeed10 = closeslowspeed10;
		this.obvfastspeed20 = obvfastspeed20;
		this.obvslowspeed20 = obvslowspeed20;
		this.closefastspeed20 = closefastspeed20;
		this.closeslowspeed20 = closeslowspeed20;
		analysis.message="obv: ";
	}
	public ObvAnalyse(String type,String siid, Date enddate,double obvfastspeed5, double obvslowspeed5, double closefastspeed5, double closeslowspeed5,
			double obvfastspeed10, double obvslowspeed10, double closefastspeed10, double closeslowspeed10,
			double obvfastspeed20, double obvslowspeed20, double closefastspeed20, double closeslowspeed20) {
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		this.obvfastspeed5 = obvfastspeed5;
		this.obvslowspeed5 = obvslowspeed5;
		this.closefastspeed5 = closefastspeed5;
		this.closeslowspeed5 = closeslowspeed5;
		this.obvfastspeed10 = obvfastspeed10;
		this.obvslowspeed10 = obvslowspeed10;
		this.closefastspeed10 = closefastspeed10;
		this.closeslowspeed10 = closeslowspeed10;
		this.obvfastspeed20 = obvfastspeed20;
		this.obvslowspeed20 = obvslowspeed20;
		this.closefastspeed20 = closefastspeed20;
		this.closeslowspeed20 = closeslowspeed20;
		analysis.message="obv: ";
	}

	/**评分标准：
	 * 用一段时间的趋势判断买入卖出情况
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(enddate);
		QuotaData quotaData_new=quotaDataMapper.selectQuotaData_latest(datePack);
		datePack.setDate1(quotaData_new.getDate());
		
		datePack.setNum(5);
		List<Double> obvlist5=quotaDataMapper.selectObv_num_date(datePack);
		List<Double> closelist5=historyDataMapper.selectHistoryDataClose_num_date(datePack);
		List<TrendPoint> obvtrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> closetrend5=new ArrayList<TrendPoint>();		
		List<Double> obvincrease5=new ArrayList<Double>();
		List<Double> closeincrease5=new ArrayList<Double>();
		datePack.setNum(10);
		List<Double> obvlist10=quotaDataMapper.selectObv_num_date(datePack);
		List<Double> closelist10=historyDataMapper.selectHistoryDataClose_num_date(datePack);
		List<TrendPoint> obvtrend10=new ArrayList<TrendPoint>();
		List<TrendPoint> closetrend10=new ArrayList<TrendPoint>();
		List<Double> obvincrease10=new ArrayList<Double>();
		List<Double> closeincrease10=new ArrayList<Double>();
		datePack.setNum(20);
		List<Double> obvlist20=quotaDataMapper.selectObv_num_date(datePack);
		List<Double> closelist20=historyDataMapper.selectHistoryDataClose_num_date(datePack);
		List<TrendPoint> obvtrend20=new ArrayList<TrendPoint>();
		List<TrendPoint> closetrend20=new ArrayList<TrendPoint>();
		List<Double> obvincrease20=new ArrayList<Double>();
		List<Double> closeincrease20=new ArrayList<Double>();
		
		obvtrend5.add(new TrendPoint(0,obvlist5.get(0)));
		closetrend5.add(new TrendPoint(0,closelist5.get(0)));
		for(int i=1;i<obvlist5.size();i++)
		{
			obvincrease5.add(obvlist5.get(i)-obvlist5.get(i-1));
			obvtrend5.add(new TrendPoint(i,obvlist5.get(i)));
			closeincrease5.add(closelist5.get(i)-closelist5.get(i-1));
			closetrend5.add(new TrendPoint(i,closelist5.get(i)));
		}
		TrendFlag obvflag5=new TrendTool(MMSTool.absmean_double(obvincrease5),obvtrend5).trend();
		TrendFlag closeflag5=new TrendTool(MMSTool.absmean_double(closeincrease5),closetrend5).trend();
//		System.out.println("obvflag5 "+obvflag5);
//		System.out.println("closeflag5 "+closeflag5);
		
		obvtrend10.add(new TrendPoint(0,obvlist10.get(0)));
		closetrend10.add(new TrendPoint(0,closelist10.get(0)));
		for(int i=1;i<obvlist10.size();i++)
		{
			obvincrease10.add(obvlist10.get(i)-obvlist10.get(i-1));
			obvtrend10.add(new TrendPoint(i,obvlist10.get(i)));
			closeincrease10.add(closelist10.get(i)-closelist10.get(i-1));
			closetrend10.add(new TrendPoint(i,closelist10.get(i)));
		}
		TrendFlag obvflag10=new TrendTool(MMSTool.absmean_double(obvincrease10),obvtrend10).trend();
		TrendFlag closeflag10=new TrendTool(MMSTool.absmean_double(closeincrease10),closetrend10).trend();
//		System.out.println("obvflag10 "+obvflag10);
//		System.out.println("closeflag10 "+closeflag10);
		
		obvtrend20.add(new TrendPoint(0,obvlist20.get(0)));
		closetrend20.add(new TrendPoint(0,closelist20.get(0)));
		for(int i=1;i<obvlist20.size();i++)
		{
			obvincrease20.add(obvlist20.get(i)-obvlist20.get(i-1));
			obvtrend20.add(new TrendPoint(i,obvlist20.get(i)));
			closeincrease20.add(closelist20.get(i)-closelist20.get(i-1));
			closetrend20.add(new TrendPoint(i,closelist20.get(i)));
		}
		TrendFlag obvflag20=new TrendTool(MMSTool.absmean_double(obvincrease20),obvtrend20).trend();
		TrendFlag closeflag20=new TrendTool(MMSTool.absmean_double(closeincrease20),closetrend20).trend();
//		System.out.println("obvflag20 "+obvflag20);
//		System.out.println("closeflag20 "+closeflag20);
		
		double scoreOut5=0;
		double scoreIn5=0;

//		1、当股价上升而OBV线下降，表示买盘无力，股价可能会回跌。
		if(closeflag5.trend>0&&obvflag5.trend<0)
		{
			scoreOut5=80;
			analysis.message+="超短期股价上升而OBV线下降，买盘无力，股价可能会回跌 建议卖出.\n";
		}
//		2、股价下降时而OBV线上升，表示买盘旺盛，逢低接手强股，股价可能会止跌回升。
		if(closeflag5.trend<0&&obvflag5.trend>0&&obvflag5.trend<Math.PI/4)
		{
			scoreIn5=80;
			analysis.message+="超短期股价下降时而OBV线上升，买盘旺盛，股价可能会止跌回升，可以逢低接手强股.\n";
		}
//		3、OBV线急速上升时，表示力量将用尽为卖出信号。
		if(obvflag5.trend>Math.PI/4)
		{
			scoreOut5=Math.min(Math.max(80,scoreOut5+10),100);
			analysis.message+="超短期OBV线急速上升，力量将用尽,建议卖出.\n";
		}
//		3.5、(股价上涨且)OBV线缓慢上升，表示买气逐渐加强，为买进信号。
		if(obvflag5.trend<Math.PI/4&&closeflag5.trend>0)
		{
			scoreIn5=Math.min(Math.max(80,scoreIn5+10),100);
			analysis.message+="超短期OBV线缓慢上升，股价上涨，表示买气逐渐加强，可以买进.\n";
		}
//		4、OBV线从正的累积数转为负数时，为下跌趋势，应该卖出持有股票。
//			反之，OBV线从负的累积数转为正数时，应该买进股票。
		if(obvflag5.trend<0&&obvlist5.get(0)>0&&obvlist5.get(obvlist5.size()-1)<0)
		{
			scoreOut5=Math.min(Math.max(80,scoreOut5+10),100);
			analysis.message+="超短期OBV线从正的累积数转为负数时，为下跌趋势，建议卖出.\n";
		}
		if(obvflag5.trend>0&&obvlist5.get(0)<0&&obvlist5.get(obvlist5.size()-1)>0)
		{
			scoreIn5=Math.min(Math.max(80,scoreIn5+10),100);
			analysis.message+="超短期OBV线从负的累积数转为正数时，为上涨趋势，建议买进.\n";
		}
		
		double scoreOut10=0;
		double scoreIn10=0;

//		1、当股价上升而OBV线下降，表示买盘无力，股价可能会回跌。
		if(closeflag10.trend>0&&obvflag10.trend<0)
		{
			scoreOut10=80;
			analysis.message+="短期股价上升而OBV线下降，买盘无力，股价可能会回跌 建议卖出.\n";
		}
//		2、股价下降时而OBV线上升，表示买盘旺盛，逢低接手强股，股价可能会止跌回升。
		if(closeflag10.trend<0&&obvflag10.trend>0&&obvflag10.trend<Math.PI/4)
		{
			scoreIn10=80;
			analysis.message+="短期股价下降时而OBV线上升，买盘旺盛，股价可能会止跌回升，可以逢低接手强股.\n";
		}
//		3、OBV线急速上升时，表示力量将用尽为卖出信号。
		if(obvflag10.trend>Math.PI/4)
		{
			scoreOut10=Math.min(Math.max(80,scoreOut10+10),100);
			analysis.message+="短期OBV线急速上升，力量将用尽,建议卖出.\n";
		}
//		3.5、(股价上涨且)OBV线缓慢上升，表示买气逐渐加强，为买进信号。
		if(obvflag10.trend<Math.PI/4&&closeflag10.trend>0)
		{
			scoreIn10=Math.min(Math.max(80,scoreIn10+10),100);
			analysis.message+="短期OBV线缓慢上升，股价上涨，表示买气逐渐加强，可以买进.\n";
		}
//		4、OBV线从正的累积数转为负数时，为下跌趋势，应该卖出持有股票。
//			反之，OBV线从负的累积数转为正数时，应该买进股票。
		if(obvflag10.trend<0&&obvlist10.get(0)>0&&obvlist10.get(obvlist10.size()-1)<0)
		{
			scoreOut10=Math.min(Math.max(80,scoreOut10+10),100);
			analysis.message+="短期OBV线从正的累积数转为负数时，为下跌趋势，建议卖出.\n";
		}
		if(obvflag10.trend>0&&obvlist10.get(0)<0&&obvlist10.get(obvlist10.size()-1)>0)
		{
			scoreIn10=Math.min(Math.max(80,scoreIn10+10),100);
			analysis.message+="短期OBV线从负的累积数转为正数时，为上涨趋势，建议买进.\n";
		}
		
		double scoreOut20=0;
		double scoreIn20=0;

//		1、当股价上升而OBV线下降，表示买盘无力，股价可能会回跌。
		if(closeflag20.trend>0&&obvflag20.trend<0)
		{
			scoreOut20=80;
			analysis.message+="中期股价上升而OBV线下降，买盘无力，股价可能会回跌 建议卖出.\n";
		}
//		2、股价下降时而OBV线上升，表示买盘旺盛，逢低接手强股，股价可能会止跌回升。
		if(closeflag20.trend<0&&obvflag20.trend>0&&obvflag20.trend<Math.PI/4)
		{
			scoreIn20=80;
			analysis.message+="中期股价下降时而OBV线上升，买盘旺盛，股价可能会止跌回升，可以逢低接手强股.\n";
		}
//		3、OBV线急速上升时，表示力量将用尽为卖出信号。
		if(obvflag20.trend>Math.PI/4)
		{
			scoreOut20=Math.min(Math.max(80,scoreOut20+10),100);
			analysis.message+="中期OBV线急速上升，力量将用尽,建议卖出.\n";
		}
//		3.5、(股价上涨且)OBV线缓慢上升，表示买气逐渐加强，为买进信号。
		if(obvflag20.trend<Math.PI/4&&closeflag20.trend>0)
		{
			scoreIn20=Math.min(Math.max(80,scoreIn20+10),100);
			analysis.message+="中期OBV线缓慢上升，股价上涨，表示买气逐渐加强，可以买进.\n";
		}
//		4、OBV线从正的累积数转为负数时，为下跌趋势，应该卖出持有股票。
//			反之，OBV线从负的累积数转为正数时，应该买进股票。
		if(obvflag20.trend<0&&obvlist20.get(0)>0&&obvlist20.get(obvlist20.size()-1)<0)
		{
			scoreOut20=Math.min(Math.max(80,scoreOut20+10),100);
			analysis.message+="中期OBV线从正的累积数转为负数时，为下跌趋势，建议卖出.\n";
		}
		if(obvflag20.trend>0&&obvlist20.get(0)<0&&obvlist20.get(obvlist20.size()-1)>0)
		{
			scoreIn20=Math.min(Math.max(80,scoreIn20+10),100);
			analysis.message+="中期OBV线从负的累积数转为正数时，为上涨趋势，建议买进.\n";
		}
		analysis.scoreIn=DataHelper.controldigit((scoreIn5+scoreIn10+scoreIn20)/3);
		analysis.scoreOut=DataHelper.controldigit((scoreOut5+scoreOut10+scoreOut20)/3);
		return analysis;
	}
	public static void main(String[] args)
	{
		QuotaAnalysis analysis=new QuotaAnalysis();
		double closeslowspeed5=Math.PI/6;
		double obvslowspeed5=Math.PI/6;
		TrendFlag obvflag5=new TrendFlag(null);
		TrendFlag closeflag5=new TrendFlag(null);
																				obvflag5.trend=Math.PI/6;
																				closeflag5.trend=Math.PI/6;
		double scoreOut5=0;
		double scoreIn5=0;
		double p=(obvflag5.trend-closeflag5.trend)/2;
//		1、当股价上升而OBV线下降，表示买盘无力，股价可能会回跌。
		if(closeflag5.trend>=closeslowspeed5&&obvflag5.trend<=-obvslowspeed5)
		{
			System.out.println(p);
			scoreOut5=Math.sqrt(
				(1-
					Math.pow(p-(-Math.PI/2),2)/
					Math.pow(-(obvslowspeed5+closeslowspeed5)/2-(-Math.PI/2),2))
				*1600)+60;
			scoreIn5=0;
			analysis.message+="股价上升而OBV线下降，买盘无力，股价可能会回跌 建议卖出.\n";
		}
//		1.5
		if(closeflag5.trend<closeslowspeed5&&closeflag5.trend>-closeslowspeed5
				&&obvflag5.trend>-obvslowspeed5&&obvflag5.trend<obvslowspeed5)
		{
			scoreOut5=-Math.sqrt(
					(1-
						Math.pow(p-(obvslowspeed5+closeslowspeed5)/2,2)/
						Math.pow(obvslowspeed5+closeslowspeed5,2))
					*3600)+60;
			scoreIn5=-Math.sqrt(
					(1-
						Math.pow(p-(-(obvslowspeed5+closeslowspeed5)/2),2)/
						Math.pow(obvslowspeed5+closeslowspeed5,2))
					*3600)+60;
			if(closeflag5.trend>0)
			{
				if(obvflag5.trend<0)
				{
					analysis.message+="股价缓慢上涨，OBV缓慢下跌，建议观望.\n";
				}
				else
				{
					double q=(obvflag5.trend+closeflag5.trend)/2;
					scoreIn5=-Math.sqrt(
							(1-
								Math.pow(q-(-(obvslowspeed5+closeslowspeed5)/2),2)/
								Math.pow(obvslowspeed5+closeslowspeed5,2))
							*3600)+60+20;
					analysis.message+="股价缓慢上涨，OBV缓慢上涨，可以买进.\n";
				}
			}
			else
			{
				if(obvflag5.trend<0)
				{
					scoreOut5+=20;
					analysis.message+="股价缓慢下跌，OBV缓慢下跌，建议卖出.\n";
				}
				else
				{
					analysis.message+="股价缓慢下跌，OBV缓慢上涨，建议观望.\n";
				}
			}
		}
//		2、股价下降时而OBV线上升，表示买盘旺盛，逢低接手强股，股价可能会止跌回升。
		if(closeflag5.trend<=-closeslowspeed5&&obvflag5.trend>=obvslowspeed5)
		{
			scoreIn5=Math.sqrt(
				(1-
					Math.pow(p-Math.PI/2,2)/
					Math.pow(-(obvslowspeed5+closeslowspeed5)/2-(-Math.PI/2),2))
				*1600)+60;
			scoreOut5=0;
			analysis.message+="股价下降时而OBV线上升，买盘旺盛，股价可能会止跌回升，可以逢低接手强股.\n";
		}
		analysis.scoreIn=scoreIn5;
		analysis.scoreOut=scoreOut5;
		System.out.println(analysis);
	}
}
