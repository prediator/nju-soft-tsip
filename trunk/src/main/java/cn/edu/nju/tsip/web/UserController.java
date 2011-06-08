package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
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

import com.google.common.collect.Lists;
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
	public @ResponseBody Map<String, String> login(@RequestBody Map<String, String> param, HttpServletResponse response,HttpSession session){
		logger.info("client login: "+param.get("loginName")+", sessionid:"+session.getId());
		User user = userService.getUser(param.get("loginName"), param.get("password"));
		if(user == null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "参数错误");
			return failureMessages;
		}else{
			session.setAttribute("id", user.getId());
			session.setAttribute("role", user.getRoleList().get(0).getName());
			user.setOnline(true);
			user.setLoginPlace(param.get("loginPlace"));
			userService.update(user);
			Map<String,String> result = Maps.newHashMap();
			result.put("status","true");
			result.put("sessionId", session.getId());
			result.put("realName", user.getRealName());
			result.put("role", user.getRoleList().get(0).getName());
			return result;
			
		}
	}
	
	@RequestMapping(value="/client/user/getAll",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getUsers(@RequestBody Map<String, String> param, HttpServletResponse response,HttpSession session){
		logger.info("client get all user ");
		Map<String,Object> result = Maps.newHashMap();
		List<Map<String, Object>> studentList = Lists.newArrayList();
		List<Map<String, Object>> teacherList = Lists.newArrayList();
		List<Map<String, Object>> counsellorList = Lists.newArrayList();
		List<Map<String, Object>> adminList = Lists.newArrayList();
		List<Map<String, Object>> leaderList = Lists.newArrayList();
		for(User user:userService.getAllUsers()){
			char role = user.getRoleList().get(0).getName().charAt(0);
			Map<String, Object> map = Maps.newHashMap();
			map.put("id", user.getId());
			map.put("realName", user.getRealName());
			map.put("isOnline", user.isOnline());
			switch (role) {
			case 's':
				studentList.add(map);
				break;
			
			case 't':
				teacherList.add(map);
				break;
			case 'c':
				counsellorList.add(map);
				break;
			case 'a':
				adminList.add(map);
				break;
			case 'l':
				leaderList.add(map);
				break;
			}
		}
		result.put("studentList", studentList);
		result.put("teacherList",teacherList);
		result.put("counsellorList", counsellorList);
		result.put("adminList", adminList);
		result.put("leaderList", leaderList);
		return result;
	}
	
	@RequestMapping(value="/client/user/exit",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> logout(@RequestBody Map<String, String> param, HttpServletResponse response,HttpSession session){
		User user = userService.find(User.class, (Integer)session.getAttribute("id"));
		if(user==null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "参数错误");
			return failureMessages;
		}else{
			user.setOnline(false);//更新数据库
			userService.update(user);
			session.invalidate();//session销毁
			return Collections.singletonMap("status", "true");
		}
		
	}
	
	@RequestMapping(value="/client/user/changepassword",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> changePassword(@RequestBody Map<String, String> param, HttpServletResponse response,HttpSession session){
		User user = userService.find(User.class, (Integer)session.getAttribute("id"));
		if(user.getPassword().equals(param.get("oldPassword"))){
			user.setPassword(param.get("newPassword"));
			return Collections.singletonMap("status", "true");
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "参数错误");
			return failureMessages;
		}
	}
}
