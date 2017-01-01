package pr;

import java.util.List;

import mapper.HistoryDataMapper;
import mapper.ConceptInfMapper;
import mapper.ConceptLatestMapper;
import mapper.StockInfMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import data.impl.DataServiceImpl;
import po.HistoryData;
import po.HistoryDataPack;
import po.ConceptInf;

public class ConceptLatestPr {
	private ApplicationContext applicationContext;
	private ConceptLatestMapper conceptLatestMapper;
	private ConceptInfMapper conceptInfMapper;
	private HistoryDataMapper historyDataMapper;
	
	/**创建对象时为mapper注入对象*/
	public ConceptLatestPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		conceptLatestMapper=(ConceptLatestMapper) applicationContext.getBean("conceptLatestMapper");
		conceptInfMapper=(ConceptInfMapper) applicationContext.getBean("conceptInfMapper");
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
	}
	public void create() throws Exception
	{
		conceptLatestMapper.createLatestDataTable();
		conceptLatestMapper.initLatestData();
	}
	public void update() throws Exception
	{
		HistoryDataPack historyDataPack=new HistoryDataPack();
		List<String> sidlist=conceptInfMapper.selectConceptInfSid_all();
		for(int i=0;i<sidlist.size();i++)
		{
			try{
				HistoryData historyData1=historyDataMapper.selectHistoryData_new_single(sidlist.get(i));
				historyDataPack.setSiid(sidlist.get(i));
				historyDataPack.setHistoryData1(historyData1);
				conceptLatestMapper.updateLatestData(historyDataPack);
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
		conceptLatestMapper.updateLatestData(historyDataPack);
	}
	public void update_concept(String cname) throws Exception
	{
		HistoryDataPack historyDataPack=new HistoryDataPack();
		List<ConceptInf> conceptlist=new DataServiceImpl().getConceptInf_cname(cname);
		for(int i=0;i<conceptlist.size();i++)
		{
			HistoryData historyData1=historyDataMapper.selectHistoryData_new_single(conceptlist.get(i).getSid());
			historyDataPack.setSiid(conceptlist.get(i).getSid());
			historyDataPack.setHistoryData1(historyData1);
			conceptLatestMapper.updateLatestData(historyDataPack);
		}
	}
}

