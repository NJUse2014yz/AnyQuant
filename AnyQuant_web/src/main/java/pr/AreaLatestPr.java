package pr;

import java.util.List;

import mapper.HistoryDataMapper;
import mapper.AreaInfMapper;
import mapper.AreaLatestMapper;
import mapper.StockInfMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import data.impl.DataServiceImpl;
import po.HistoryData;
import po.HistoryDataPack;
import po.AreaInf;

public class AreaLatestPr {
	private ApplicationContext applicationContext;
	private AreaLatestMapper areaLatestMapper;
	private AreaInfMapper areaInfMapper;
	private HistoryDataMapper historyDataMapper;
	
	/**创建对象时为mapper注入对象*/
	public AreaLatestPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		areaLatestMapper=(AreaLatestMapper) applicationContext.getBean("areaLatestMapper");
		areaInfMapper=(AreaInfMapper) applicationContext.getBean("areaInfMapper");
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
	}
	public void create() throws Exception
	{
		areaLatestMapper.createLatestDataTable();
		areaLatestMapper.initLatestData();
	}
	public void update() throws Exception
	{
		HistoryDataPack historyDataPack=new HistoryDataPack();
		List<String> sidlist=areaInfMapper.selectAreaInfSid_all();
		for(int i=0;i<sidlist.size();i++)
		{
			try{
				HistoryData historyData1=historyDataMapper.selectHistoryData_new_single(sidlist.get(i));
				historyDataPack.setSiid(sidlist.get(i));
				historyDataPack.setHistoryData1(historyData1);
				areaLatestMapper.updateLatestData(historyDataPack);
			} catch (Exception e)
			{
				e.printStackTrace();
			}
		}
	}
	public void update_single(String sid) throws Exception
	{
		HistoryDataPack historyDataPack=new HistoryDataPack();
		HistoryData historyData1=historyDataMapper.selectHistoryData_new_single(sid);
		historyDataPack.setSiid(sid);
		historyDataPack.setHistoryData1(historyData1);
		areaLatestMapper.updateLatestData(historyDataPack);
	}
	public void update_area(String area) throws Exception
	{
		HistoryDataPack historyDataPack=new HistoryDataPack();
		List<AreaInf> arealist=new DataServiceImpl().getAreaInf_area(area);
		for(int i=0;i<arealist.size();i++)
		{
			HistoryData historyData1=historyDataMapper.selectHistoryData_new_single(arealist.get(i).getSid());
			historyDataPack.setSiid(arealist.get(i).getSid());
			historyDataPack.setHistoryData1(historyData1);
			areaLatestMapper.updateLatestData(historyDataPack);
		}
	}
}
