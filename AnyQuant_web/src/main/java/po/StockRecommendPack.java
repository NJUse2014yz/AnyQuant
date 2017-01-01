package po;

import java.util.ArrayList;
import java.util.List;

import vo.StrategyRecStocks;

public class StockRecommendPack {
	private List<StrategyRecInfString> recommendlist;

	public StockRecommendPack() {
		this.recommendlist = new ArrayList<StrategyRecInfString>();
	}
	public StockRecommendPack(List<StrategyRecInfString> list) {
		this.recommendlist = list;
	}
	
	public List<StrategyRecInfString> getRecommendlist() {
		return recommendlist;
	}
	public void setRecommendlist(List<StrategyRecInfString> list) {
		this.recommendlist = list;
	}
	@Override
	public String toString() {
		return "StockRecommendPack [recommendlist=" + recommendlist + "]";
	}
}
