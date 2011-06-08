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

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.edu.nju.tsip.entity.BBS;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IBBSService;
import cn.edu.nju.tsip.service.IUserService;

@Controller
public class BBSController {
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private Validator validator;
	
	private IUserService<User> userService;
	
	private IBBSService<BBS> bbsService;

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public IBBSService<BBS> getBbsService() {
		return bbsService;
	}

	@Autowired
	public void setBbsService(IBBSService<BBS> bbsService) {
		this.bbsService = bbsService;
	}
	
	@RequestMapping(value="/client/BBS/create",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> createBBS(@RequestBody Map<String,String> param, HttpServletResponse response,HttpSession session){
		logger.info("client create BBS");
		BBS bbs = new BBS();
		bbs.setContent(param.get("content"));
		bbs.setCreateDate(new Date());
		bbs.setPublisher(userService.find(User.class, (Integer) session.getAttribute("id")));
		bbs.setReadTimes(0);
		bbs.setTitle(param.get("title"));
		bbsService.create(bbs);
		return Collections.singletonMap("status","true");
	}
	
	@RequestMapping(value="/client/BBS/change",method=RequestMethod.POST)
	public @ResponseBody Map<String,String> changeBBS(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client change BBS");
		BBS bbs = bbsService.get((Integer) param.get("id"),(Integer)session.getAttribute("id"));
		if(param.get("title")!=null){
			bbs.setTitle((String) param.get("title"));
		}
		if(param.get("content")!=null){
			bbs.setContent((String) param.get("content"));
		}
		bbsService.update(bbs);
		return Collections.singletonMap("status","true");
	}
	
	@RequestMapping(value="/client/BBS/get",method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> getBBSs(@RequestBody Map<String,Object> param, HttpServletResponse response,HttpSession session){
		Set<Map<String, Object>> result = Sets.newHashSet();
		for(BBS bbs : bbsService.getBBSs()){
			Map<String, Object> temp = Maps.newHashMap();
			temp.put("id", bbs.getId());
			temp.put("title", bbs.getTitle());
			temp.put("publisherName", bbs.getPublisher().getRealName());
			temp.put("publisherId", bbs.getPublisher().getId());
			temp.put("creatDate", bbs.getCreateDate());
			result.add(temp);
		}
		return Collections.singletonMap("BBSs",result);
	}
	
	@RequestMapping(value="/client/BBS/detail",method=RequestMethod.POST)
	public @ResponseBody Map<String,? extends Object> BBSDetail(@RequestBody Map<String,Integer> param, HttpServletResponse response,HttpSession session){
		BBS bbs = bbsService.find(BBS.class, param.get("id"));
		if(bbs==null){
			Map<String, String> failureMessages = new HashMap<String, String>();
			failureMessages.put("status", "false");
			failureMessages.put("error", "消息不存在");
			return failureMessages;
		}else{
			Map<String, Object> result = Maps.newHashMap();
			result.put("title", bbs.getTitle());
			result.put("content", bbs.getContent());
			result.put("publisherName", bbs.getPublisher().getRealName());
			result.put("publisherId", bbs.getPublisher().getId());
			result.put("readTimes", bbs.getReadTimes());
			bbs.setReadTimes(bbs.getReadTimes()+1);
			bbsService.update(bbs);
			return result;
		}
	}

}
