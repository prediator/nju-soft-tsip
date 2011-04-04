package cn.edu.nju.tsip.test.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IUserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"webapp/WEB-INF/spring:root-context.xml"})
public class UserServiceTest {
	
	@Autowired
	private IUserService<User> userService;

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