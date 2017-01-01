package pr;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.List;

import mapper.HistoryDataMapper;
import mapper.IndiceInfMapper;
import mapper.StockInfMapper;
import mapper.WeekHDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.HistoryData;
import po.HistoryDataPack;
import po.IndiceInf;
import po.StockInf;
import tool.MMSTool;

public class WeekHDataPr {
	public static ApplicationContext applicationContext;
	public static HistoryDataMapper historyDataMapper;
	public static WeekHDataMapper weekHDataMapper;
	public static StockInfMapper stockInfMapper;
	public static IndiceInfMapper indiceInfMapper;
	/**历史数据列表*/
	public List<HistoryData> hisList;
	/**要新写的周历史数据列表*/
	public List<HistoryData> newweekhisList;
	/**历史数据中最新周id*/
	public int weekid;
	/**周历史数据最近id*/
	public int weekhisid;
	
	/**创建对象时为mapper注入对象*/
	public WeekHDataPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		weekHDataMapper=(WeekHDataMapper) applicationContext.getBean("weekHDataMapper");
		stockInfMapper=(StockInfMapper)applicationContext.getBean("stockInfMapper");
		indiceInfMapper=(IndiceInfMapper)applicationContext.getBean("indiceInfMapper");
		weekid=-1;
		weekhisid=-1;
	}
	
	public void insertWeekHData_all() throws Exception
	{
		List<StockInf> stockList=stockInfMapper.selectStockInf_all();
		for(int i=0;i<stockList.size();i++)
		{
			insertWeekHData_single("s",stockList.get(i).getSid());
		}
		
		List<IndiceInf> indiceList=indiceInfMapper.selectIndiceInf_all();
		for(int i=0;i<indiceList.size();i++)
		{
			insertWeekHData_single("i",indiceList.get(i).getIid());
		}
	}
	
	public void insertWeekHData_single(String type,String siid) throws Exception
	{
		//从数据库stockinf表中读取weekid和weekhisid
		weekid=-1;
		weekhisid=-1;
		if(type.equals("s"))
		{
			StockInf stockInf=stockInfMapper.selectStockInf_e_sid(siid);
			weekid=stockInf.getWeekid();
			weekhisid=stockInf.getWeekhisid();
		}
		else if(type.equals("i"))
		{
			IndiceInf indiceInf=indiceInfMapper.selectIndiceInf_e_iid(siid);
			weekid=indiceInf.getWeekid();
			weekhisid=indiceInf.getWeekhisid();
		}
		else
		{
			return ;//exception
		}
		if(weekid==-1)
		{
			return ;
		}
		if(weekhisid==-1)
		{
			weekHDataMapper.createWeekHDataTable(siid);
		}
		
		//从数据库中读取需要更新的周的历史数据
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setId1(weekhisid);
		datePack.setId2(weekid);
		hisList=historyDataMapper.selectHistoryData_b_weekid(datePack);
		if(weekhisid!=-1)
		{
			weekHDataMapper.deleteWeekHData_e_weekid(datePack);
		}
		else
		{
			weekhisid++;
		}
		newweekhisList=new ArrayList<HistoryData>();
		
		int start=0;
		int end=-1;
		int itr=0;
		while(itr<hisList.size())
		{
			//看此时itr取到的历史数据的weekid是否是接下来要处理的
			if(hisList.get(itr).getWeekid()==weekhisid&&itr!=hisList.size()-1)
			{
				end++;
				itr++;
			}
			else
			{
				if(hisList.get(itr).getWeekid()==weekhisid&&itr==hisList.size()-1)
				{
					end++;
					itr++;
				}
				
				HistoryData historyData=new HistoryData();
				historyData.setWeekid(weekhisid);
				historyData.setDate(hisList.get(end).getDate());
				historyData.setOpen(hisList.get(start).getOpen());
				historyData.setClose(hisList.get(end).getClose());
				historyData.setHigh(MMSTool.max_high(hisList.subList(start, end+1)));
				historyData.setLow(MMSTool.min_low(hisList.subList(start, end+1)));
				historyData.setVolume(MMSTool.sum_vol(hisList.subList(start, end+1)));
				historyData.setIncrease(historyData.getClose()-historyData.getOpen());
				if(historyData.getOpen()!=0)
					historyData.setIncrPer(historyData.getIncrease()/historyData.getOpen());
				else
					historyData.setIncrPer(0);
				historyData.setAmount(MMSTool.sum_amo(hisList.subList(start, end+1)));
				newweekhisList.add(historyData);
				weekhisid++;
				start=end+1;
			}
		}
		
		//插入周历史数据
		HistoryDataPack historyDataPack=new HistoryDataPack();
		historyDataPack.setSiid(siid);
		historyDataPack.setList(newweekhisList);
		weekHDataMapper.insertWeekHData_list(historyDataPack);
		
		//更新inf
		if(type.equals("s"))
		{
			StockInf stockInf=new StockInf();
			stockInf.setSid(siid);
			stockInf.setWeekhisid(weekid);
			stockInfMapper.updateStockInfWeekhisid_e_sid(stockInf);
		}
		else if(type.equals("i"))
		{
			IndiceInf indiceInf=new IndiceInf();
			indiceInf.setIid(siid);
			indiceInf.setWeekhisid(weekid);
			indiceInfMapper.updateIndiceInfWeekhisid_e_iid(indiceInf);
		}
	}
}
