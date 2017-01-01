package service;

import java.util.List;

import vo.AllNewsInf;
import vo.StrategyRecStocks;

public interface HomePageService { 
	public List<AllNewsInf> getAllNews() throws Exception;
	public List<StrategyRecStocks> getStrategyRecStocks(int num)throws Exception;
}
