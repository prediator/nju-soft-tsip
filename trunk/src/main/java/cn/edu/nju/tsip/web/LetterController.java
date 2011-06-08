package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.Date;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;

import cn.edu.nju.tsip.entity.DisuGroup;
import cn.edu.nju.tsip.entity.GroupLetter;
import cn.edu.nju.tsip.entity.Letter;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IDisugroupService;
import cn.edu.nju.tsip.service.IGroupletterService;
import cn.edu.nju.tsip.service.ILetterService;
import cn.edu.nju.tsip.service.IUserService;

@Controller
public class LetterController {
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private Validator validator;
	
	private ILetterService<Letter> letterService;
	
	private IUserService<User> userService;
	
	private IDisugroupService<DisuGroup> disugroupService;
	
	private IGroupletterService<GroupLetter> groupletterService;

	public IGroupletterService<GroupLetter> getGroupletterService() {
		return groupletterService;
	}

	@Autowired
	public void setGroupletterService(
			IGroupletterService<GroupLetter> groupletterService) {
		this.groupletterService = groupletterService;
	}

	public IDisugroupService<DisuGroup> getDisugroupService() {
		return disugroupService;
	}

	@Autowired
	public void setDisugroupService(IDisugroupService<DisuGroup> disugroupService) {
		this.disugroupService = disugroupService;
	}

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
	public @ResponseBody Map<String, String> addLetter(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client send letter");
		Letter letter = new Letter();
		letter.setContent((String)param.get("content"));
		letter.setCreateDate(new Date());
		letter.setSender(userService.find(User.class, (Integer)session.getAttribute("id")));
		letter.setReceiver(userService.find(User.class,(Integer)param.get("otherId")));
		letterService.create(letter);
		return Collections.singletonMap("status", "true");
	}
	
	@RequestMapping(value="/client/letter/getone",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getOneLetter(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client get letter");
		List<Letter> letters = letterService.getUnreadedLetters((Integer)param.get("otherId"),(Integer)session.getAttribute("id"));
		List<Map<String, Object>> contents = Lists.newArrayList();
		for(Letter letter:letters){
			letter.setReaded(true);
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("content",letter.getContent());
			tempResult.put("createDate",letter.getCreateDate().toString());
			contents.add(tempResult);
			letterService.update(letter);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("name", userService.find(User.class, (Integer)param.get("otherId")));
		result.put("messages", contents);
		return result;
	}
	
	@RequestMapping(value="/client/letter/getall",method=RequestMethod.POST)
	public @ResponseBody Map<String, Object> getAllLetter(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client get letter");
		List<Letter> letters = letterService.getUnreadedLetters((Integer)session.getAttribute("id"));
		List<Map<String, Object>> contents = Lists.newArrayList();
		for(Letter letter:letters){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("senderId",letter.getSender().getId());
			tempResult.put("name",letter.getSender().getRealName());
			tempResult.put("content",letter.getContent());
			tempResult.put("createDate",letter.getCreateDate().toString());
			contents.add(tempResult);
			letter.setReaded(true);
			letterService.update(letter);
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
	
	@RequestMapping(value="/client/leter/getlatest",method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> getLatestRecord(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		List<Letter> letters = letterService.getLatestLetters((Integer)session.getAttribute("id"), (Integer)param.get("otherId"),(Integer) param.get("start"),(Integer) param.get("end"));
		List<Map<String, Object>> contents = Lists.newArrayList();
		for(int i = letters.size()-1;i>=0;i--){
			Letter letter = letters.get(i);
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("content",letter.getContent());
			tempResult.put("createDate",letter.getCreateDate().toString());
			tempResult.put("speakerId",letter.getSender().getId());
			tempResult.put("receiverId",letter.getReceiver().getId());
			contents.add(tempResult);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("name", userService.find(User.class, (Integer)param.get("otherId")).getRealName());
		result.put("contents", contents);
		return result;	
	}
	
	@RequestMapping(value="/client/disugroup/create",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> creatediscGroup(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client create disugrop");
		if(disugroupService.find((String) param.get("name"))==null){
			DisuGroup disuGroup = new DisuGroup();
			disuGroup.setName((String) param.get("name"));
			User user = userService.find(User.class, (Integer) session.getAttribute("id"));
			disuGroup.setAuthor(user);
			disuGroup.getUsers().add(user);
			disugroupService.create(disuGroup);
			return Collections.singletonMap("status","true");
		}else{
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "参数错误");
			return failureMessages;
		}
	}
	
	@RequestMapping(value="/client/disugroup/adduser",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> addUser2discGroup(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client add user to group ");
		DisuGroup disuGroup = disugroupService.find((String) param.get("name"));
		if(disuGroup==null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "参数错误");
			return failureMessages;
		}else{
			disuGroup.getUsers().add(userService.find(User.class,(Integer) param.get("userId")));
			disugroupService.update(disuGroup);
			return Collections.singletonMap("status","true");
		}
	}
	
	@RequestMapping(value="/client/disugroup/send",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> send2discGroup(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client add message to group ");
		DisuGroup disuGroup = disugroupService.find((String) param.get("groupName"));
		if(disuGroup==null){
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "参数错误");
			return failureMessages;
		}else{
			String content = (String) param.get("content");
			User sender = userService.find(User.class, (Integer) session.getAttribute("id"));
			int id = (Integer) session.getAttribute("id");
			for(User user:disuGroup.getUsers()){
				if(user.getId()==id){
					continue;
				}
				GroupLetter groupLetter = new GroupLetter();
				groupLetter.setContent(content);
				groupLetter.setCreateDate(new Date());
				groupLetter.setDisuGroup(disuGroup);
				groupLetter.setReceiver(user);
				groupLetter.setSender(sender);
				groupletterService.create(groupLetter);
			}
			return Collections.singletonMap("status","true");
		}
	}
	
	@RequestMapping(value="/client/disugroup/receive",method=RequestMethod.POST)
	public @ResponseBody Map<String,Object> receivefromdiscGroup(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client get group letter");
		List<GroupLetter> letters = groupletterService.getUnreadedLetters((Integer)session.getAttribute("id"));
		List<Map<String, Object>> contents = Lists.newArrayList();
		for(GroupLetter letter:letters){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("groupId", letter.getDisuGroup().getId());
			tempResult.put("groupName",letter.getDisuGroup().getName());
			tempResult.put("senderId",letter.getSender().getId());
			tempResult.put("senderName",letter.getSender().getRealName());
			tempResult.put("content",letter.getContent());
			tempResult.put("createDate",letter.getCreateDate().toString());
			contents.add(tempResult);
			letter.setReaded(true);
			groupletterService.update(letter);
		}
		Map<String, Object> result = Maps.newHashMap();
		result.put("messages", contents);
		return result;
	}
}
