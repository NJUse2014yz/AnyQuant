package pr;

import java.io.File;
import java.io.FileOutputStream;
import java.io.ObjectOutputStream;

import service.impl.HomePageServiceImpl;

public class DataBaseSetUp {
	public static void setUp()
	{
		
		try {
			//创建地域信息表
//			new AreaInfPr().initAreaInfTable();
		
			//创建行业信息表
//			new IndustryInfPr().initIndustryInfTable();
			
			//创建概念信息表
//			new ConceptInfPr().initConceptInfTable();
			
			//创建股票信息表
//			new StockInfPr().createStockInf();
			
			//创建指数信息表
//			new IndiceInfPr().initIndiceInfTable();
			
			//初始化历史数据
//			new HistoryDataPr().insertHistoryData_all();
			
			//初始化周历史数据
//			new WeekHDataPr().insertWeekHData_all();
			
			//初始化月历史数据
//			new MonthHDataPr().insertMonthHData_all();
			
			//初始化指标数据
//			new QuotaDataPr();
//			QuotaDataPr.insertQuotaData_all();
//			QuotaDataPr.insertWeekQData_all();
//			QuotaDataPr.insertMonthQData_all();
		
			//初始化板块历史最新数据
//			new IndustryLatestPr().create();
//			new IndustryLatestPr().update();
//			new AreaLatestPr().create();
//			new AreaLatestPr().update();
//			new ConceptLatestPr().create();
//			new ConceptLatestPr().update();
			
			//初始化股票预测数据
//			new StrategyRecommendPr().createStrategyRecommend();
			new StrategyRecommendPr().initStrategyRecommend();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public static void main(String[] args)
	{
		DataBaseSetUp.setUp();
	}
}
