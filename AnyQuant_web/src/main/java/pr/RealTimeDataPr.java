package pr;

import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;
import java.util.TimerTask;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mapper.IndiceInfMapper;
import mapper.RealTimeDataMapper;
import mapper.StockInfMapper;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import po.IndiceInf;
import po.RealTimeData;
import po.RealTimeDataPack;
import po.RealTimeDataString;
import po.StockInf;
import tool.JsonExchangeTool;
/**
 * 从API获取数据，计算并存储到数据库中
 * @author YU Fan
 * @date 2016年5月10日
 */
public class RealTimeDataPr{
	private ApplicationContext applicationContext;
	private RealTimeDataMapper realTimeDataMapper;
	private StockInfMapper stockInfMapper;
	private IndiceInfMapper indiceInfMapper;
	
	/**创建对象时为mapper注入对象*/
	public RealTimeDataPr()
	{
		applicationContext =new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		realTimeDataMapper=(RealTimeDataMapper) applicationContext.getBean("realTimeDataMapper");
		stockInfMapper=(StockInfMapper)applicationContext.getBean("stockInfMapper");
		indiceInfMapper=(IndiceInfMapper)applicationContext.getBean("indiceInfMapper");
	}
	
	/**初始创建实时数据表*/
	public void createRealTimeDataTable_all() throws Exception
	{
		List<StockInf> stockList=stockInfMapper.selectStockInf_all();
		for(int i=0;i<stockList.size();i++)
		{
			realTimeDataMapper.createRealTimeDataTable(stockList.get(i).getSid());
		}
		List<IndiceInf> indiceList=indiceInfMapper.selectIndiceInf_all(); 
		for(int i=0;i<indiceList.size();i++)
		{
			realTimeDataMapper.createRealTimeDataTable(indiceList.get(i).getIid());
		}
	}
	
	/**插入单条实时数据-单个单条 */
	public void insertRealTimeData_single(String gid) throws Exception
	{
		RealTimeDataPack realTimeDataPack=new RealTimeDataPack();
		realTimeDataPack.setSiid(gid);
		RealTimeData realTimeData=JsonExchangeTool.getRealTimeData(gid);
		realTimeDataPack.setRealTimeData1(realTimeData);
		//数据库中最新一天
		Date latestdate=realTimeDataMapper.selectRealTimeDataDate(gid);
		if(latestdate==null)
		{
			realTimeDataMapper.insertRealTimeData_single(realTimeDataPack);
			return;
		}
		//API中最新一天
		Date today=realTimeData.getDate();
		if(today.getYear()==latestdate.getYear()&&today.getMonth()==latestdate.getMonth()&&today.getDay()==latestdate.getDay())
		{
			Time nowTime=realTimeDataPack.getRealTimeData1().getTime();
			Time latestTime=realTimeDataMapper.selectRealTimeDataTime_max(gid);
			if(nowTime.getHours()==latestTime.getHours()&&nowTime.getMinutes()==latestTime.getMinutes()&&nowTime.getSeconds()==latestTime.getSeconds())
			{	
				return ;
			}
			else
			{
				realTimeDataMapper.insertRealTimeData_single(realTimeDataPack);
			}
		}
		else
		{
			if(Calendar.getInstance().get(Calendar.HOUR)>=9)
			{
				realTimeDataMapper.initRealTimeDataTable(gid);
				realTimeDataMapper.insertRealTimeData_single(realTimeDataPack);
			}
		}
	}
	
	//(除法定节假日外周一到周五，早上从9：30--11：30，下午是：13：00--15：00,每2分钟)插入股票实时数据
	/**插入实时数据-全部单条 */
	public void insertRealTimeData_all() throws Exception
	{
		List<StockInf> stockList=null;
		try {
			stockList=stockInfMapper.selectStockInf_all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<stockList.size();i++)
		{
			insertRealTimeData_single(stockList.get(i).getSid());
		}

		List<IndiceInf> indiceList=null;
		try {
			indiceList=indiceInfMapper.selectIndiceInf_all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<indiceList.size();i++)
		{
			insertRealTimeData_single(indiceList.get(i).getIid());
		}
	}
	/**插入单条实时数据-多个单条 */
	public void insertRealTimeData_list() throws Exception
	{
		RealTimeDataPack realTimeDataPack=new RealTimeDataPack();
		realTimeDataPack.setList(new ArrayList<RealTimeData>());

		List<String> list=new ArrayList<String>();
		List<StockInf> stockList=null;
		try {
			stockList=stockInfMapper.selectStockInf_all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<stockList.size();i++)
		{
			list.add(stockList.get(i).getSid());
		}
		List<IndiceInf> indiceList=null;
		try {
			indiceList=indiceInfMapper.selectIndiceInf_all();
		} catch (Exception e) {
			e.printStackTrace();
		}
		for(int i=0;i<indiceList.size();i++)
		{
			list.add(indiceList.get(i).getIid());
		}
		
		Date latestdate=realTimeDataMapper.selectRealTimeDataDate(list.get(0));//只看一个
		if(latestdate!=null)
		{
			Date today=JsonExchangeTool.getRealTimeData(list.get(0)).getDate();
			if(today.getYear()==latestdate.getYear()&&today.getMonth()==latestdate.getMonth()&&today.getDay()==latestdate.getDay())
			{
				;
			}
			else
			{
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=9)
				{
					realTimeDataMapper.initRealTimeData_all(list);
				}
			}
			
			for(int i=0;i<list.size();i++)
			{
				RealTimeData realTimeData=JsonExchangeTool.getRealTimeData(list.get(i));
				realTimeDataPack.getList().add(realTimeData);
			}
		}
		if(realTimeDataPack.getList().size()>=1)
		{
			realTimeDataMapper.insertRealTimeData_all(realTimeDataPack);
		}
		else
		{
			System.out.println("no update");
		}
	}
	//每30分钟更新(所有实时数据)
	public static void main(String[] args)
	{
		
		RealTimeDataPr realTimeDataPr=new RealTimeDataPr();
//		try {
//			realTimeDataPr.createRealTimeDataTable_all();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
		
		//定时任务
		Runnable task=new Runnable()
		{
			@Override
			public void run() {
				if(Calendar.getInstance().get(Calendar.HOUR_OF_DAY)>=9&&Calendar.getInstance().get(Calendar.HOUR_OF_DAY)<=15)
				{
					try {
						realTimeDataPr.insertRealTimeData_list();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
			}
		};
		ScheduledExecutorService service = Executors  
                .newSingleThreadScheduledExecutor();  
        // 第二个参数为首次执行的延时时间，第三个参数为定时执行的间隔时间  
        service.scheduleAtFixedRate(task, 0, 30, TimeUnit.MINUTES);  
	}
}
