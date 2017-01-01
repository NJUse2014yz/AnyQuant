package controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import common.Ktype;
import service.StockDataService;
import vo.KgraInf;
import vo.RealTimeInf;
import vo.StockListInf;

@Controller
public class StockCompareController {
	
	@Autowired
	private StockDataService stockBl;
	
	@RequestMapping("/compare")
	public ModelAndView compare(String id1,String id2) throws Exception{
		ModelAndView modelAndView=new ModelAndView();
		
		StockListInf inf1=null;
		StockListInf inf2=null;
		if(id1==null||id2==null){
			inf1=stockBl.showSingleStock("sh600000");
			inf2=stockBl.showSingleStock("sh600200");
		}else{
			inf1=stockBl.showSingleStock(id1);
			inf2=stockBl.showSingleStock(id2);
		}
		
		
		
		
		modelAndView.addObject("inf1",inf1);
		modelAndView.addObject("inf2",inf2);
		modelAndView.setViewName("compare/Compare");
		
		return modelAndView;
	}
	
}
