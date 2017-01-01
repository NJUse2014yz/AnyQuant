package service;

import java.sql.Date;
import java.util.List;

import po.StockInf;
import vo.StockPair;

/**配对交易,推荐可以成对交易的股票组*/
public interface PairStrategyService {
	/**用于配对的股票列表，推荐条数,推荐算法从截止日期前的多少天开始，推荐算法的截止日期，显著性(0.01,0.05,0.10)*/
	public List<StockPair> getPairStrategyRecommend(List<StockInf> sidlist,int quo,int num,Date enddate,double alpha) throws Exception;
	public List<StockPair> getSolemate(String sid) throws Exception;
	/**推荐条数，板块名称*/
	public List<StockPair> getPairStrategyRecommendArea(int quo,String cname) throws Exception;
	public List<StockPair> getPairStrategyRecommendIndustry(int quo,String cname) throws Exception;
	public List<StockPair> getPairStrategyRecommendConcept(int quo,String cname) throws Exception;
}
