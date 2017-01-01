package backtest.impl;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import mapper.HistoryDataMapper;
import mapper.QuotaDataMapper;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import po.DatePack;
import po.HistoryData;
import po.QuotaData;
import vo.QuotaAnalysis;
import vo.Scale;
import vo.TradeSignal;
import backtest.BackTestInnerService;
import backtest.tool.AnalyseSignalPack;
import backtest.tool.DateDouble;
import backtest.tool.HisQuoPack;
import backtest.tool.TestReport;
import bl.BiasAnalyse;
import bl.QuotaAnalyse;

public class BackTestInnerServiceImpl implements BackTestInnerService {
	/**股仓(暂时只有一只股票)*/
	public List<Scale> scales;
	/**分析结果和交易标志*/
	public List<AnalyseSignalPack> analyseSignalPacks;
	
	/**现金*/
	public double cash;
	/**每只股票的持股数*/
	List<Integer> numlist;
	
	/**税费(默认采用买千分之一，卖千分之二的税费)*/
	public double inTaxRatio;
	public double outTaxRatio;
	
	/**回测开始日期*/
	public Date startdate;
	/**回测结束日期*/
	public Date enddate;
	/**回测周期（单位：日）*/
	public int n;
	/**每日资本*/
	public List<DateDouble> capital;
	
	/**基准*/
	public String benchmark;
	/**分析结果和交易标志*/
	public AnalyseSignalPack bAnalyseSignalPack;
	/**每日资本-基准*/
	public List<DateDouble> bCapital;
	/**基准现金*/
	public double bCash;
	/**基准股数*/
	public double bNum;
	
	public void setParam(List<Scale> scales,double cash,Date startdate,Date enddate,int n,
			List<AnalyseSignalPack> analyseSignalPacks,String benchmark,AnalyseSignalPack bAnalyseSignalPack) throws Exception
	{
		this.scales=scales;

		int sum=0;
		for(int i=0;i<scales.size();i++)
		{
			sum+=scales.get(i).percent;
		}
		if(sum!=100)
			throw new Exception();
		
		this.cash=cash;
		this.inTaxRatio=0.001;
		this.outTaxRatio=0.002;
		this.startdate=startdate;
		this.enddate=enddate;
		this.n=n;
		this.analyseSignalPacks=analyseSignalPacks;
		numlist=new ArrayList<Integer>();
		capital=new ArrayList<DateDouble>();
//		capital.add(cash);
		
		//---------------基准
		this.benchmark=benchmark;
		bCapital=new ArrayList<DateDouble>();
//		bCapital.add(cash);
		this.bAnalyseSignalPack=bAnalyseSignalPack;
		bCash=cash;
		bNum=0;
		//-------------------
	}
	
	@Override
	public TestReport runTest(List<Scale> scales,List<AnalyseSignalPack> analyseSignalPacks,AnalyseSignalPack bAnalyseSignalPack) throws Exception 
	{
		setParam(scales,100000,new Date(115,0,1),new Date(116,0,1),10,analyseSignalPacks,"sh000300",bAnalyseSignalPack);
		return test();
	}

	@Override
	public TestReport runTest_self(List<Scale> scales,double invest,Date startdate, Date enddate, int n,
			List<AnalyseSignalPack> analyseSignalPacks,String benchmark,AnalyseSignalPack bAnalyseSignalPack) throws Exception {
		setParam(scales,invest,startdate,enddate,n,analyseSignalPacks,benchmark,bAnalyseSignalPack);
		return test();
	}
	
	public TestReport test() throws Exception
	{
//File file=new File("test.txt");////////////////////////////////////////////////////
//FileWriter fw=new FileWriter(file,true);/////////////////////////////////////////////
//BufferedWriter bufferWritter = new BufferedWriter(fw);//////////////////////////////////
		//填充历史数据
		ApplicationContext applicationContext=new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml");
		HistoryDataMapper historyDataMapper=(HistoryDataMapper) applicationContext.getBean("historyDataMapper");
		QuotaDataMapper quotaDataMapper=(QuotaDataMapper) applicationContext.getBean("quotaDataMapper");

		List<HisQuoPack> HQstatisticlist=new ArrayList<HisQuoPack>();
		
		DatePack datePack=new DatePack();
		datePack.setDate1(startdate);
		datePack.setDate2(enddate);
		for(int i=0;i<scales.size();i++)
		{
			datePack.setSiid(scales.get(i).siid);
			List<HistoryData> hisList=historyDataMapper.selectHistoryData_b_date(datePack);
			List<QuotaData> quoList=quotaDataMapper.selectQuotaData_b_date(datePack);
			HQstatisticlist.add(new HisQuoPack(hisList,quoList));
		}
//System.out.println(HQstatisticlist);
		capital.add(new DateDouble(HQstatisticlist.get(0).hislist.get(0).getDate(),cash));
		bCapital.add(new DateDouble(HQstatisticlist.get(0).hislist.get(0).getDate(),bCash));
		//--------------基准
		datePack.setSiid(benchmark);
		HisQuoPack bHQstatisticlist=new HisQuoPack(historyDataMapper.selectHistoryData_b_date(datePack),quotaDataMapper.selectQuotaData_b_date(datePack));
		//----------------
		
		//建仓,假设没有税费,从此次开始买入和卖出均用收盘价
		for(int i=0;i<scales.size();i++)
		{
			numlist.add((int)(cash*scales.get(i).percent/100.0/HQstatisticlist.get(i).hislist.get(0).getClose()));
//bufferWritter.write(scales.get(i).siid+"\n");
//bufferWritter.write("day 0 close "+HQstatisticlist.get(i).hislist.get(0).getClose()+"\n");
//bufferWritter.write("num "+numlist.get(i)+"\n");
		}
		for(int i=0;i<scales.size();i++)
		{
			cash-=numlist.get(i)*HQstatisticlist.get(i).hislist.get(0).getClose();
		}
//bufferWritter.write("cash "+cash+"\n");
//bufferWritter.write("capital "+capital.get(0)+"\n");
//bufferWritter.write("\n");
		//---------------基准
		int bNum=(int)(bCash/bHQstatisticlist.hislist.get(0).getClose());
		bCash-=bNum*bHQstatisticlist.hislist.get(0).getClose();
		//-----------------
		
		//开始回测历史
		List<Double> inPrice=new ArrayList<Double>();
		List<Double> outPrice=new ArrayList<Double>();
		
		for(int k=n-1;k<HQstatisticlist.get(0).hislist.size();k=k+n)//n=10默认每五天
		{
			double capitaltoday=0;//今日股票资本
			double inprice=0;//今日买入总价
			double outprice=0;//今日卖出总价
			for(int j=0;j<scales.size();j++)//对于股仓中的某支股票
			{
				AnalyseSignalPack analyseSignalPack=analyseSignalPacks.get(j);
				boolean sellFlag=false;
				boolean buyFlag=false;
				for(int i=0;i<analyseSignalPack.analyses.size();i++)//对于某股票的某个指标
				{
					QuotaAnalyse analyse=analyseSignalPack.analyses.get(i);
					//每次设置新的
					analyse.setAnalysis(new QuotaAnalysis());
					analyse.setEnddate(HQstatisticlist.get(j).hislist.get(k-1).getDate());
					
					TradeSignal tradeSignal=analyseSignalPack.signals.get(i);
					QuotaAnalysis analysis=analyse.pro_quo();
//bufferWritter.write(scales.get(j).siid+" sin "+analysis.scoreIn+" sout "+analysis.scoreOut+"\n"+analysis.message);///////////////////////////////////
					if(analysis.scoreIn>analysis.scoreOut
							&&analysis.scoreIn>tradeSignal.sell.scoreIn)
						buyFlag=true;
					else
						buyFlag=false;
					
					if(analysis.scoreOut>analysis.scoreIn
							&&analysis.scoreOut>tradeSignal.sell.scoreOut)
						sellFlag=true;
					else
						sellFlag=false;
				}
				double price=HQstatisticlist.get(j).hislist.get(k).getClose();
//bufferWritter.write("day "+k+" close "+HQstatisticlist.get(j).hislist.get(k).getClose()+"\n");
				
				if(sellFlag==true&&buyFlag==false)//sell
				{
					int num=numlist.get(j);
					cash+=num*price*(1-outTaxRatio);
					numlist.set(j,0);//减仓
					outprice+=num*price;
//bufferWritter.write("sell \n");
//bufferWritter.write("out:"+num*price*(1-outTaxRatio)+" \n");///////////////////////////
				}
				else if(sellFlag==false&&buyFlag==true)//buy
				{
					int num=(int)(cash/(price*(1+inTaxRatio)));//可以买入多少只
//bufferWritter.write("buy \n");
//bufferWritter.write("in:"+num+"\n");/////////////////////////////////////////////
					cash-=num*price*(1+inTaxRatio);
					numlist.set(j,numlist.get(j)+num);//加仓
					inprice+=num*price;
				}
				else//wait
				{
					;
//bufferWritter.write("wait \n");
				}
				capitaltoday+=numlist.get(j)*price;
			}
			capital.add(new DateDouble(HQstatisticlist.get(0).hislist.get(k).getDate(),capitaltoday+cash));
			try{

//bufferWritter.write("inPrice today "+inprice+"\n");/////////////////////////////////////////////
//bufferWritter.write("outPrice today "+outprice+"\n");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
			inPrice.add(inprice);
			outPrice.add(outprice);
//bufferWritter.write("num "+numlist.get(0)+"\n");
//bufferWritter.write("cash "+cash+"\n");
//bufferWritter.write("capital "+capital.get((k+1)/n)+"\n");
//bufferWritter.write("\n");
			//-----------------------------------------------------------------基准
			boolean bSellFlag=false;
			boolean bBuyFlag=false;
			
			for(int i=0;i<bAnalyseSignalPack.analyses.size();i++)//对于基准的某个指标
			{
//				QuotaAnalyse analyse=bAnalyseSignalPack.analyses.get(i);
				TradeSignal tradeSignal=bAnalyseSignalPack.signals.get(i);
				QuotaAnalysis analysis=bAnalyseSignalPack.analyses.get(i).pro_quo();
				if(analysis.scoreIn>analysis.scoreOut
						&&analysis.scoreIn>tradeSignal.buy.scoreIn)
					bBuyFlag=true;
				else
					bBuyFlag=false;
				
				if(analysis.scoreOut>analysis.scoreIn
						&&analysis.scoreOut>tradeSignal.sell.scoreOut)
					bSellFlag=true;
				else
					bSellFlag=false;
			}
			double price=bHQstatisticlist.hislist.get(k).getClose();
			if(bSellFlag==true&&bBuyFlag==false)//sell
			{
				bCash+=bNum*price*(1-outTaxRatio);
				bNum=0;//减仓
			}
			else if(bSellFlag==false&&bBuyFlag==true)//buy
			{
				int num=(int)(bCash/price*(1+inTaxRatio));//可以买入多少只
				bCash-=num*price*(1+inTaxRatio);
				bNum+=num;//加仓
			}
			else//wait
			{
				;
			}
			bCapital.add(new DateDouble(HQstatisticlist.get(0).hislist.get(k).getDate(),bNum*price+bCash));
			//-----------------------------------------------------------------
		}
//bufferWritter.close();//////////////////////////////////////////////////////////////////////////
		//生成回测报告
		TestReport testReport=new TestReport(bHQstatisticlist.hislist.size()/n,capital,bCapital,inPrice,outPrice);
		
		testReport.run(capital.get(capital.size()-1).value,capital.get(0).value,bCapital.get(bCapital.size()-1).value,bCapital.get(0).value);
		return testReport;
	}

}
