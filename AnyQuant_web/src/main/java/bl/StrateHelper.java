package bl;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import vo.StockTopInf;
import vo.StrategyRecInf;

public class StrateHelper {
	public static List<StrategyRecInf> turnStrategyRec(String result) {
		result = "{\"head\":" + result + "}";
		JSONObject obj = JSONObject.fromObject(result);

		JSONArray jsonArray = obj.getJSONArray("head");
		ArrayList<StrategyRecInf> relist = new ArrayList<StrategyRecInf>();
		for (int i = 0; i < jsonArray.size(); i++) {
			JSONObject temp = (JSONObject) jsonArray.get(i);
			StrategyRecInf inf = new StrategyRecInf();
			inf.id = i + 1;
			inf.open = DataHelper.controldigit(temp.getDouble("open"));
			inf.close = DataHelper.controldigit(temp.getDouble("close"));
			inf.volumn = DataHelper.controldigit(temp.getDouble("volumn"));
			inf.turnover = DataHelper.controldigit(temp.getDouble("turnover"));
			relist.add(inf);
		}
		return relist;

	}
}
