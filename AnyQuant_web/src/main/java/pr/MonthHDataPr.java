package pr;

import java.util.ArrayList;
import java.util.List;

import mapper.HistoryDataMapper;
import mapper.IndiceInfMapper;
import mapper.StockInfMapper;
import mapper.MonthHDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.HistoryData;
import po.HistoryDataPack;
import po.IndiceInf;
import po.StockInf;
import tool.MMSTool;

public class MonthHDataPr {
	public static ApplicationContext applicationContext;
	public static HistoryDataMapper historyDataMapper;
	public static MonthHDataMapper monthHDataMapper;
	public static StockInfMapper stockInfMapper;
	public static IndiceInfMapper indiceInfMapper;
	/**历史数据列表*/
	public List<HistoryData> hisList;
	/**要新写的月历史数据列表*/
	public List<HistoryData> newmonthhisList;
	/**历史数据中最新月id*/
	public int monthid;
	/**月历史数据最近id*/
	public int monthhisid;
	
	/**创建对象时为mapper注入对象*/
	public MonthHDataPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		monthHDataMapper=(MonthHDataMapper) applicationContext.getBean("monthHDataMapper");
		stockInfMapper=(StockInfMapper)applicationContext.getBean("stockInfMapper");
		indiceInfMapper=(IndiceInfMapper)applicationContext.getBean("indiceInfMapper");
		monthid=-1;
		monthhisid=-1;
	}
	
	public void insertMonthHData_all() throws Exception
	{
		List<StockInf> stockList=stockInfMapper.selectStockInf_all();
		for(int i=0;i<stockList.size();i++)
		{
			insertMonthHData_single("s",stockList.get(i).getSid());
		}
		
		List<IndiceInf> indiceList=indiceInfMapper.selectIndiceInf_all();
		for(int i=0;i<indiceList.size();i++)
		{
			insertMonthHData_single("i",indiceList.get(i).getIid());
		}
	}
	
	public void insertMonthHData_single(String type,String siid) throws Exception
	{
		//从数据库stockinf表中读取monthid和monthhisid
		monthid=-1;
		monthhisid=-1;
		if(type.equals("s"))
		{
			StockInf stockInf=stockInfMapper.selectStockInf_e_sid(siid);
			monthid=stockInf.getMonthid();
			monthhisid=stockInf.getMonthhisid();
		}
		else if(type.equals("i"))
		{
			IndiceInf indiceInf=indiceInfMapper.selectIndiceInf_e_iid(siid);
			monthid=indiceInf.getMonthid();
			monthhisid=indiceInf.getMonthhisid();
		}
		else
		{
			return ;//exception
		}
		if(monthid==-1)
		{
			return ;
		}
		if(monthhisid==-1)
		{
			monthHDataMapper.createMonthHDataTable(siid);
		}
		
		//从数据库中读取需要更新的月的历史数据
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setId1(monthhisid);
		datePack.setId2(monthid);
		hisList=historyDataMapper.selectHistoryData_b_monthid(datePack);
		if(monthhisid!=-1)
		{
			monthHDataMapper.deleteMonthHData_e_monthid(datePack);
		}
		else
		{
			monthhisid++;
		}
		newmonthhisList=new ArrayList<HistoryData>();
		
		int start=0;
		int end=-1;
		int itr=0;
		while(itr<hisList.size())
		{
			//看此时itr取到的历史数据的monthid是否是接下来要处理的
			if(hisList.get(itr).getMonthid()==monthhisid&&itr!=hisList.size()-1)
			{
				end++;
				itr++;
			}
			else
			{
				if(hisList.get(itr).getMonthid()==monthhisid&&itr==hisList.size()-1)
				{
					end++;
					itr++;
				}
				HistoryData historyData=new HistoryData();
				historyData.setMonthid(monthhisid);
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
				newmonthhisList.add(historyData);
				monthhisid++;
				start=end+1;
			}
		}
		
		//插入月历史数据
		HistoryDataPack historyDataPack=new HistoryDataPack();
		historyDataPack.setSiid(siid);
		historyDataPack.setList(newmonthhisList);
		monthHDataMapper.insertMonthHData_list(historyDataPack);
		
		//更新inf
		if(type.equals("s"))
		{
			StockInf stockInf=new StockInf();
			stockInf.setSid(siid);
			stockInf.setMonthhisid(monthid);
			stockInfMapper.updateStockInfMonthhisid_e_sid(stockInf);
		}
		else if(type.equals("i"))
		{
			IndiceInf indiceInf=new IndiceInf();
			indiceInf.setIid(siid);
			indiceInf.setMonthhisid(monthid);
			indiceInfMapper.updateIndiceInfMonthhisid_e_iid(indiceInf);
		}
	}
}
