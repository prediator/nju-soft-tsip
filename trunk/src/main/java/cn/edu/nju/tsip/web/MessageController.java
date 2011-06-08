package cn.edu.nju.tsip.web;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.edu.nju.tsip.entity.Message;
import cn.edu.nju.tsip.entity.Message_User;
import cn.edu.nju.tsip.entity.Student;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IMessageService;
import cn.edu.nju.tsip.service.IStudentService;
import cn.edu.nju.tsip.service.IUserService;
import cn.edu.nju.tsip.service.ServiceException;

@Controller
public class MessageController {
	
	private Validator validator;
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private IMessageService<Message> messageService;
	
	private IUserService<User> userService;
	
	private IStudentService<Student> studentService;

	public IStudentService<Student> getStudentService() {
		return studentService;
	}

	@Autowired
	public void setStudentService(IStudentService<Student> studentService) {
		this.studentService = studentService;
	}

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public IMessageService<Message> getMessageService() {
		return messageService;
	}

	@Autowired
	public void setMessageService(IMessageService<Message> messageService) {
		this.messageService = messageService;
	}
	
	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/client/message/create/some",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> addMessage(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client adding message");
		if(session.getAttribute("role").toString().equals("student")){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "权限不足");
			return failureMessages;
		}
		Message message = new Message();
		message.setTitle((String) param.get("title"));
		message.setContent((String) param.get("content"));
		message.setPublisher(userService.find(User.class, (Integer) session.getAttribute("id")));
		List<Map<String, Integer>> userIds = (ArrayList<Map<String,Integer>>) param.get("receivers");
		Set<Message_User> mus = message.getMessage2Users();
		for(Map<String, Integer> userId:userIds){
			Message_User mu = new Message_User();
			mu.setMessage(message);
			mu.setUser(userService.find(User.class,userId.get("id")));
			mus.add(mu);
		}
		messageService.create(message);
		return Collections.singletonMap("status", "true");
	}
	
	@RequestMapping(value="/client/message/create/student",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> addMessage2Stu(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client adding message to student");
		if(session.getAttribute("role").toString().equals("student")){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "权限不足");
			return failureMessages;
		}
		Message message = new Message();
		message.setTitle((String) param.get("title"));
		message.setContent((String) param.get("content"));
		message.setPublisher(userService.find(User.class, (Integer) session.getAttribute("id")));
		List<Student> students = studentService.getAll();
		Set<Message_User> mus = message.getMessage2Users();
		for(Student student:students){
			Message_User mu = new Message_User();
			mu.setMessage(message);
			mu.setUser(student);
			mus.add(mu);
		}
		messageService.create(message);
		return Collections.singletonMap("status", "true");
		/////////////////////////////////problem/////////////////////////
	}
	
	@RequestMapping(value="/client/message/getallunreaded",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getUnreadeMessages(@RequestBody Map<String,Integer> param, HttpServletResponse response,HttpSession session){
		Set<Map<String, Object>> contents = Sets.newHashSet();
		for(Message_User mu:userService.find(User.class, (Integer) session.getAttribute("id")).getUser2Messages()){
			if(!mu.isReaded()){
				Map<String, Object> tempResult = Maps.newHashMap();
				tempResult.put("id", mu.getMessage().getId());
				tempResult.put("title", mu.getMessage().getTitle());
				tempResult.put("publisherId", mu.getUser().getId());
				tempResult.put("publishName", mu.getUser().getRealName());
				contents.add(tempResult);
			}
			
		}
		return Collections.singletonMap("messages",contents);
	}
	
	@RequestMapping(value="/client/message/getMy",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMyMessages(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		List<Message> messages = messageService.getPublishMessages((Integer) session.getAttribute("id"));
		Set<Map<String, Object>> contents = Sets.newHashSet();
		for(Message message:messages){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("id", message.getId());
			tempResult.put("title", message.getTitle());
			contents.add(tempResult);
		}
		return Collections.singletonMap("messages", contents);
	}
	
	@RequestMapping(value="/client/message/detailMy",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMyMessageDetail(@RequestBody Map<String,Integer> param, HttpServletResponse response,HttpSession session){
		Message message = messageService.getPublishMessage((Integer) session.getAttribute("id"),param.get("id"));
		if(message==null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "不存在该相册");
			return failureMessages;
		}else{
			Map<String, Object> result = Maps.newHashMap();
			result.put("title", message.getTitle());
			result.put("content", message.getContent());
			Set<Map<String, Object>> contents = Sets.newHashSet();
			for(Message_User mu:message.getMessage2Users()){
				Map<String, Object> tempresult = Maps.newHashMap();
				tempresult.put("id", mu.getUser().getId());
				tempresult.put("name", mu.getUser().getRealName());
				tempresult.put("readed", mu.isReaded());
				contents.add(tempresult);
			}
			return result;
		}
	}
	
	@RequestMapping(value="/client/message/detail",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMessageDetail(@RequestBody Map<String,Integer> param, HttpServletResponse response,HttpSession session){
		Message_User mu = messageService.getReceiveMessage((Integer) session.getAttribute("id"),param.get("id"));
		Message message  = mu.getMessage();
		if(message==null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "不存在该消息");
			return failureMessages;
		}else{
			Map<String, Object> result = Maps.newHashMap();
			result.put("title", message.getTitle());
			result.put("content", message.getContent());
			mu.setReaded(true);
			messageService.updateMessage_User(mu);
			//评论
			return result;
		}
	}
	
	@RequestMapping(value="/client/message/getall",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMessages(@RequestBody Map<String,Integer> param, HttpServletResponse response,HttpSession session){
		Set<Map<String, Object>> contents = Sets.newHashSet();
		for(Message_User mu:userService.find(User.class, (Integer) session.getAttribute("id")).getUser2Messages()){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("id", mu.getMessage().getId());
			tempResult.put("title", mu.getMessage().getTitle());
			tempResult.put("readed", mu.isReaded());
			tempResult.put("publisherId", mu.getUser().getId());
			tempResult.put("publishName", mu.getUser().getRealName());
			tempResult.put("content", mu.getMessage().getContent());
			contents.add(tempResult);
		}
		return Collections.singletonMap("messages",contents);
	}
	
	@ExceptionHandler(ServiceException.class)
	public @ResponseBody Map<String, String> handleServiceException(ServiceException exception){
		Map<String, String> failureMessages = new HashMap<String, String>();
		failureMessages.put("status", "false");
		exception.printStackTrace();
		failureMessages.put("error", exception.getMessage());
		return failureMessages;
	}

}
