package cn.edu.nju.tsip.test.service;

import java.util.List;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Message;
import cn.edu.nju.tsip.entity.Message_User;
import cn.edu.nju.tsip.entity.Student;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IMessageService;
import cn.edu.nju.tsip.service.IStudentService;
import cn.edu.nju.tsip.service.IUserService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;

@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class MessageServiceTest {
	
	private IMessageService<Message> messageService;
	
	private IStudentService<Student> studentService;
	
	private IUserService<User> userService;

	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public IStudentService<Student> getStudentService() {
		return studentService;
	}

	@Autowired
	public void setStudentService(IStudentService<Student> studentService) {
		this.studentService = studentService;
	}

	public IMessageService<Message> getMessageService() {
		return messageService;
	}

	@Autowired
	public void setMessageService(IMessageService<Message> messageService) {
		this.messageService = messageService;
	}
	
	@Test
	public void createTest(){
		/*Message message = new Message();
		message.setTitle("Test3");
		message.setContent("testing testing");
		message.setPublisher(userService.find(User.class, 1));
		List<Student> students = studentService.getAll();
		Set<Message_User> mus = message.getMessage2Users();
		for(Student student:students){
			Message_User mu = new Message_User();
			mu.setMessage(message);
			mu.setUser(student);
			mus.add(mu);
		}
		messageService.create(message);*/
		
		/*Message message = messageService.get(1, 11);
		System.out.println(message.getTitle());
		for(Message_User mu:message.getMessage2Users()){
			System.out.println(mu.getUser().getRealName());
		}*/
		for(Message_User mu:userService.find(User.class, 1).getUser2Messages()){
			System.out.println(mu.getMessage().getTitle());
		}
	}
	
	

}
