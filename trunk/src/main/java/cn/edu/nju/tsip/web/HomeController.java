package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IUserService;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	private IUserService<User> userService;
	
	private Validator validator;
	
	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public IUserService<User> getUserService() {
		return userService;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 */
	@RequestMapping(value="/", method=RequestMethod.GET)
	public String home() {
		logger.info("Welcome home!");
		return "home";
	}
	
	@RequestMapping(value="/create",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> create(@RequestBody User user, HttpServletResponse response){
		
		return Collections.singletonMap("status",true);	
	}
	
}

