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
import tool.MMSTool;
import tool.TrendPoint;
import tool.TrendTool;
import vo.QuotaAnalysis;

public class KdjAnalyse extends QuotaAnalyse{
	private HistoryDataMapper historyDataMapper;
	
	public KdjAnalyse(String siid)
	{
		super(siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		analysis.message="kdj: ";
	}
	public KdjAnalyse(String type,String siid)
	{
		super(type,siid);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		analysis.message="kdj: ";
	}
	public KdjAnalyse(String siid,Date enddate)
	{
		super(siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		analysis.message="kdj: ";
	}
	public KdjAnalyse(String type,String siid,Date enddate)
	{
		super(type,siid,enddate);
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		analysis.message="kdj: ";
	}
	/**评分标准：利用当日KDJ的数值大小判断买入卖出情况
	 * scoreOutk=	(k-90)/(100-90)*20+80	,90<=k<=100
	 * 				(k-50)/(90-50)*80+0		,50<=k<=90
	 * 				0						,0<=k<=50
	 * scoreInk=	0						,50<=k<=100
	 * 				(k-50)/(10-50)*80+0		,10<=k<=50
	 * 				(k-10)/(0-10)*20+80		,0<=k<=10
	 * scoreOutd=	(d-80)/(100-80)*20+80	,80<=d<=100
	 * 				(d-50)/(80-50)*80+0		,50<=d<=80
	 * 				0						,0<=d<=50
	 * scoreInd=	0						,50<=d<=100
	 * 				(d-50)/(20-50)*80+0		,20<=d<=50
	 * 				(d-20)/(0-20)*20+80		,0<=d<=20
	 * scoreOutj=	-400/(j-90)+100			,j>=100
	 * 				(j-50)/(100-50)*60+0	,50<=j<=100
	 * 				0						,j<=0
	 * scoreInj=	0						,j>=50
	 * 				(j-50)/(0-50)*60+0		,0<=j<=50
	 * 				400/(j-10)+100			,j<=0
	 */
	public QuotaAnalysis pro_quo() throws Exception
	{
		double scoreInK=0;
		double scoreOutK=0;
		double scoreInD=0;
		double scoreOutD=0;
		double scoreInJ=0;
		double scoreOutJ=0;

		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(enddate);
		QuotaData quotaData_new=quotaDataMapper.selectQuotaData_latest(datePack);
		double k=quotaData_new.getK();
		double d=quotaData_new.getD();
		double j=quotaData_new.getJ();
		
		
		if(d>=80&&d<=100)
		{
			scoreOutD=(d-80)/(100-80)*20+80;
			scoreInD=0;
			analysis.message+="d值进入超买区，建议卖出.\n";
		}
		else if(d<=80&&d>=50)
		{
			scoreOutD=(d-50)/(80-50)*80+0;
			scoreInD=0;
		}
		else if(d<=50&&d>=20)
		{
			scoreInD=(d-50)/(20-50)*80+0;
			scoreOutD=0;
		}
		else
		{
			scoreInD=(d-20)/(0-20)*20+80;
			scoreOutD=0;
			analysis.message+="d值进入超卖区，建议买入.\n";
		}
		
		if(k>=90&&k<=100)
		{
			scoreOutK=(k-90)/(100-90)*20+80;
			scoreInK=0;
			analysis.message+="k值进入超买区，建议卖出.\n";
		}
		else if(k<=90&&k>=50)
		{
			scoreOutK=(k-50)/(90-50)*80+0;
			scoreInK=0;
		}
		else if(k<=50&&k>=10)
		{
			scoreInK=(k-50)/(10-50)*80+0;
			scoreOutK=0;
		}
		else
		{
			scoreInK=(k-10)/(0-10)*20+80;
			scoreOutK=0;
			analysis.message+="k值进入超卖区，建议买入.\n";
		}
		
		if(j>=100)
		{
			scoreOutJ=-400/(j-90)+100;
			scoreInJ=0;
			analysis.message+="j值进入超买区，建议卖出.\n";
		}
		else if(j<=100&&j>=50)
		{
			scoreOutJ=(j-50)/(100-50)*60+0;
			scoreInJ=0;
		}
		else if(j>=0&&j<=50)
		{
			scoreInJ=(j-50)/(0-50)*60+0;
			scoreOutJ=0;
		}
		else
		{
			scoreInJ=400/(j-10)+100;
			scoreOutJ=0;
			analysis.message+="j值进入超卖区，建议买入.\n";
		}
		analysis.scoreIn=DataHelper.controldigit((scoreInK+scoreInD+scoreInJ)/3);
		analysis.scoreOut=DataHelper.controldigit((scoreOutK+scoreOutD+scoreOutJ)/3);
		
		return analysis;
	}
}
