package mapper;

import java.util.List;

import po.StrategyRecInfString;

public interface StockRecommendMapper {
	public void createStockRecommendTable() throws Exception;
	public void deleteStockRecommend_all() throws Exception;
	public void insertStockRecommend_list(List<StrategyRecInfString> list) throws Exception;
	public void insertStockRecommend_single(StrategyRecInfString strategyInfString) throws Exception;
	public List<StrategyRecInfString> selectStockRecommend_list() throws Exception;
}
