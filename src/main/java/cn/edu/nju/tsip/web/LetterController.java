package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.edu.nju.tsip.entity.Letter;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.ILetterService;
import cn.edu.nju.tsip.service.IUserService;

@Controller
public class LetterController {
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private Validator validator;
	
	private ILetterService<Letter> letterService;
	
	private IUserService<User> userService;

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

	public ILetterService<Letter> getLetterService() {
		return letterService;
	}

	@Autowired
	public void setLetterService(ILetterService<Letter> letterService) {
		this.letterService = letterService;
	}
	
	@RequestMapping(value="/client/letter/add",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> addLetter(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client send letter");
		Letter letter = new Letter();
		letter.setContent((String)param.get("content"));
		letter.setCreateDate(new Date());
		letter.setSender(userService.find(User.class, (Integer)session.getAttribute("id")));
		letter.setReceiver(userService.find(User.class,(Integer)param.get("receiverId")));
		letterService.create(letter);
		return Collections.singletonMap("status", "true");
	}
	
	@RequestMapping(value="/client/letter/getone",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getOneLetter(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client send letter");
		List<Letter> letters = letterService.getUnreadedLetters((Integer)param.get("otherId"),(Integer)session.getAttribute("id"));
		List<String> contents = Lists.newArrayList();
		for(Letter letter:letters){
			contents.add("{\"content\":\""+letter.getContent()+"\",\"createDate\":\""+letter.getCreateDate()+"\"}");
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("name", userService.find(User.class, (Integer)param.get("otherId")));
		result.put("messages", contents);
		return result;
	}
	
	@RequestMapping(value="/client/letter/getall",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getallLetter(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client get letter");
		List<Letter> letters = letterService.getUnreadedLetters((Integer)session.getAttribute("id"));
		List<String> contents = Lists.newArrayList();
		for(Letter letter:letters){
			contents.add("{\"senderId\":"+letter.getSender().getId()+",\"name\":\""+letter.getSender().getRealName()+"\",\"content\":\""+letter.getContent()+"\",\"createDate\":\""+letter.getCreateDate()+"\"}");
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("messages", contents);
		return result;
	}
	
	@RequestMapping(value="/client/user/getOnline",method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> getOnlineUser(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client get online user");
		List<User> users = userService.getOnlineUsers();
		List<Map<String, Object>> contents = Lists.newArrayList();
		for(User user:users){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("id", user.getId());
			tempResult.put("name", user.getRealName());
			tempResult.put("role", user.getRoleList().get(0).getName());
			tempResult.put("logingPlace", user.getLoginPlace());
			contents.add(tempResult);
		}
		return Collections.singletonMap("users",contents);
	}

}
