package service;

import java.sql.Date;

import vo.QuotaAnalysis;

public interface QuotaAnalyseService {
	/**默认乖离率分析，parameter:股票代码*/
	public QuotaAnalysis getBiasAnalysis(String sid) throws Exception;
	/**自定义乖离率分析,parameter:股票代码，6日基期，12日基期，24日基期，6日上分位点，12日上分位点，24日上分位点，6日中分位点，12日中分位点，24日中分位点，6日下分位点，12日下分位点，24日下分位点，*/
	public QuotaAnalysis getBiasAnalysis(String sid,Date startdate6,Date startdate12,Date startdate24,
			double separator6_up,double separator12_up,double separator24_up,
			double separator6_mid,double separator12_mid,double separator24_mid,
			double separator6_down,double separator12_down,double separator24_down) throws Exception;
	
	/**默认相对强弱指标分析，parameter:股票代码*/
	public QuotaAnalysis getRsiAnalysis(String sid) throws Exception;
	/**自定义相对强弱指标分析，parameter:股票代码，基期，上分位点，中分位点，下分位点*/
	public QuotaAnalysis getRsiAnalysis(String sid, Date startdate,
			double up,double mid,double down, double deviation) throws Exception;
	
	/**默认成交量变异律分析，parameter:股票代码*/
	public QuotaAnalysis getVrAnalysis(String sid) throws Exception;
	/**自定义成交量变异律分析，parameter:股票代码、低价区下界、低价区上界、安全区下界、安全区上界、盈利区下界、盈利区上界（警戒区下界）*/
	public QuotaAnalysis getVrAnalysis(String sid, double in1, double in2,
			double wait1, double wait2, double out1, double out2,
			Date startdate,Date enddate,double deviation) throws Exception;
	
	/**默认能量潮分析，parameter:股票代码*/
	public QuotaAnalysis getObvAnalysis(String sid) throws Exception;
	/**默认能量潮分析，parameter:股票代码*/
	public QuotaAnalysis getObvAnalysis(String sid, double obvfastspeed5, double obvslowspeed5, double closefastspeed5, double closeslowspeed5,
			double obvfastspeed10, double obvslowspeed10, double closefastspeed10, double closeslowspeed10,
			double obvfastspeed20, double obvslowspeed20, double closefastspeed20, double closeslowspeed20) throws Exception;
	
	/**默认布林线分析，parameter:股票代码*/
	public QuotaAnalysis getBollAnalysis(String sid) throws Exception;
	/**自定义布林线分析，parameter:股票代码、变动范围*/
	public QuotaAnalysis getBollAnalysis(String sid,double deviation) throws Exception;
	
	/**默认kdj分析，parameter:股票代码*/
	public QuotaAnalysis getKdjAnalysis(String sid) throws Exception;
	
	/**默认macd分析，parameter:股票代码*/
	public QuotaAnalysis getMacdAnalysis(String sid) throws Exception;
	/**自定义macd分析，parameter:股票代码、变动范围*/
	public QuotaAnalysis getMacdAnalysis(String sid,double deviation) throws Exception;
	
	/**默认dmi分析，parameter:股票代码*/
	public QuotaAnalysis getDmiAnalysis(String sid) throws Exception;
	/**自定义dmi分析，parameter:股票代码、变动范围*/
	public QuotaAnalysis getDmiAnalysis(String sid,double deviation) throws Exception;
	
	/**默认roc分析，parameter:股票代码*/
	public QuotaAnalysis getRocAnalysis(String sid) throws Exception;
	public QuotaAnalysis getRocAnalysis(String sid,Date startdate,double deviation,
			double up1,double down1) throws Exception;
	
	/**默认乖离率分析，parameter:股票代码*/
	public QuotaAnalysis getBiasAnalysis(String type,String sid) throws Exception;
	/**自定义乖离率分析,parameter:股票代码，6日基期，12日基期，24日基期，6日上分位点，12日上分位点，24日上分位点，6日中分位点，12日中分位点，24日中分位点，6日下分位点，12日下分位点，24日下分位点，*/
	public QuotaAnalysis getBiasAnalysis(String type,String sid,Date startdate6,Date startdate12,Date startdate24,
			double separator6_up,double separator12_up,double separator24_up,
			double separator6_mid,double separator12_mid,double separator24_mid,
			double separator6_down,double separator12_down,double separator24_down) throws Exception;
	
	/**默认相对强弱指标分析，parameter:股票代码*/
	public QuotaAnalysis getRsiAnalysis(String type,String sid) throws Exception;
	/**自定义相对强弱指标分析，parameter:股票代码，基期，上分位点，中分位点，下分位点*/
	public QuotaAnalysis getRsiAnalysis(String type,String sid, Date startdate,
			double up,double mid,double down, double deviation) throws Exception;
	
	/**默认成交量变异律分析，parameter:股票代码*/
	public QuotaAnalysis getVrAnalysis(String type,String sid) throws Exception;
	/**自定义成交量变异律分析，parameter:股票代码、低价区下界、低价区上界、安全区下界、安全区上界、盈利区下界、盈利区上界（警戒区下界）*/
	public QuotaAnalysis getVrAnalysis(String type,String sid, double in1, double in2,
			double wait1, double wait2, double out1, double out2,
			Date startdate,Date enddate,double deviation) throws Exception;
	
	/**默认能量潮分析，parameter:股票代码*/
	public QuotaAnalysis getObvAnalysis(String type,String sid) throws Exception;
	/**默认能量潮分析，parameter:股票代码*/
	public QuotaAnalysis getObvAnalysis(String type,String sid, double obvfastspeed5, double obvslowspeed5, double closefastspeed5, double closeslowspeed5,
			double obvfastspeed10, double obvslowspeed10, double closefastspeed10, double closeslowspeed10,
			double obvfastspeed20, double obvslowspeed20, double closefastspeed20, double closeslowspeed20) throws Exception;
	
	/**默认布林线分析，parameter:股票代码*/
	public QuotaAnalysis getBollAnalysis(String type,String sid) throws Exception;
	/**自定义布林线分析，parameter:股票代码、变动范围*/
	public QuotaAnalysis getBollAnalysis(String type,String sid,double deviation) throws Exception;
	
	/**默认kdj分析，parameter:股票代码*/
	public QuotaAnalysis getKdjAnalysis(String type,String sid) throws Exception;
	
	/**默认macd分析，parameter:股票代码*/
	public QuotaAnalysis getMacdAnalysis(String type,String sid) throws Exception;
	/**自定义macd分析，parameter:股票代码、变动范围*/
	public QuotaAnalysis getMacdAnalysis(String type,String sid,double deviation) throws Exception;
	
	/**默认dmi分析，parameter:股票代码*/
	public QuotaAnalysis getDmiAnalysis(String type,String sid) throws Exception;
	/**自定义dmi分析，parameter:股票代码、变动范围*/
	public QuotaAnalysis getDmiAnalysis(String type,String sid,double deviation) throws Exception;
	
	/**默认roc分析，parameter:股票代码*/
	public QuotaAnalysis getRocAnalysis(String type,String sid) throws Exception;
	public QuotaAnalysis getRocAnalysis(String type,String sid,Date startdate,double deviation,
			double up1,double down1) throws Exception;
}
