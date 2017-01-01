package service.impl;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.List;

import mapper.StockRecommendMapper;

import org.springframework.beans.factory.annotation.Autowired;

import bl.NewsHelper;
import data.impl.DataServiceImpl;
import po.StockInf;
import po.StrategyRecInfString;
import service.HomePageService;
import vo.AllNewsInf;
import vo.FieldRankInf;
import vo.StockListInf;
import vo.StrategyRecInf;
import vo.StrategyRecStocks;

public class HomePageServiceImpl implements HomePageService{
	@Autowired
	private StockRecommendMapper stockRecommendMapper;
	@Override
	public List<AllNewsInf> getAllNews() throws Exception {
		Runtime run = Runtime.getRuntime();
		String result ="";
		try {
			Process p = run.exec("python E:/python/news.py");
			BufferedInputStream in = new BufferedInputStream(p.getInputStream());
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String lineStr;
			while ((lineStr = br.readLine()) != null) {
				result += lineStr;
			}
			br.close();
			in.close();
			p.waitFor();
		} catch (Exception e) {
			e.printStackTrace();
		}
//		System.out.println(result);
		return NewsHelper.turnAllNews(result);
	}
	
	
	@Override
	public List<StrategyRecStocks> getStrategyRecStocks(int num) throws Exception {
		//读出数据库
		List<StrategyRecInfString> stockRecList=stockRecommendMapper.selectStockRecommend_list();
		List<StrategyRecStocks> strategyRecList=new ArrayList<StrategyRecStocks>();
		for(int i=0;i<Math.min(num,stockRecList.size());i++)
		{
			strategyRecList.add(new StrategyRecStocks(stockRecList.get(i)));
		}
		return strategyRecList;
	}
	
	public List<StrategyRecStocks> getStrategy()
	{
		List<StrategyRecStocks> list =null;
		try{
			ObjectInputStream ois = new ObjectInputStream(new FileInputStream(
					new File("recommend.txt")));
			list=(List<StrategyRecStocks>) ois.readObject();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		return list;
	}
	
	
	public static void main(String[] args) throws Exception {
		HomePageServiceImpl here=new HomePageServiceImpl();
		List<AllNewsInf> temp=here.getAllNews();
		for(int i=0;i<temp.size();i++){
			System.out.println(temp.get(i).classify+"   "+temp.get(i).title);
		}
		
//		List<StrategyRecStocks> temp=here.getStrategyRecStocks();
//		for(int i=0;i<temp.size();i++){
//			System.out.println(temp.get(i).stockId+"  "+temp.get(i).p_change);
//		}
	}
	
	    


	

}
