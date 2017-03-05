package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.MarketType;
import service.StockDataService;
import vo.HaltStockInf;
import vo.NewStockInf;
import vo.StockListInf;
import vo.StockTopInf;

/**
 * 
 * @author 周聪
 * 2016年5月12日
 */
@Controller
public class StockController {

	@Autowired
	StockDataService stockBl;
	
	@RequestMapping("/stock")
	public ModelAndView stock() throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		List<StockListInf> list1=null;
		List<StockListInf> list2=null;
		List<StockTopInf> toplist=null;
		List<NewStockInf> startlist=null;
		List<HaltStockInf> endlist=null;
		
			list1=stockBl.getIndiceList(MarketType.SH);
			list2=stockBl.getIndiceList(MarketType.SZ);
			toplist=stockBl.getRank();
			toplist=new ArrayList<StockTopInf>();
			startlist = stockBl.getNewStocks();
			endlist = stockBl.getHaltStock();
		
			if(toplist.size()>10)
				toplist=toplist.subList(0, 10);
		
		modelAndView.addObject("toplen", toplist.size()-1);	
		modelAndView.addObject("toplist", toplist);
		modelAndView.addObject("list1", list1);
		modelAndView.addObject("len1", list1.size()-1);
		modelAndView.addObject("list2", list2);
		modelAndView.addObject("len2", list2.size()-1);
		modelAndView.addObject("startlist", startlist);
		modelAndView.addObject("endlist", endlist);
		modelAndView.setViewName("data/Stock");
		return modelAndView;
	}
	
	/**
	 * 获得所有股票的数据列表
	 * @param pageNumber 页号
	 * @throws Exception 
	 */
	@RequestMapping("/getStockList")
	public @ResponseBody List<StockListInf> getStockList(int pageNumber) throws Exception{
		List<StockListInf> stockList=null;
		
		stockList=stockBl.showListStock(pageNumber);
		
		return stockList;
	}
	
	/**
	 * 根据关键字搜索股票
	 * 2016年6月3日
	 */
	@RequestMapping("/searchStock")
	public @ResponseBody List<StockListInf> searchStock(String key) throws Exception{
		List<StockListInf> resultSet=stockBl.searchStock(key);
		return resultSet;
	}
	
	@RequestMapping("/grail_sh")
	public String grail_sh(){
		return "data/Grail-sh";
	}
	
	@RequestMapping("/grail_sz")
	public String grail_sz(){
		return "data/Grail-sz";
	}
}
