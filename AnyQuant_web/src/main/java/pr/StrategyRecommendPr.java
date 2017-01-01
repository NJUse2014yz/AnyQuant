package pr;

import java.util.ArrayList;
import java.util.List;

import mapper.StockRecommendMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import bl.DataHelper;
import po.StockInf;
import po.StrategyRecInfString;
import service.impl.StrategyServiceImpl;
import vo.StrategyRecInf;
import data.impl.DataServiceImpl;
import exception.DataLackException;

public class StrategyRecommendPr {
	private ApplicationContext applicationContext;
	private StockRecommendMapper stockRecommendMapper;
	
	public StrategyRecommendPr()
	{
		applicationContext=new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		stockRecommendMapper=(StockRecommendMapper) applicationContext.getBean("stockRecommendMapper");
	}
	public void createStrategyRecommend() throws Exception
	{
		stockRecommendMapper.createStockRecommendTable();
	}
	public void initStrategyRecommend() throws Exception
	{
		StrategyServiceImpl strateRec = new StrategyServiceImpl();
		ArrayList<StrategyRecInfString> relist=new ArrayList<StrategyRecInfString>();
		List<StockInf> list = new DataServiceImpl().getStockList();
		for(int i=0;i<list.size();i++){
			try{
				StrategyRecInfString temp=new StrategyRecInfString();
				temp.sid=list.get(i).getSid();
				temp.name=list.get(i).getSname();
				List<StrategyRecInf> predictlist = strateRec.getRecStrategy(temp.sid);
				temp.p_change=DataHelper.controldigit(7*(predictlist.get(0).close/predictlist.get(0).open-1)+
						4*(predictlist.get(1).close/predictlist.get(1).open-1)+
						2*(predictlist.get(2).close/predictlist.get(2).open-1)+
						1*(predictlist.get(3).close/predictlist.get(3).open-1));
				temp.recinf=predictlist.toString();
				System.out.println(temp);
				relist.add(temp);
				stockRecommendMapper.insertStockRecommend_single(temp);
//				System.out.println(i);
				relist.add(temp);
			}
			catch(DataLackException e)
			{
				e.printStackTrace();
			}
		}
		
		//写入数据库
//		stockRecommendMapper.insertStockRecommend_list(relist);
	}
}
