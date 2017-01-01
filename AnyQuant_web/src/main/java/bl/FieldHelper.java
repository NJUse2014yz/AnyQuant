package bl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import data.DataService;
import data.impl.DataServiceImpl;
import po.BlockHistoryData;
import po.HistoryData;
import tool.DateExchangeTool;

public class FieldHelper {
	private static DataService dataService = new DataServiceImpl();
	//return 0:todayAmount;   1:baseAmount
	public static ArrayList<Double> calIndex(List<BlockHistoryData> stocklist) throws Exception {
//		String basedate = "2016-04-01";
//		Date base = DateExchangeTool.stringToSqlDate(basedate);
		// 得到基准日数据
//		double baseAmount = 0;
		double todayAmount = 0;
//		double baseVolumn = 1;
		double todayVolumn = 1;
		for (int i = 0; i < stocklist.size(); i++) {
			BlockHistoryData temp=stocklist.get(i);
				todayAmount += temp.getAmount();
				todayVolumn +=temp.getVolume();
			
		}
		
		ArrayList<Double> result=new ArrayList<Double>();
		result.add(todayAmount/todayVolumn);
//		result.add(baseAmount/baseVolumn);
		return result ;

	}
}
