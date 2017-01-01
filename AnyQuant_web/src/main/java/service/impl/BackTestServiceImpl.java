package service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import service.BackTestService;
import vo.Scale;
import vo.TradeSignal;
import backtest.BackTestInnerService;
import backtest.impl.BackTestInnerServiceImpl;
import backtest.tool.AnalyseSignalPack;
import backtest.tool.TestReport;
import bl.QuotaAnalyse;

public class BackTestServiceImpl implements BackTestService {
	public BackTestInnerService backTestInnerService;
	public BackTestServiceImpl()
	{
		backTestInnerService=new BackTestInnerServiceImpl();
	}
	@Override
	public TestReport backtest_single(String sid, List<QuotaAnalyse> analysises,List<QuotaAnalyse> bAnalysises)
			throws Exception {
		List<Scale> scales=new ArrayList<Scale>();
		scales.add(new Scale(sid,100));
		
		List<TradeSignal> signals=new ArrayList<TradeSignal>();
		for(int i=0;i<analysises.size();i++)
		{
			signals.add(new TradeSignal());//默认信号
		}
		List<AnalyseSignalPack> analyseSignalPacks=new ArrayList<AnalyseSignalPack>();
		analyseSignalPacks.add(new AnalyseSignalPack(analysises, signals));
		AnalyseSignalPack bAnalyseSignalPack=new AnalyseSignalPack(bAnalysises,signals);
		return backTestInnerService.runTest(scales, analyseSignalPacks, bAnalyseSignalPack);
	}

	@Override
	public TestReport backtest(List<Scale> scales,
			List<AnalyseSignalPack> analyseSignalPacks,List<QuotaAnalyse> bAnalysises) throws Exception {
		List<TradeSignal> signals=new ArrayList<TradeSignal>();
		for(int i=0;i<bAnalysises.size();i++)
		{
			signals.add(new TradeSignal());//基准默认信号
		}
		AnalyseSignalPack bAnalyseSignalPack=new AnalyseSignalPack(bAnalysises,signals);
		return backTestInnerService.runTest(scales, analyseSignalPacks, bAnalyseSignalPack);
	}

	@Override
	public TestReport backtest_self(List<Scale> scales,
			List<AnalyseSignalPack> analyseSignalPacks, double invest,
			Date startdate, Date enddate, String benchmark,
			AnalyseSignalPack bAnalyseSignalPack) throws Exception {
		return backTestInnerService.runTest_self(scales, invest, startdate, enddate, 1, analyseSignalPacks, benchmark, bAnalyseSignalPack);
	}

}
