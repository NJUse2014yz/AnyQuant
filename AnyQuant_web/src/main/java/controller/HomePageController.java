package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import service.HomePageService;
import service.StockDataService;
import vo.AllNewsInf;
import vo.StockListInf;
import vo.StrategyRecInf;
import vo.StrategyRecStocks;

/**
 * 首页
 * @author 周聪
 * 2016年5月22日
 */
@Controller
public class HomePageController {

	@Autowired
	private HomePageService bl;
	
	@Autowired
	private StockDataService stockBl;
	
	/**
	 * 根据策略号获得股票推荐列表
	 * @param currentStrategy 策略号
	 * 2016年5月29日
	 * @throws Exception 
	 */
	@RequestMapping("/index")
	public ModelAndView getRecommendStock(Integer currentStrategy) throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		
		List<StrategyRecStocks> strategyStocks=bl.getStrategyRecStocks(4);
		/*StrategyRecStocks recs=new StrategyRecStocks();
		recs.name="可口可乐";
		recs.stockId="sh600004";
		StrategyRecInf info=new StrategyRecInf();
		info.close=44;
		info.open=234;
		info.turnover=0.23;
		List<StrategyRecInf> infos=new ArrayList<>();
		infos.add(info);
		recs.predictList=infos;
		
		List<StrategyRecStocks> strategyStocks=new ArrayList<>();
		for(int i=0;i<4;i++){
			strategyStocks.add(recs);
		}*/
		
		modelAndView.addObject("strategyStocks", strategyStocks);
		
		//查询基础信息
		List<StockListInf> stocks=new ArrayList<>();
		for(int i=0;i<Math.min(strategyStocks.size(),4);i++){
			StockListInf stockInfo=stockBl.showSingleStock(strategyStocks.get(i).sid);
			stocks.add(stockInfo);
		}
		modelAndView.addObject("stocks", stocks);
		
		List<AllNewsInf> newsList=bl.getAllNews();
		modelAndView.addObject("newsList",newsList);
		
		modelAndView.setViewName("main/index");
		return modelAndView;
	}
	
	
}
