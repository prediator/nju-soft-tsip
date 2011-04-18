package cn.edu.nju.tsip.web;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IMblogService;

@Controller
public class MblogController {
	
	private Validator validator;
	
	private IMblogService<User> mblogService;
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public IMblogService<User> getMblogService() {
		return mblogService;
	}

	@Autowired
	public void setMblogService(IMblogService<User> mblogService) {
		this.mblogService = mblogService;
	}

}
