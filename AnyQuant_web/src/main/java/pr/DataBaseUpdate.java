package pr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;

import service.impl.HomePageServiceImpl;

public class DataBaseUpdate {
	public static void update()
	{
//		File file=new File("test.txt");
//		FileWriter fw=null;
//		try {
//			fw = new FileWriter(file,true);
//		} catch (IOException e1) {
//			e1.printStackTrace();
//		}
//		BufferedWriter bufferWritter = new BufferedWriter(fw);
//		
//		try {
//			//更新历史数据
//			new HistoryDataPr().insertHistoryData_all();
//			bufferWritter.write("history data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try{
//			//更新周历史数据
//			new WeekHDataPr().insertWeekHData_all();
//			bufferWritter.write("history week data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try{
//			//更新月历史数据
//			new MonthHDataPr().insertMonthHData_all();
//			bufferWritter.write("history month data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		new QuotaDataPr();
//		try{
//			//更新指标数据
//			QuotaDataPr.insertQuotaData_all();
//			bufferWritter.write("quota data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try{
//			//更新指标数据
//			QuotaDataPr.insertWeekQData_all();
//			bufferWritter.write("quota week data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try{
//			//更新指标数据
//			QuotaDataPr.insertMonthQData_all();
//			bufferWritter.write("quota month data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try{
//			//更新板块历史最新数据
//			new IndustryLatestPr().update();
//			bufferWritter.write("industry data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try{
//			//更新板块历史最新数据
//			new AreaLatestPr().update();
//			bufferWritter.write("area data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		try{
//			//更新板块历史最新数据
//			new ConceptLatestPr().update();
//			bufferWritter.write("concept data\n");
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		try{
			//更新股票预测数据
			new StrategyRecommendPr().initStrategyRecommend();
//			bufferWritter.write("recommend data\n");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
//		try {
//			bufferWritter.close();
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}
}
