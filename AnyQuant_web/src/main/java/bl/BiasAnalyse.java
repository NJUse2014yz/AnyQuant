package bl;

import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import javassist.bytecode.analysis.Analyzer;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import vo.QuotaAnalysis;
import mapper.QuotaDataMapper;
import po.DatePack;
import po.QuotaData;
import pr.QuotaDataPr;
import tool.MMSTool;
import data.QuotaService;
import data.impl.QuotaServiceImpl;

/**
 * 乖离率指标的分析
 * @author YU Fan
 * @date 2016年5月31日
 */
public class BiasAnalyse extends QuotaAnalyse{
	private Date startdate5;
	private Date startdate10;
	private Date startdate20;
	private double separator5_up;
	private double separator5_mid;
	private double separator5_down;
	private double separator10_up;
	private double separator10_mid;
	private double separator10_down;
	private double separator20_up;
	private double separator20_mid;
	private double separator20_down;
	
	
	public BiasAnalyse(String siid)
	{
		super(siid);
		this.startdate5=new Date(0,0,1);
		this.startdate10=new Date(0,0,1);
		this.startdate20=new Date(0,0,1);
		this.separator5_up=0.8;
		this.separator5_mid=0.6;
		this.separator5_down=0.4;
		this.separator10_up=0.8;
		this.separator10_mid=0.6;
		this.separator10_down=0.4;
		this.separator20_up=0.8;
		this.separator20_mid=0.6;
		this.separator20_down=0.4;
		analysis.message="bias: ";
	}
	public BiasAnalyse(String type,String siid)
	{
		super(type,siid);
		this.startdate5=new Date(0,0,1);
		this.startdate10=new Date(0,0,1);
		this.startdate20=new Date(0,0,1);
		this.separator5_up=0.8;
		this.separator5_mid=0.6;
		this.separator5_down=0.4;
		this.separator10_up=0.8;
		this.separator10_mid=0.6;
		this.separator10_down=0.4;
		this.separator20_up=0.8;
		this.separator20_mid=0.6;
		this.separator20_down=0.4;
		analysis.message="bias: ";
	}
	public BiasAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		this.startdate5=new Date(0,0,1);
		this.startdate10=new Date(0,0,1);
		this.startdate20=new Date(0,0,1);
		this.separator5_up=0.8;
		this.separator5_mid=0.6;
		this.separator5_down=0.4;
		this.separator10_up=0.8;
		this.separator10_mid=0.6;
		this.separator10_down=0.4;
		this.separator20_up=0.8;
		this.separator20_mid=0.6;
		this.separator20_down=0.4;
		analysis.message="bias: ";
	}
	public BiasAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		this.startdate5=new Date(0,0,1);
		this.startdate10=new Date(0,0,1);
		this.startdate20=new Date(0,0,1);
		this.separator5_up=0.8;
		this.separator5_mid=0.6;
		this.separator5_down=0.4;
		this.separator10_up=0.8;
		this.separator10_mid=0.6;
		this.separator10_down=0.4;
		this.separator20_up=0.8;
		this.separator20_mid=0.6;
		this.separator20_down=0.4;
		analysis.message="bias: ";
	}
	
	/**乖离率处理，param：股票代码、bias5、10、20取一段值的基准日期、(90分、60分、0分)分位点取值*/
	public BiasAnalyse(String siid,Date startdate5,Date startdate10,Date startdate20,
			double separator5_up,double separator10_up,double separator20_up,
			double separator5_mid,double separator10_mid,double separator20_mid,
			double separator5_down,double separator10_down,double separator20_down)
	{
		super(siid);
		this.startdate5=startdate5;
		this.startdate10=startdate10;
		this.startdate20=startdate20;
		this.separator5_up=separator5_up;
		this.separator10_up=separator10_up;
		this.separator20_up=separator20_up;
		this.separator5_mid=separator5_mid;
		this.separator10_mid=separator10_mid;
		this.separator20_mid=separator20_mid;
		this.separator5_down=separator5_down;
		this.separator10_down=separator10_down;
		this.separator20_down=separator20_down;
		analysis.message="bias: ";
	}
	/**乖离率处理，param：类型、代码、bias5、10、20取一段值的基准日期、(90分、60分、0分)分位点取值*/
	public BiasAnalyse(String type,String siid,Date startdate5,Date startdate10,Date startdate20,
			double separator5_up,double separator10_up,double separator20_up,
			double separator5_mid,double separator10_mid,double separator20_mid,
			double separator5_down,double separator10_down,double separator20_down)
	{
		super(type,siid);
		this.startdate5=startdate5;
		this.startdate10=startdate10;
		this.startdate20=startdate20;
		this.separator5_up=separator5_up;
		this.separator10_up=separator10_up;
		this.separator20_up=separator20_up;
		this.separator5_mid=separator5_mid;
		this.separator10_mid=separator10_mid;
		this.separator20_mid=separator20_mid;
		this.separator5_down=separator5_down;
		this.separator10_down=separator10_down;
		this.separator20_down=separator20_down;
		analysis.message="bias: ";
	}
	/**乖离率处理，param：股票代码、bias5、10、20取一段值的基准日期、(90分、60分、0分)分位点取值*/
	public BiasAnalyse(String siid,Date enddate,Date startdate5,Date startdate10,Date startdate20,
			double separator5_up,double separator10_up,double separator20_up,
			double separator5_mid,double separator10_mid,double separator20_mid,
			double separator5_down,double separator10_down,double separator20_down)
	{
		super(siid,enddate);
		this.startdate5=startdate5;
		this.startdate10=startdate10;
		this.startdate20=startdate20;
		this.separator5_up=separator5_up;
		this.separator10_up=separator10_up;
		this.separator20_up=separator20_up;
		this.separator5_mid=separator5_mid;
		this.separator10_mid=separator10_mid;
		this.separator20_mid=separator20_mid;
		this.separator5_down=separator5_down;
		this.separator10_down=separator10_down;
		this.separator20_down=separator20_down;
		analysis.message="bias: ";
	}
	/**乖离率处理，param：类型、代码、bias5、10、20取一段值的基准日期、(90分、60分、0分)分位点取值*/
	public BiasAnalyse(String type,String siid,Date enddate,Date startdate5,Date startdate10,Date startdate20,
			double separator5_up,double separator10_up,double separator20_up,
			double separator5_mid,double separator10_mid,double separator20_mid,
			double separator5_down,double separator10_down,double separator20_down)
	{
		super(type,siid,enddate);
		this.startdate5=startdate5;
		this.startdate10=startdate10;
		this.startdate20=startdate20;
		this.separator5_up=separator5_up;
		this.separator10_up=separator10_up;
		this.separator20_up=separator20_up;
		this.separator5_mid=separator5_mid;
		this.separator10_mid=separator10_mid;
		this.separator20_mid=separator20_mid;
		this.separator5_down=separator5_down;
		this.separator10_down=separator10_down;
		this.separator20_down=separator20_down;
		analysis.message="bias: ";
	}
	/**评分标准：用当日的超短期(5日)、短期(10日)、中期(20日)乖离率的数值大小判断买入卖出情况
	 * 达到或超过历史最值记100分，到mid记60分，到down及至0记0分
	 * scoreOut=100 												,bias<=negUp
	 * 			(60-100)/(negMid-negHisM)^2 * (bias-negHisM)^2 +100 ,negHisM<=bias<=negDown
	 * 			0 													,bias>=posDown
	 * scoreIn=	0 													,bias<=posDown
	 * 			(60-100)/(posMid-posHisM)^2 * (bias-posHisM)^2 +100 ,posDown<=bias<=posHisM
	 * 			100 												,bias>=posHisM
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		
		DatePack datePack5=new DatePack();
		datePack5.setSiid(siid);
		datePack5.setDate1(startdate5);
		datePack5.setDate2(enddate);
		
		//bias5最值
		double bias5max=quotaDataMapper.selectBias5Max_b_date(datePack5);
		double bias5min=quotaDataMapper.selectBias5Min_b_date(datePack5);
		
		//bias5分位数
		List<Double> posibias5=quotaDataMapper.selectBias5Posi_12_b_date(datePack5);
		List<Double> negabias5=quotaDataMapper.selectBias5Nega_21_b_date(datePack5);
		int posibias5size=posibias5.size();
		int negabias5size=negabias5.size();
		
		//上分位
		double posibias5_up=3;
		double negabias5_up=-3;
		//中分位
		double posibias5_mid=2;
		double negabias5_mid=-2;
		//下分位
		double posibias5_down=1;
		double negabias5_down=-1;
		
		if(negabias5size>4)
		{
			negabias5_up=negabias5.get((int)(negabias5size*separator5_up));
			negabias5_mid=negabias5.get((int)(negabias5size*separator5_mid));
			negabias5_down=negabias5.get((int)(negabias5size*separator5_down));
			
		}
		if(posibias5size>4)
		{
			posibias5_up=posibias5.get((int)(posibias5size*separator5_up));
			posibias5_mid=posibias5.get((int)(posibias5size*separator5_mid));
			posibias5_down=posibias5.get((int)(posibias5size*separator5_down));
			
		}
		
		DatePack datePack10=new DatePack();
		datePack10.setSiid(siid);
		datePack10.setDate1(startdate10);
		datePack10.setDate2(enddate);
		
		//bias10最值
		double bias10max=quotaDataMapper.selectBias10Max_b_date(datePack10);
		double bias10min=quotaDataMapper.selectBias10Min_b_date(datePack10);
		
		//bias10分位数
		List<Double> posibias10=quotaDataMapper.selectBias10Posi_12_b_date(datePack10);
		List<Double> negabias10=quotaDataMapper.selectBias10Nega_12_b_date(datePack10);
		int posibias10size=posibias10.size();
		int negabias10size=negabias10.size();
		
		//上分位
		double posibias10_up=5;
		double negabias10_up=-4.5;
		//中分位
		double posibias10_mid=3;
		double negabias10_mid=-3;
		//下分位
		double posibias10_down=2;
		double negabias10_down=-2;
		
		if(negabias10size>4)
		{
			negabias10_up=negabias10.get((int)(negabias10size*separator10_up));
			negabias10_mid=negabias10.get((int)(negabias10size*separator10_mid));
			negabias10_down=negabias10.get((int)(negabias10size*separator10_down));
			
		}
		if(posibias10size>4)
		{
			posibias10_up=posibias10.get((int)(posibias10size*separator10_up));
			posibias10_mid=posibias10.get((int)(posibias10size*separator10_mid));
			posibias10_down=posibias10.get((int)(posibias10size*separator10_down));
			
		}
		
		DatePack datePack20=new DatePack();
		datePack20.setSiid(siid);
		datePack20.setDate1(startdate20);
		datePack20.setDate2(enddate);
		
		//bias20最值
		double bias20max=quotaDataMapper.selectBias20Max_b_date(datePack20);
		double bias20min=quotaDataMapper.selectBias20Min_b_date(datePack20);
		
		//bias20分位数
		List<Double> posibias20=quotaDataMapper.selectBias20Posi_12_b_date(datePack20);
		List<Double> negabias20=quotaDataMapper.selectBias20Nega_12_b_date(datePack20);
		int posibias20size=posibias20.size();
		int negabias20size=negabias20.size();
		
		//上分位
		double posibias20_up=8;
		double negabias20_up=-7;
		//中分位
		double posibias20_mid=6;
		double negabias20_mid=-5;
		//下分位
		double posibias20_down=3;
		double negabias20_down=-3;
		
		if(negabias20size>4)
		{
			negabias20_up=negabias20.get((int)(negabias20size*separator20_up));
			negabias20_mid=negabias20.get((int)(negabias20size*separator20_mid));
			negabias20_down=negabias20.get((int)(negabias20size*separator20_down));
			
		}
		if(posibias20size>4)
		{
			posibias20_up=posibias20.get((int)(posibias20size*separator20_up));
			posibias20_mid=posibias20.get((int)(posibias20size*separator20_mid));
			posibias20_down=posibias20.get((int)(posibias20size*separator20_down));
			
		}
		QuotaData quotaData_new=new QuotaDataPr().getQuotaData_new(type,siid);
		double bias5_new=quotaData_new.getBias5();
		double bias10_new=quotaData_new.getBias10();
		double bias20_new=quotaData_new.getBias20();
		
		//-------------------------
//		DatePack datePackTest=new DatePack();
//		datePackTest.setSiid(siid);
//		datePackTest.setId1((quotaData_new.getId())-44);
//		QuotaData testdata=quotaDataMapper.selectQuotaData_e_id(datePackTest);
//		double bias5_new=testdata.getBias5();
//		double bias10_new=testdata.getBias10();
//		double bias20_new=testdata.getBias20();		
		//--------------------------
		
		double scoreIn5=0;
		double scoreIn10=0;
		double scoreIn20=0;
		double scoreOut5=0;
		double scoreOut10=0;
		double scoreOut20=0;
		
		//scoreIn5
		if(bias5_new<=bias5min)
		{
			scoreIn5=100;
			scoreOut5=0;
			analysis.message+="bias5 达到历史最低值，强烈建议买入，禁止卖出\n";
		}
		else if(bias5_new>bias5min&&bias5_new<=negabias5_up)
		{
			if(negabias5_up-bias5min!=0)
			{
				scoreIn5=(negabias5_up-bias5_new)/(negabias5_up-bias5min)*10+90;
				scoreOut5=0;
				analysis.message+="bias5 负偏离程度很大，强烈建议买入,禁止卖出\n";
			}
		}
		else if(bias5_new>negabias5_up&&bias5_new<=negabias5_mid)
		{
			if(negabias5_mid-negabias5_up!=0)
			{
				scoreIn5=(negabias5_mid-bias5_new)/(negabias5_mid-negabias5_up)*30+60;
				scoreOut5=0;
				analysis.message+="bias5 负偏离程度较大，建议买入,禁止卖出\n";
			}
		}
		else if(bias5_new>negabias5_mid&&bias5_new<=negabias5_down)
		{
			if(negabias5_down-negabias5_mid!=0)
			{
				scoreIn5=(negabias5_down-bias5_new)/(negabias5_down-negabias5_mid)*60+0;
				scoreOut5=0;
				analysis.message+="bias5 负偏离程度一般，不建议买入或卖出\n";
			}
		}
		else if(bias5_new>negabias5_down&&bias5_new<=0)
		{
			scoreIn5=0;
			scoreOut5=0;
			analysis.message+="bias5 负偏离程度很小，不建议买入或卖出\n";
		}
		//scoreOut5
		if(bias5_new>=bias5max)
		{
			scoreOut5=100;
			scoreIn5=0;
			analysis.message+="bias5 达到历史最高值，强烈建议卖出，禁止买入\n";
		}
		else if(bias5_new<bias5max&&bias5_new>=posibias5_up)
		{
			if(bias5max-posibias5_up!=0)
			{
				scoreOut5=(bias5_new-posibias5_up)/(bias5max-posibias5_up)*10+90;
				scoreIn5=0;
				analysis.message+="bias5 正偏离程度很大，强烈建议卖出，禁止买入\n";
			}
		}
		else if(bias5_new<posibias5_up&&bias5_new>=posibias5_mid)
		{
			if(posibias5_up-posibias5_mid!=0)
			{
				scoreOut5=(bias5_new-posibias5_mid)/(posibias5_up-posibias5_mid)*30+60;
				scoreIn5=0;
				analysis.message+="bias5 正偏离程度较大，建议卖出，禁止买入\n";
			}
		}
		else if(bias5_new<posibias5_mid&&bias5_new>=posibias5_down)
		{
			if(posibias5_mid-posibias5_down!=0)
			{
				scoreOut5=(bias5_new-posibias5_down)/(posibias5_mid-posibias5_down)*60+0;
				scoreIn5=0;
				analysis.message+="bias5 正偏离程度一般，不建议买入或卖出\n";
			}
		}
		else if(bias5_new<posibias5_down&&bias5_new>=0)
		{
			scoreOut5=0;
			scoreIn5=0;
			analysis.message+="bias5 正偏离程度很小，不建议买入或卖出\n";
		}
		//scoreIn10
		if(bias10_new<=bias10min)
		{
			scoreIn10=100;
			scoreOut10=0;
			analysis.message+="bias10 达到历史最低值，强烈建议买入，禁止卖出\n";
		}
		else if(bias10_new>bias10min&&bias10_new<=negabias10_up)
		{
			if(negabias10_up-bias10min!=0)
			{
				scoreIn10=(negabias10_up-bias10_new)/(negabias10_up-bias10min)*10+90;
				scoreOut10=0;
				analysis.message+="bias10 负偏离程度很大，强烈建议买入,禁止卖出\n";
			}
		}
		else if(bias10_new>negabias10_up&&bias10_new<=negabias10_mid)
		{
			if(negabias10_mid-negabias10_up!=0)
			{
				scoreIn10=(negabias10_mid-bias10_new)/(negabias10_mid-negabias10_up)*30+60;
				scoreOut10=0;
				analysis.message+="bias10 负偏离程度较大，建议买入,禁止卖出\n";
			}
		}
		else if(bias10_new>negabias10_mid&&bias10_new<=negabias10_down)
		{
			if(negabias10_down-negabias10_mid!=0)
			{
				scoreIn10=(negabias10_down-bias10_new)/(negabias10_down-negabias10_mid)*60+0;
				scoreOut10=0;
				analysis.message+="bias10 负偏离程度一般，不建议买入或卖出\n";
			}
		}
		else if(bias10_new>negabias10_down&&bias10_new<=0)
		{
			scoreIn10=0;
			scoreOut10=0;
			analysis.message+="bias10 负偏离程度很小，不建议买入或卖出\n";
		}
		//scoreOut10
		if(bias10_new>=bias10max)
		{
			scoreOut10=100;
			scoreIn10=0;
			analysis.message+="bias10 达到历史最高值，强烈建议卖出，禁止买入\n";
		}
		else if(bias10_new<bias10max&&bias10_new>=posibias10_up)
		{
			if(bias10max-posibias10_up!=0)
			{
				scoreOut10=(bias10_new-posibias10_up)/(bias10max-posibias10_up)*10+90;
				scoreIn10=0;
				analysis.message+="bias10 正偏离程度很大，强烈建议卖出，禁止买入\n";
			}
		}
		else if(bias10_new<posibias10_up&&bias10_new>=posibias10_mid)
		{
			if(posibias10_up-posibias10_mid!=0)
			{
				scoreOut10=(bias10_new-posibias10_mid)/(posibias10_up-posibias10_mid)*30+60;
				scoreIn10=0;
				analysis.message+="bias10 正偏离程度较大，建议卖出，禁止买入\n";
			}
		}
		else if(bias10_new<posibias10_mid&&bias10_new>=posibias10_down)
		{
			if(posibias10_mid-posibias10_down!=0)
			{
				scoreOut10=(bias10_new-posibias10_down)/(posibias10_mid-posibias10_down)*60+0;
				scoreIn10=0;
				analysis.message+="bias10 正偏离程度一般，不建议买入或卖出\n";
			}
		}
		else if(bias10_new<posibias10_down&&bias10_new>=0)
		{
			scoreOut10=0;
			scoreIn10=0;
			analysis.message+="bias10 正偏离程度很小，不建议买入或卖出\n";
		}
		//scoreIn20
		if(bias20_new<=bias20min)
		{
			scoreIn20=100;
			scoreOut20=0;
			analysis.message+="bias20 达到历史最低值，强烈建议买入，禁止卖出\n";
		}
		else if(bias20_new>bias20min&&bias20_new<=negabias20_up)
		{
			if(negabias20_up-bias20min!=0)
			{
				scoreIn20=(negabias20_up-bias20_new)/(negabias20_up-bias20min)*10+90;
				scoreOut20=0;
				analysis.message+="bias20 负偏离程度很大，强烈建议买入,禁止卖出\n";
			}
		}
		else if(bias20_new>negabias20_up&&bias20_new<=negabias20_mid)
		{
			if(negabias20_mid-negabias20_up!=0)
			{
				scoreIn20=(negabias20_mid-bias20_new)/(negabias20_mid-negabias20_up)*30+60;
				scoreOut20=0;
				analysis.message+="bias20 负偏离程度较大，建议买入,禁止卖出\n";
			}
		}
		else if(bias20_new>negabias20_mid&&bias20_new<=negabias20_down)
		{
			if(negabias20_down-negabias20_mid!=0)
			{
				scoreIn20=(negabias20_down-bias20_new)/(negabias20_down-negabias20_mid)*60+0;
				scoreOut20=0;
				analysis.message+="bias20 负偏离程度一般，不建议买入或卖出\n";
			}
		}
		else if(bias20_new>negabias20_down&&bias20_new<=0)
		{
			scoreIn20=0;
			scoreOut20=0;
			analysis.message+="bias20 负偏离程度很小，不建议买入或卖出\n";
		}
		//scoreOut20
		if(bias20_new>=bias20max)
		{
			scoreOut20=100;
			scoreIn20=0;
			analysis.message+="bias20 达到历史最高值，强烈建议卖出，禁止买入\n";
		}
		else if(bias20_new<bias20max&&bias20_new>=posibias20_up)
		{
			if(bias20max-posibias20_up!=0)
			{
				scoreOut20=(bias20_new-posibias20_up)/(bias20max-posibias20_up)*10+90;
				scoreIn20=0;
				analysis.message+="bias20 正偏离程度很大，强烈建议卖出，禁止买入\n";
			}
		}
		else if(bias20_new<posibias20_up&&bias20_new>=posibias20_mid)
		{
			if(posibias20_up-posibias20_mid!=0)
			{
				scoreOut20=(bias20_new-posibias20_mid)/(posibias20_up-posibias20_mid)*30+60;
				scoreIn20=0;
				analysis.message+="bias20 正偏离程度较大，建议卖出，禁止买入\n";
			}
		}
		else if(bias20_new<posibias20_mid&&bias20_new>=posibias20_down)
		{
			if(posibias20_mid-posibias20_down!=0)
			{
				scoreOut20=(bias20_new-posibias20_down)/(posibias20_mid-posibias20_down)*60+0;
				scoreIn20=0;
				analysis.message+="bias20 正偏离程度一般，不建议买入或卖出\n";
			}
		}
		else if(bias20_new<posibias20_down&&bias20_new>=0)
		{
			scoreOut20=0;
			scoreIn20=0;
			analysis.message+="bias20 正偏离程度很小，不建议买入或卖出\n";
		}
		analysis.scoreIn=DataHelper.controldigit((scoreIn5+scoreIn10+scoreIn20)/3);
		analysis.scoreOut=DataHelper.controldigit((scoreOut5+scoreOut10+scoreOut20)/3);
		
//		System.out.println("bias5max "+bias5max);
//		System.out.println("posibias5_up "+posibias5_up);
//		System.out.println("posibias5_mid "+posibias5_mid);
//		System.out.println("posibias5_down "+posibias5_down);
//		System.out.println("negabias5_down "+negabias5_down);
//		System.out.println("negabias5_mid "+negabias5_mid);
//		System.out.println("negabias5_up "+negabias5_up);
//		System.out.println("bias5min "+bias5min);
//		System.out.println();
//		
//		System.out.println("bias10max "+bias10max);
//		System.out.println("posibias10_up "+posibias10_up);
//		System.out.println("posibias10_mid "+posibias10_mid);
//		System.out.println("posibias10_down "+posibias10_down);
//		System.out.println("negabias10_down "+negabias10_down);
//		System.out.println("negabias10_mid "+negabias10_mid);
//		System.out.println("negabias10_up "+negabias10_up);
//		System.out.println("bias10min "+bias10min);
//		System.out.println();
//		
//		System.out.println("bias20max "+bias20max);
//		System.out.println("posibias20_up "+posibias20_up);
//		System.out.println("posibias20_mid "+posibias20_mid);
//		System.out.println("posibias20_down "+posibias20_down);
//		System.out.println("negabias20_down "+negabias20_down);
//		System.out.println("negabias20_mid "+negabias20_mid);
//		System.out.println("negabias20_up "+negabias20_up);
//		System.out.println("bias20min "+bias20min);
//		System.out.println();
//		
//		System.out.println("bias5_new "+bias5_new);
//		System.out.println("bias10_new "+bias10_new);
//		System.out.println("bias20_new "+bias20_new);
//		System.out.println();
//		
//		System.out.println("scoreIn5 "+scoreIn5);
//		System.out.println("scoreIn10 "+scoreIn10);
//		System.out.println("scoreIn20 "+scoreIn20);
//		System.out.println("scoreOut5 "+scoreOut5);
//		System.out.println("scoreOut10 "+scoreOut10);
//		System.out.println("scoreOut20 "+scoreOut20);
		
		return analysis;
	}
	
	public static void main(String[] args)
	{
		try {
			System.out.println(new BiasAnalyse("sh600007").pro_quo());
//			System.out.println(analysis);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
}
