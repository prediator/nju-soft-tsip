package cn.edu.nju.tsip.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.MBlog;
import cn.edu.nju.tsip.entity.Student;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IMblogService;
import cn.edu.nju.tsip.service.IStudentService;
import cn.edu.nju.tsip.service.IUserService;
import cn.edu.nju.tsip.test.util.InitDatabase;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;

@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class StudentServiceTest {
	
	private IStudentService<Student> studentService;

	public IStudentService<Student> getStudentService() {
		return studentService;
	}

	@Autowired
	public void setStudentService(IStudentService<Student> studentService) {
		this.studentService = studentService;
	}
	
	private IUserService<User> userService;

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public IUserService<User> getUserService() {
		return userService;
	}
	
	private IMblogService<MBlog> mblogService;
	
	@Test
	public void iniTest() throws Exception{
		InitDatabase initDatabase = new InitDatabase(userService,mblogService,null,studentService,null);
		initDatabase.init();
	}

	@Autowired
	public void setMblogService(IMblogService<MBlog> mblogService) {
		this.mblogService = mblogService;
	}

	public IMblogService<MBlog> getMblogService() {
		return mblogService;
	}

}
