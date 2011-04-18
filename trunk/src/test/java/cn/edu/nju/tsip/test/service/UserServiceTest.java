package cn.edu.nju.tsip.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IUserService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;

@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class UserServiceTest {
	
	private IUserService<User> userService;

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public IUserService<User> getUserService() {
		return userService;
	}
	
	@Test
	public void nothingTest(){
		
	}

}