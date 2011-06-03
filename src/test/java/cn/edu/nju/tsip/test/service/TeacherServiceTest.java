package cn.edu.nju.tsip.test.service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Role;
import cn.edu.nju.tsip.entity.Teacher;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.ITeacherService;
import cn.edu.nju.tsip.service.IUserService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;

@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class TeacherServiceTest {
	
	private ITeacherService<Teacher> teacherService;
	
	private IUserService<User> userService;
	

	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public ITeacherService<Teacher> getTeacherService() {
		return teacherService;
	}

	@Autowired
	public void setTeacherService(ITeacherService<Teacher> teacherService) {
		this.teacherService = teacherService;
	}
	
	@Test
	public void createTest() throws ParseException{
		Teacher teacher = new Teacher();
		SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
		Date date = dateformat.parse("1980-07-20");
		teacher.setBirthday(date);
		teacher.setCreateDate(new Date());
		teacher.setLoginName("eryuding");
		teacher.setPassword("123");
		teacher.setRealName("丁二玉");
		Role role = userService.getRole("teacher");
		teacher.getRoleList().add(role);
		teacher.setSex(true);
		teacher.setTrno(1238987);
		teacherService.create(teacher);
	}
}
