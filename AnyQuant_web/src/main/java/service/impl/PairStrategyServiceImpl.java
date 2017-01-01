package service.impl;

import java.sql.Date;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

//import org.apache.catalina.core.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import mapper.StockInfMapper;
import data.impl.DataServiceImpl;
import bl.PairStrategy;
import po.AreaInf;
import po.ConceptInf;
import po.IndustryInf;
import po.StockInf;
import service.PairStrategyService;
import vo.StockPair;

public class PairStrategyServiceImpl implements PairStrategyService {
	private StockInfMapper stockInfMapper;
	
	@Override
	public List<StockPair> getPairStrategyRecommend(List<StockInf> sidlist,int quo,int num,Date enddate,double alpha) throws Exception {
		List<StockPair> recList=PairStrategy.recommend(sidlist,num, enddate, alpha);
		List<StockPair> result=new ArrayList<StockPair>();
		result.add(recList.get(0));
		for(int i=1;i<Math.min(quo,recList.size());i++)
		{
			for(int j=i-1;j>=0;j--)
			{
				if(recList.get(i).correlation>result.get(j).correlation)
				{
					if(j==0)
						result.add(j,recList.get(i));
					continue;
				}
				else
				{
					result.add(j+1,recList.get(i));
					break;
				}
			}
		}
		if(quo<=result.size())
		{
			for(int i=result.size();i<recList.size();i++)
			{
				for(int j=i-1;j>=0;j--)
				{
					if(recList.get(i).correlation>result.get(j).correlation)
					{
						if(j==0)
							result.add(j,recList.get(i));
						continue;
					}
					else
					{
						result.add(j+1,recList.get(i));
						break;
					}
				}
			}
		}
		return result;
	}

	@Override
	public List<StockPair> getPairStrategyRecommendArea(int quo, String cname)
			throws Exception {
		List<AreaInf> areaList=new DataServiceImpl().getAreaInf_area(cname);
		List<StockInf> sidlist=new ArrayList<StockInf>();
		for(int i=0;i<areaList.size();i++)
		{
			sidlist.add(new StockInf(areaList.get(i).getSid(),areaList.get(i).getName()));
		}
		return getPairStrategyRecommend(sidlist, quo, 10, new Date(Calendar.getInstance().getTimeInMillis()),0.05);
	}
	@Override
	public List<StockPair> getPairStrategyRecommendIndustry(int quo,
			String cname) throws Exception {
		List<IndustryInf> industryList=new DataServiceImpl().getIndustryInf_cname(cname);
		List<StockInf> sidlist=new ArrayList<StockInf>();
		for(int i=0;i<industryList.size();i++)
		{
			sidlist.add(new StockInf(industryList.get(i).getSid(),industryList.get(i).getName()));
		}
		return getPairStrategyRecommend(sidlist, quo, 10, new Date(Calendar.getInstance().getTimeInMillis()),0.05);
	}
	@Override
	public List<StockPair> getPairStrategyRecommendConcept(int quo, String cname)
			throws Exception {
		List<ConceptInf> conceptList=new DataServiceImpl().getConceptInf_cname(cname);
		List<StockInf> sidlist=new ArrayList<StockInf>();
		for(int i=0;i<conceptList.size();i++)
		{
			sidlist.add(new StockInf(conceptList.get(i).getSid(),conceptList.get(i).getName()));
		}
		return getPairStrategyRecommend(sidlist, quo, 10, new Date(Calendar.getInstance().getTimeInMillis()),0.05);
	}

	@Override
	public List<StockPair> getSolemate(String sid) throws Exception {
		stockInfMapper=(StockInfMapper) new ClassPathXmlApplicationContext("classpath:configure/spring/applicationContext-dao.xml").getBean("stockInfMapper");
		StockInf stockInf=stockInfMapper.selectStockInf_e_sid(sid);
		List<StockInf> list=stockInfMapper.selectStockInf_all();
		list.remove(stockInf.getId());
		List<StockPair> recList=PairStrategy.getPair(stockInf,list);
		List<StockPair> result=new ArrayList<StockPair>();
		result.add(recList.get(0));
		for(int i=1;i<Math.min(3,recList.size());i++)
		{
			for(int j=i-1;j>=0;j--)
			{
				if(recList.get(i).correlation>result.get(j).correlation)
				{
					if(j==0)
						result.add(j,recList.get(i));
					continue;
				}
				else
				{
					result.add(j+1,recList.get(i));
					break;
				}
			}
		}
		if(3<=result.size())
		{
			for(int i=result.size();i<recList.size();i++)
			{
				for(int j=i-1;j>=0;j--)
				{
					if(recList.get(i).correlation>result.get(j).correlation)
					{
						if(j==0)
							result.add(j,recList.get(i));
						continue;
					}
					else
					{
						result.add(j+1,recList.get(i));
						break;
					}
				}
			}
		}
		return result;
	}
}
