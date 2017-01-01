package bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

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

public class MacdAnalyse extends QuotaAnalyse{
	private double deviation;
	
	public MacdAnalyse(String siid)
	{
		super(siid);
		this.deviation=0.05;
		analysis.message="macd: ";
	}
	public MacdAnalyse(String type,String siid)
	{
		super(type,siid);
		this.deviation=0.05;
		analysis.message="macd: ";
	}
	public MacdAnalyse(String siid,double deviation)
	{
		super(siid);
		this.deviation=deviation;
		analysis.message="macd: ";
	}
	public MacdAnalyse(String type,String siid,double deviation)
	{
		super(type,siid);
		this.deviation=deviation;
		analysis.message="macd: ";
	}
	public MacdAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		this.deviation=0.05;
		analysis.message="macd: ";
	}
	public MacdAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		this.deviation=0.05;
		analysis.message="macd: ";
	}
	public MacdAnalyse(String siid,double deviation,Date enddate)
	{
		super(siid,enddate);
		this.deviation=deviation;
		analysis.message="macd: ";
	}
	public MacdAnalyse(String type,String siid,Date enddate,double deviation)
	{
		super(type,siid,enddate);
		this.deviation=deviation;
		analysis.message="macd: ";
	}
	/**评分标准：利用一段时间DIFF、DEA、MACD的数值和交叉判断买入卖出情况
	 * 买/卖点加分
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(enddate);
		QuotaData quotaData_new=quotaDataMapper.selectQuotaData_latest(datePack);
		datePack.setId1(quotaData_new.getId());
		datePack.setNum(5);
		double scoreIn5=0;
		double scoreOut5=0;
		List<Double> difflist5=quotaDataMapper.selectDiff_num_id(datePack);
		List<Double> dealist5=quotaDataMapper.selectDea_num_id(datePack);
		List<Double> macdlist5=quotaDataMapper.selectMacd_num_id(datePack);
		List<Double> zerolist5=new ArrayList<Double>();
		
		List<TrendPoint> difftrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> deatrend5=new ArrayList<TrendPoint>();
		List<TrendPoint> macdtrend5=new ArrayList<TrendPoint>();
		List<Double> diffincrease5=new ArrayList<Double>();
		List<Double> deaincrease5=new ArrayList<Double>();
		List<Double> macdincrease5=new ArrayList<Double>();
		zerolist5.add(0.0);
		difftrend5.add(new TrendPoint(0,difflist5.get(0)));
		deatrend5.add(new TrendPoint(0,difflist5.get(0)));
		macdtrend5.add(new TrendPoint(0,difflist5.get(0)));
//		System.out.println(zerolist5);
		for(int i=1;i<difflist5.size();i++)
		{
			zerolist5.add(0.0);
//			System.out.println(zerolist5);
			difftrend5.add(new TrendPoint(i,difflist5.get(i)));
			diffincrease5.add(difflist5.get(i)-difflist5.get(i-1));
			deatrend5.add(new TrendPoint(i,dealist5.get(i)));
			deaincrease5.add(dealist5.get(i)-dealist5.get(i-1));
			macdtrend5.add(new TrendPoint(i,macdlist5.get(i)));
			macdincrease5.add(macdlist5.get(i)-macdlist5.get(i-1));
		}
		TrendFlag diffflag5=new TrendTool(MMSTool.absmean_double(diffincrease5),difftrend5).trend();
		TrendFlag deaflag5=new TrendTool(MMSTool.absmean_double(deaincrease5),deatrend5).trend();
		TrendFlag macdflag5=new TrendTool(MMSTool.absmean_double(macdincrease5),macdtrend5).trend();
		CrossResult diffdea=new CrossTool(difflist5,dealist5).cross();
//		 * 3. MACD 绿转红：MACD 值由负变正，市场由空头转为多头。
//		 * 4. MACD 红转绿：MACD 值由正变负，市场由多头转为空头。
		if(macdflag5.trend>0&&macdlist5.get(0)<0&&macdlist5.get(macdlist5.size()-1)>0)
		{
			scoreIn5=60;
			analysis.message+="MACD 值由负变正，市场由空头转为多头，建议买入.\n";
		}
		if(macdflag5.trend<0&&macdlist5.get(0)>0&&macdlist5.get(macdlist5.size()-1)<0)
		{
			scoreOut5=60;
			analysis.message+="MACD 值由正变负，市场由多头转为空头，建议卖出.\n";
		}
//		 * 5. DIFF 与 DEA 均为正值,即都在零轴线以上时，大势属多头市场，
//		 * 		DIFF 向上突破 DEA，可作买入信号。
//		 * 6. DIFF 与 DEA 均为负值,即都在零轴线以下时，大势属空头市场，
//		 * 		DIFF 向下跌破 DEA，可作卖出信号。
		
		double diffzero=new UpLowTool(difflist5,zerolist5,deviation).uplow();
		double deazero=new UpLowTool(dealist5,zerolist5,deviation).uplow();
		if(diffzero>=0&&deazero>=0)
		{
			analysis.message+="DIFF与DEA都在零轴线以上时，大势属多头市场.\n";
			if(diffdea.cross>0)
			{
				scoreIn5+=20;
				analysis.message+="DIFF向上突破 DEA，可作买入信号.\n";
			}
		}
		if(diffzero<=0&&deazero<=0)
		{
			analysis.message+="DIFF与DEA都在零轴线以下时，大势属空头市场.\n";
			if(diffdea.cross<0)
			{
				scoreOut5+=20;
				analysis.message+="DIFF向下突破 DEA，可作卖出信号.\n";
			}
		}
		analysis.scoreIn=DataHelper.controldigit(scoreIn5);
		analysis.scoreOut=DataHelper.controldigit(scoreOut5);
		return analysis;
	}
}
