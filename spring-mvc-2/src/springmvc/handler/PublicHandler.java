package springmvc.handler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class PublicHandler {

	/**
	 * 1. 在 @ExceptionHandler 方法的入参中可以加入 Exception 类型的参数, 该参数即对应发生的异常对象
	 * 2. @ExceptionHandler 方法的入参中不能传入 Map. 若希望把异常信息传导页面上, 需要使用 ModelAndView 作为返回值
	 * 3. @ExceptionHandler 方法标记的异常有优先级的问题. 
	 * 4. @ControllerAdvice: 如果在当前 Handler 中找不到 @ExceptionHandler 方法来出来当前方法出现的异常, 
	 * 则将去 @ControllerAdvice 标记的类中查找 @ExceptionHandler 标记的方法来处理异常. 
	 */
	@ExceptionHandler({RuntimeException.class})
	public ModelAndView testExceptionHandlerException(Exception ex) {
		
		ModelAndView mv= new ModelAndView("error");
		mv.addObject("exception",ex);
		return mv;
		
	}
	
	@RequestMapping("/testExceptionhandler")
	public String testExceptionhandler(@RequestParam("i") Integer i) {
		System.out.println("i=  " + 10/i);
		
		return "seccess";
	}
	
	@RequestMapping("/testIntercepters")
	public String testIntercepters() {
		
		return "seccess";
	}
}
