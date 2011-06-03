package cn.edu.nju.tsip.web;

import javax.validation.Validator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import cn.edu.nju.tsip.entity.Student;
import cn.edu.nju.tsip.service.IStudentService;

@Controller
public class StudentController {
	
	private Validator validator;
	
	private static final Logger logger = LoggerFactory.getLogger(MblogController.class);
	
	private IStudentService<Student> studentService;

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public IStudentService<Student> getStudentService() {
		return studentService;
	}

	@Autowired
	public void setStudentService(IStudentService<Student> studentService) {
		this.studentService = studentService;
	}

}
