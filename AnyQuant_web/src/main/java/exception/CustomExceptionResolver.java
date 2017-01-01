package exception;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.ModelAndView;

/**
 * 自定义异常处理器
 * 处理controller抛出的异常
 */
public class CustomExceptionResolver implements HandlerExceptionResolver{

	@Override
	public ModelAndView resolveException(HttpServletRequest request, HttpServletResponse response, Object handler,
			Exception ex) {
		
		ModelAndView modelAndView=new ModelAndView();
		
		modelAndView.addObject("errorMessage", ex.getMessage());
		modelAndView.setViewName("exception/error");
		return modelAndView;
	}

}
