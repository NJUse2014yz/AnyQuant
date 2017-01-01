package pr;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.sql.Date;
import java.util.Calendar;
import java.util.List;

import mapper.IndiceInfMapper;
import mapper.StockInfMapper;
import mapper.HistoryDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.HistoryData;
import po.HistoryDataPack;
import po.IndiceInf;
import po.StockInf;
import tool.JsonExchangeTool;

public class HistoryDataPr {
	private ApplicationContext applicationContext;
	private HistoryDataMapper historyDataMapper;
	private StockInfMapper stockInfMapper;
	private IndiceInfMapper indiceInfMapper;
	
	/**创建对象时为mapper注入对象*/
	public HistoryDataPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		stockInfMapper=(StockInfMapper)applicationContext.getBean("stockInfMapper");
		indiceInfMapper=(IndiceInfMapper)applicationContext.getBean("indiceInfMapper");
	}
	
	/**插入历史数据-全部最近 */
	public void insertHistoryData_all() throws Exception
	{
		List<StockInf> stockList=stockInfMapper.selectStockInf_all();
		for(int i=0;i<stockList.size();i++)
		{
			insertHistoryData_single("s",stockList.get(i).getSid());
		}
		
		List<IndiceInf> indiceList=indiceInfMapper.selectIndiceInf_all();
		for(int i=0;i<indiceList.size();i++)
		{
			insertHistoryData_single("i",indiceList.get(i).getIid());
		}
	}
	/**插入历史数据-单个最近*/
	private void insertHistoryData_single(String type,String gid) throws Exception
	{
		List<HistoryData> list=null;
		HistoryData latestHistoryData=getHistoryData_new(type,gid);
		
		if(latestHistoryData==null)
		{
			latestHistoryData=new HistoryData();
			historyDataMapper.createHistoryDataTable(gid);
		}
		if(type.equals("s"))
		{
			list=JsonExchangeTool.getStockHistoryData(gid, latestHistoryData.getDate(), new Date(Calendar.getInstance().getTimeInMillis()));
		}
		else if(type.equals("i"))
		{
			list=JsonExchangeTool.getIndiceHistoryData(gid, latestHistoryData.getDate(), new Date(Calendar.getInstance().getTimeInMillis()));
		}
		else
		{
			throw new Exception();
		}
		if(list==null)
		{
			return ;
		}
		for(int i=list.size()-1;i>=0;i--)
		{
			HistoryData nowHistoryData=list.get(i);
			nowHistoryData.setYearid(nowHistoryData.getDate().getYear()+1900);
			nowHistoryData.setId(latestHistoryData.getId()+1);
			if(latestHistoryData.getDate()==null)
			{
				nowHistoryData.setWeekid(0);
				nowHistoryData.setMonthid(0);
			}
			else
			{
				if(nowHistoryData.getDate().getMonth()==latestHistoryData.getDate().getMonth())
				{
					nowHistoryData.setMonthid(latestHistoryData.getMonthid());
				}
				else
				{
					nowHistoryData.setMonthid(latestHistoryData.getMonthid()+1);
				}
				
				Calendar cal1=Calendar.getInstance();
				cal1.setTime(nowHistoryData.getDate());
				Calendar cal2=Calendar.getInstance();
				cal2.setTime(latestHistoryData.getDate());
				if(cal1.get(Calendar.WEEK_OF_YEAR)==cal2.get(Calendar.WEEK_OF_YEAR))
				{
					nowHistoryData.setWeekid(latestHistoryData.getWeekid());
				}
				else
				{
					nowHistoryData.setWeekid(latestHistoryData.getWeekid()+1);
				}
			}
			latestHistoryData=nowHistoryData;
			
		}
		
		HistoryDataPack historyDataPack=new HistoryDataPack();
		historyDataPack.setSiid(gid);
		historyDataPack.setList(list);
		historyDataMapper.insertHistoryData_list(historyDataPack);
		
		if(list!=null)
		{
			if(type.equals("s"))
			{
				//更新日期
				StockInf stockInf=new StockInf();
				stockInf.setSid(gid);
				stockInf.setDate(list.get(0).getDate());
				stockInf.setHisid(latestHistoryData.getId());
				stockInf.setWeekid(list.get(0).getWeekid());
				stockInf.setMonthid(list.get(0).getMonthid());
				stockInfMapper.updateStockInfDateHWMid_e_sid(stockInf);
			}
			else if(type.equals("i"))
			{
				IndiceInf indiceInf=new IndiceInf();
				indiceInf.setIid(gid);
				indiceInf.setDate(list.get(0).getDate());
				indiceInf.setHisid(latestHistoryData.getId());
				indiceInf.setWeekid(list.get(0).getWeekid());
				indiceInf.setMonthid(list.get(0).getMonthid());
				indiceInfMapper.updateIndiceInfDateHWMid_e_iid(indiceInf);
			}
		}
	}
	
	/**获取最近的历史数据 */
	public HistoryData getHistoryData_new(String type,String siid) throws Exception
	{
		Date date=null;
		if(type.equals("s"))
		{
			date=stockInfMapper.selectStockInf_e_sid(siid).getDate();
		}
		else if(type.equals("i"))
		{
			date=indiceInfMapper.selectIndiceInf_e_iid(siid).getDate();
		}
		
		if(date==null)
		{
			return null;
		}
		
		DatePack datePack=new DatePack();
		datePack.setSiid(siid);
		datePack.setDate1(date);
		return historyDataMapper.selectHistoryData_e_date(datePack);
	}
}
