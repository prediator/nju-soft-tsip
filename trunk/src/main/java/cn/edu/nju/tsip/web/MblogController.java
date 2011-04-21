package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
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

import cn.edu.nju.tsip.entity.MBlog;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IMblogService;
import cn.edu.nju.tsip.service.IUserService;

@Controller
public class MblogController {
	
	private Validator validator;
	
	private IMblogService<MBlog> mblogService;
	
	private IUserService<User> userService;
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public IMblogService<MBlog> getMblogService() {
		return mblogService;
	}

	@Autowired
	public void setMblogService(IMblogService<MBlog> mblogService) {
		this.mblogService = mblogService;
	}
	
	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}
	
	@RequestMapping(value="/client/mblog/create",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> addMBlog(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client adding MBlog");
		MBlog mBlog = new MBlog();
		mBlog.setContent((String) param.get("content"));
		if(param.get("flwMBlogId") != null){
			mBlog.setFlwBlog(mblogService.find(MBlog.class,(Integer)param.get("flwMBlogId")));
		}
		mBlog.setPublisher(userService.find(User.class, (Integer)session.getAttribute("id")));
		mblogService.create(mBlog);
		return Collections.singletonMap("status", "true");
		
		/*Set<ConstraintViolation<MBlog>> failures = validator.validate(mBlog);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessages(failures);
		} else {
			mblogService.create(mBlog);
			return Collections.singletonMap("status", "true");
		}*/
	}
	
	@RequestMapping(value="/client/mblog/delete",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> deleteMBlog(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client delete MBlog");
		if(mblogService.delete((Integer)param.get("id"), (Integer)session.getAttribute("id")) ){
			return Collections.singletonMap("status", "true");
		}else{
			return Collections.singletonMap("status", "false");
		}
	}
	
	@RequestMapping(value="/client/mblog/getAll",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMBlogs(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		Map<String, Object> result = Maps.newHashMap();
		
		return result;
	}
	
	private Map<String, String> validationMessages(Set<ConstraintViolation<MBlog>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<MBlog> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}

}
