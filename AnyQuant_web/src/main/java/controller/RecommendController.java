package controller;

import java.awt.List;
import java.util.ArrayList;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import Trash.GrailName;
import Trash.Stock;

/***
 * 股票推荐
 * @author 周聪
 * 2016年5月11日
 */
@Controller
public class RecommendController {

	
	@RequestMapping("/recommend")
	public ModelAndView recommend(){
		ModelAndView modelAndView=new ModelAndView();
		
		ArrayList<Stock> list1=getList("可口可乐");
		modelAndView.addObject("list1", list1);
		
		ArrayList<Stock> list2=getList("腾讯");
		modelAndView.addObject("list2",list2);
		
		
		modelAndView.setViewName("main");
		
		return modelAndView;
	}
	
	@RequestMapping("/recommendJSON")
	public @ResponseBody ArrayList<Stock> recommendJSON(Integer modelNumber){
		
		System.out.println("getting data of model "+modelNumber);
		if(modelNumber==1)
			return getList("腾讯");
		else
			return getList("火影忍者");
		
		
	}

	private ArrayList<Stock> getList(String name) {
		Stock model1=new Stock();
		model1.setId(name);
		model1.setGrailName(GrailName.HS);
		ArrayList<Stock> modelList1=new ArrayList<>();
		
		for(int i=0;i<4;i++)
			modelList1.add(model1);
		return modelList1;
	}
	
	
}
