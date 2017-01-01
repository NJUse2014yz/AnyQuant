package service;

import java.sql.Date;
import java.util.List;

import vo.Scale;
import backtest.tool.AnalyseSignalPack;
import backtest.tool.TestReport;
import bl.QuotaAnalyse;

public interface BackTestService {
	/**单股默认信号默认回测*/
	public TestReport backtest_single(String sid,List<QuotaAnalyse> analysises,List<QuotaAnalyse> bAnalysises) throws Exception;
	
	/**多股自定义策略默认回测:选股和比例，每股对应的‘多策略分析结果和对应交易信号’*/
	public TestReport backtest(List<Scale> scales,List<AnalyseSignalPack> analyseSignalPacks,List<QuotaAnalyse> bAnalysises) throws Exception;
	
	/**多股自定义策略自定义回测:选股和比例，每股对应的‘多策略分析结果和交易信号’,虚拟投资金额，回测开始日期，回测结束日期，基准指数代码，基准分析结果和交易信号*/
	public TestReport backtest_self(List<Scale> scales,List<AnalyseSignalPack> analyseSignalPacks,double invest,Date startdate, Date enddate,String benchmark,AnalyseSignalPack bAnalyseSignalPack) throws Exception;
}
