package springmvc.HandleException;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class HandleException {
	//在这里的处理异常是作用于整个程序
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView testExceptionHandlerException(Exception ex) {
		
		ModelAndView mv= new ModelAndView("error");
		mv.addObject("exception",ex);
		return mv;		
	}
}
