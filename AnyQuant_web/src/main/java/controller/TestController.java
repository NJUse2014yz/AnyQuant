package controller;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import tool.CookieHelper;

/**
 * 测试界面
 * @author 周聪
 * 2016年5月12日
 */
@Controller
public class TestController {

	@RequestMapping("/test")
	public ModelAndView test(HttpServletRequest request,HttpServletResponse response){
		ModelAndView modelAndView=new ModelAndView();
		modelAndView.setViewName("data/StockInf");
		modelAndView.addObject(CookieHelper.IS_LIKE,"false");
		CookieHelper.addCookie(CookieHelper.IS_LIKE, "false", response, request);
		
		return modelAndView;
	}
	
	/**
	 * 
	 * 2016年6月1日
	 */
	@RequestMapping("/error")
	public ModelAndView error(){
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.setViewName("exception/error");
		
		modelAndView.addObject("errorMessage","系统错误");
		return modelAndView;
	}
	
}
