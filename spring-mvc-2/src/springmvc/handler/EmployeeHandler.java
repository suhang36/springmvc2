package springmvc.handler;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.apache.commons.io.filefilter.FalseFileFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.sun.net.httpserver.HttpHandler;

import springmvc.dao.DepartmentDao;
import springmvc.dao.EmployeeDao;
import springmvc.entities.Employee;

@Controller
public class EmployeeHandler {
	
	@Autowired
	private DepartmentDao departmentDao;
	@Autowired
	private EmployeeDao employeeDao;
	
	
	@RequestMapping("/testFileUpload")
	public String testFileUpload(@RequestParam(value="desc",required=false) String desc,
			@RequestParam(value="file",required=false) MultipartFile file) throws IOException {
		System.out.println("desc:" + desc);
		System.out.println("OriginalFilename" + file.getOriginalFilename());
		System.out.println("InputStream"+file.getInputStream());
		return "success";
	}
	
	@RequestMapping("/testResponseEnity")
	public ResponseEntity<byte[]> testResponseEnity(HttpSession session) throws IOException{
		byte [] body = null;
		ServletContext context = session.getServletContext();
		InputStream in = context.getResourceAsStream("/abc.txt");
		body = new byte[in.available()];
		in.read(body);
		
		HttpHeaders headers = new HttpHeaders();
		
		headers.add("Content-Dispostion","attachment;filename=abc.txt");
		
		HttpStatus statusCode = HttpStatus.OK;
		
		ResponseEntity<byte[]> response =
				new ResponseEntity<byte[]>(body, headers, statusCode);
		
		
		
		return response;
	}
	
	@ResponseBody
	@RequestMapping("/testJson")
	public Collection<Employee> testjson(){
		
		return employeeDao.getAll();
	}
	
//规定那个属性不添加（bander初始化时进行）
//	@InitBinder
//	public void initBander(WebDataBinder binder) {
//		binder.setDisallowedFields("lastName");
//	}
	
	@ModelAttribute
	public void getemp(@RequestParam(value="id",required=false) Integer id,
			Map<String, Object> map) {
		if(id != null) {
			map.put("employee", employeeDao.get(id));
		}
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.PUT)
	public String put(Employee employee) {
		employeeDao.save(employee);
		return "redirect:/emps";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.GET)
	public String eidt(@PathVariable Integer id,Map<String,Object> map) {
		map.put("department", departmentDao.getDepartments());
		map.put("employee", employeeDao.get(id));
		
		return "input";
	}
	
	@RequestMapping(value="/emp/{id}",method=RequestMethod.DELETE)
	public String delete(@PathVariable Integer id) {
		employeeDao.delete(id);
		
		return "redirect:/emps";
	}
	//注意BindingResult必须和校验的bean挨着，已经完成对tomcat的支持，不用修改tomcat的el.jar包
	@RequestMapping(value="/emp",method=RequestMethod.POST)
	public String SavedRequest(@Valid Employee employee,BindingResult result,Map<String,Object> map) {
        if (result.getErrorCount()>0){
        	for(FieldError error:result.getFieldErrors()) {
        		System.out.println(error.getField()+":"+error.getDefaultMessage());
        	}
        	map.put("department", departmentDao.getDepartments());
        	return "input";
        }
		employeeDao.save(employee);
		
		return "redirect:/emps";
	}
	
	@RequestMapping("/emps")
	public String getAll(Map<String,Object> map) {
		System.out.println(employeeDao.getAll());
		map.put("employees", employeeDao.getAll());
		return "list";
	}
	
	@RequestMapping(value="/emp",method=RequestMethod.GET)
	public String input(Map<String, Object> map) {
		map.put("department", departmentDao.getDepartments());
		map.put("employee", new Employee());
		return "input";
	}
}
