package bl;

import java.util.ArrayList;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.AllNewsInf;
import vo.StockNewsInf;

public class NewsHelper {
	public static ArrayList<AllNewsInf> turnAllNews(String result) {
		result = "{\"head\":" + result + "}";
		JSONObject obj = JSONObject.fromObject(result);

		JSONArray jsonArray = obj.getJSONArray("head");
		// for (int i = 0; i < jsonArray.size(); i++) {
		// System.out.println("element " + i + " :" + jsonArray.get(i));
		// }
		ArrayList<AllNewsInf> relist = new ArrayList<AllNewsInf>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject temp = (JSONObject) jsonArray.get(i);
			if (temp.getString("classify").equals("国内财经")) {
				AllNewsInf inf = new AllNewsInf();
				inf.classify = temp.getString("classify");
				inf.title = temp.getString("title");
				inf.time = temp.getString("time");
				inf.url = temp.getString("url");
				relist.add(inf);
			}
		}
		if (relist.size() == 0) {
			for (int i = 0; i < jsonArray.size(); i++) {
				JSONObject temp = (JSONObject) jsonArray.get(i);
				if (temp.getString("classify").equals("生活")) {
					AllNewsInf inf = new AllNewsInf();
					inf.classify = temp.getString("classify");
					inf.title = temp.getString("title");
					inf.time = temp.getString("time");
					inf.url = temp.getString("url");
					relist.add(inf);
				}
			}
		}
		// System.out.println("size="+relist.size());
		return relist;
	}

	public static ArrayList<StockNewsInf> turnSingleNews(String result) {
		result = "{\"head\":" + result + "}";
		JSONObject obj = JSONObject.fromObject(result);

		JSONArray jsonArray = obj.getJSONArray("head");
//		 for (int i = 0; i < jsonArray.size(); i++) {
//		 System.out.println("element " + i + " :" + jsonArray.get(i));
//		 }
		ArrayList<StockNewsInf> relist = new ArrayList<StockNewsInf>();
		for (int i = 0; (i < jsonArray.size()) && (i < 5); i++) {
			JSONObject temp = (JSONObject) jsonArray.get(i);

			StockNewsInf inf = new StockNewsInf();
			inf.title = temp.getString("title");
			inf.type = temp.getString("type");
			inf.date = temp.getString("date");
			inf.url = temp.getString("url");
			relist.add(inf);
		}
		return relist;
	}
}
