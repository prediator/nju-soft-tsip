package cn.edu.nju.tsip.web;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;

import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IUserService;

@Controller
public class UserController {
	
	private IUserService<User> userService;
	
	private Validator validator;

	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}
	
	private static final Logger logger = LoggerFactory.getLogger(UserController.class);
	
	@RequestMapping(value="/client/user/login",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> create(@RequestBody Map<String, String> param, HttpServletResponse response,HttpSession session){
		logger.info("client login: "+param.get("loginName"));
		User user = userService.getUser((String) param.get("loginName"),(String) param.get("password"));
		if(user == null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("error", "参数错误");
			return failureMessages;
		}else{
			session.setAttribute("id", user.getId());
			session.setAttribute("place", param.get("loginPlace"));
			Map<String,String> result = Maps.newHashMap();
			result.put("status","true");
			result.put("sessionId", session.getId());
			result.put("role", user.getRoleList().get(0).getName());
			return result;
			
		}
	}
}
