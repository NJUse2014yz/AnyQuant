package backtest;

import java.sql.Date;
import java.util.List;

import vo.QuotaAnalysis;
import vo.Scale;
import vo.TradeSignal;
import backtest.tool.AnalyseSignalPack;
import backtest.tool.TestReport;
import bl.QuotaAnalyse;

public interface BackTestInnerService {
	public TestReport runTest(List<Scale> scales,List<AnalyseSignalPack> analyseSignalPacks,AnalyseSignalPack bAnalyseSignalPack) throws Exception;
	public TestReport runTest_self(List<Scale> scales,double invest,Date startdate,Date enddate,int n,List<AnalyseSignalPack> analyseSignalPacks,String benchmark,AnalyseSignalPack bAnalyseSignalPack) throws Exception;	
}
