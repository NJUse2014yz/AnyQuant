package pr;

import java.sql.Date;

import mapper.StockInfMapper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.test.context.ContextConfiguration;

import po.StockInf;
import tool.JsonExchangeTool;

public class StockInfPr {
	private ApplicationContext applicationContext;
	private StockInfMapper stockInfMapper;
	
	/**创建对象时为mapper注入对象*/
	public StockInfPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		stockInfMapper=(StockInfMapper) applicationContext.getBean("stockInfMapper");
	}
	
	/**创建股票信息*/
	public void createStockInf()
	{
		try {
			stockInfMapper.createStockInfTable();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			stockInfMapper.insertStockInf_list(JsonExchangeTool.getStockInf());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
