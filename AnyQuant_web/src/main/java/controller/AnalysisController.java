package controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import backtest.tool.TestReport;
import bl.BiasAnalyse;
import bl.BollAnalyse;
import bl.DmiAnalyse;
import bl.KdjAnalyse;
import bl.MacdAnalyse;
import bl.ObvAnalyse;
import bl.QuotaAnalyse;
import bl.RocAnalyse;
import bl.RsiAnalyse;
import bl.VrAnalyse;
import service.BackTestService;
import service.QuotaAnalyseService;
import service.StockDataService;
import vo.QuotaAnalysis;
import vo.StockListInf;

@Controller
public class AnalysisController {
	@Autowired
	private StockDataService stockBl;
	@Autowired
	private QuotaAnalyseService quato;
	@Autowired
	private BackTestService back;
	
	@RequestMapping("/analysis")
	public ModelAndView stockAnalysis(String id) throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		StockListInf inf = stockBl.showSingleStock(id);		
		modelAndView.setViewName("analysis/analysis");
		modelAndView.addObject("inf", inf);
		return modelAndView;
	}
	@RequestMapping("/strategy")
	public ModelAndView stockStrategy(String id) throws Exception{
		ModelAndView modelAndView=new ModelAndView();		
		modelAndView.setViewName("analysis/strategy");
		StockListInf inf = stockBl.showSingleStock(id);
		modelAndView.addObject("inf", inf);
		return modelAndView;
	}
	
	@RequestMapping("/stocksInput")
	public String stocksInput(){
		return "analysis/stocksInput";
	}
	
	@RequestMapping("/getAnalysis")
	public @ResponseBody List<QuotaAnalysis> getAnalysis(String id,String quotas)throws Exception{
		List<QuotaAnalysis> list=null;
		if(id.startsWith("sh000")||id.startsWith("sz399")){
			list=getGrailAnalysisList(id,quotas);
		}
		else{	
			list=getAnalysisList(id,quotas);
		}
		return list;
	}
	@RequestMapping("/getStrategy")
	public @ResponseBody TestReport getStrategy(String id,String quotas)throws Exception{
		List<QuotaAnalyse> list1=new ArrayList<QuotaAnalyse>();
		List<QuotaAnalyse> list2=new ArrayList<QuotaAnalyse>();
		//无指标
		if(quotas.equals("end"))
			return null;
		String[] strs=quotas.split(",");
		int len=strs.length-1;
		QuotaAnalyse re=null;
		for(int i=0;i<len;i++){
			switch (strs[i]) {
			case "bias":
				re=new BiasAnalyse(id);
				list1.add(re);
				re=new BiasAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "boll":
				re=new BollAnalyse(id);
				list1.add(re);
				re=new BollAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "dmi":
				re=new DmiAnalyse(id);
				list1.add(re);
				re=new DmiAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "kdj":
				re=new KdjAnalyse(id);
				list1.add(re);
				re=new KdjAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "macd":
				re=new MacdAnalyse(id);
				list1.add(re);
				re=new MacdAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "obv":
				re=new ObvAnalyse(id);
				list1.add(re);
				re=new ObvAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "roc":
				re=new RocAnalyse(id);
				list1.add(re);
				re=new RocAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "rsi":
				re=new RsiAnalyse(id);
				list1.add(re);
				re=new RsiAnalyse("i","sh000300");
				list2.add(re);
				break;
			case "vr":
				re=new VrAnalyse(id);
				list1.add(re);
				re=new VrAnalyse("i","sh000300");
				list2.add(re);
				break;
			}			
		}
		TestReport report=back.backtest_single(id, list1, list2);
		return report;
	}
	@RequestMapping("/getAnalysisCompare")
	public @ResponseBody List<QuotaAnalysis> getAnalysisCompare(String id1,String id2)throws Exception{
		List<QuotaAnalysis> list=new ArrayList<QuotaAnalysis>();
		
		QuotaAnalysis re=null;
		re= quato.getKdjAnalysis(id1);
		list.add(re);
		re= quato.getKdjAnalysis(id2);
		list.add(re);
		re= quato.getMacdAnalysis(id1);
		list.add(re);
		re= quato.getMacdAnalysis(id2);
		list.add(re);
		re= quato.getObvAnalysis(id1);
		list.add(re);
		re= quato.getObvAnalysis(id2);
		list.add(re);
		re= quato.getRsiAnalysis(id1);
		list.add(re);
		re= quato.getRsiAnalysis(id2);
		list.add(re);
		re= quato.getVrAnalysis(id1);
		list.add(re);			
		re= quato.getVrAnalysis(id2);
		list.add(re);
		return list;
	}
	private List<QuotaAnalysis> getGrailAnalysisList(String id,String quotas)throws Exception{
		List<QuotaAnalysis> list=new ArrayList<QuotaAnalysis>();
		//无指标
		if(quotas.equals("end"))
			return list;
		String[] strs=quotas.split(",");
		int len=strs.length-1;
		QuotaAnalysis re=null;
		for(int i=0;i<len;i++){
			switch (strs[i]) {
			case "bias":
				re=quato.getBiasAnalysis("i",id);
				break;
			case "boll":
				re= quato.getBollAnalysis("i",id);			
				break;
			case "dmi":
				re= quato.getDmiAnalysis("i",id);
				break;
			case "kdj":
				re= quato.getKdjAnalysis("i",id);
				break;
			case "macd":
				re= quato.getMacdAnalysis("i",id);
				break;
			case "obv":
				re= quato.getObvAnalysis("i",id);
				break;
			case "roc":
				re= quato.getRocAnalysis("i",id);
				break;
			case "rsi":
				re= quato.getRsiAnalysis("i",id);
				break;
			case "vr":
				re= quato.getVrAnalysis("i",id);
				break;
			}			
			list.add(re);			
		}
		return list;
	}
	private List<QuotaAnalysis> getAnalysisList(String id,String quotas)throws Exception{
		List<QuotaAnalysis> list=new ArrayList<QuotaAnalysis>();
		//无指标
		if(quotas.equals("end"))
			return list;
		String[] strs=quotas.split(",");
		int len=strs.length-1;
		QuotaAnalysis re=null;
		for(int i=0;i<len;i++){
			switch (strs[i]) {
			case "bias":
				re=quato.getBiasAnalysis(id);
				break;
			case "boll":
				re= quato.getBollAnalysis(id);			
				break;
			case "dmi":
				re= quato.getDmiAnalysis(id);
				break;
			case "kdj":
				re= quato.getKdjAnalysis(id);
				break;
			case "macd":
				re= quato.getMacdAnalysis(id);
				break;
			case "obv":
				re= quato.getObvAnalysis(id);
				break;
			case "roc":
				re= quato.getRocAnalysis(id);
				break;
			case "rsi":
				re= quato.getRsiAnalysis(id);
				break;
			case "vr":
				re= quato.getVrAnalysis(id);
				break;
			}			
			list.add(re);			
		}
		return list;
	}
}
