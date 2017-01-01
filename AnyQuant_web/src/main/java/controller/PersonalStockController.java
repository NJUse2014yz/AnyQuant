package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * 我的股票页面
 * @author 周聪
 * 2016年5月25日
 */
@Controller
public class PersonalStockController {

	@RequestMapping("/personalStock")
	public ModelAndView personalStock(){
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("user/PersonalStock");
		return modelAndView;
	}
	
}
