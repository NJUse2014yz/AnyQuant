package pr;

import java.util.List;

import mapper.HistoryDataMapper;
import mapper.IndustryInfMapper;
import mapper.IndustryLatestMapper;
import mapper.StockInfMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import data.impl.DataServiceImpl;
import po.HistoryData;
import po.HistoryDataPack;
import po.IndustryInf;

public class IndustryLatestPr {
	private ApplicationContext applicationContext;
	private IndustryLatestMapper industryLatestMapper;
	private IndustryInfMapper industryInfMapper;
	private HistoryDataMapper historyDataMapper;
	
	/**创建对象时为mapper注入对象*/
	public IndustryLatestPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		industryLatestMapper=(IndustryLatestMapper) applicationContext.getBean("industryLatestMapper");
		industryInfMapper=(IndustryInfMapper) applicationContext.getBean("industryInfMapper");
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
	}
	public void create() throws Exception
	{
		industryLatestMapper.createLatestDataTable();
		industryLatestMapper.initLatestData();
	}
	public void update() throws Exception
	{
		HistoryDataPack historyDataPack=new HistoryDataPack();
		List<String> sidlist=industryInfMapper.selectIndustryInfSid_all();
		for(int i=0;i<sidlist.size();i++)
		{
			try{
				HistoryData historyData1=historyDataMapper.selectHistoryData_new_single(sidlist.get(i));
				historyDataPack.setSiid(sidlist.get(i));
				historyDataPack.setHistoryData1(historyData1);
				industryLatestMapper.updateLatestData(historyDataPack);
			} catch(Exception e)
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
		industryLatestMapper.updateLatestData(historyDataPack);
	}
	public void update_industry(String cname) throws Exception
	{
		HistoryDataPack historyDataPack=new HistoryDataPack();
		List<IndustryInf> industrylist=new DataServiceImpl().getIndustryInf_cname(cname);
		for(int i=0;i<industrylist.size();i++)
		{
			HistoryData historyData1=historyDataMapper.selectHistoryData_new_single(industrylist.get(i).getSid());
			historyDataPack.setSiid(industrylist.get(i).getSid());
			historyDataPack.setHistoryData1(historyData1);
			industryLatestMapper.updateLatestData(historyDataPack);
		}
	}
}
