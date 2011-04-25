package cn.edu.nju.tsip.test.util;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Date;
import java.text.SimpleDateFormat;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import cn.edu.nju.tsip.entity.*;
import cn.edu.nju.tsip.service.*;

/**
 * 初始化整个数据库所有必要的数据，并不包含测试数据
 * @author ljj
 *
 */
public class InitDatabase {
	
	IUserService<User> userService;
	IMblogService<MBlog> mblogService;
	ICommentService<Comment> commentService;
	IStudentService<Student> studentService;
	
	public InitDatabase(IUserService<User> userService,IMblogService<MBlog> mblogService,ICommentService<Comment> commentService,IStudentService<Student> studentService){
		this.userService = userService;
		this.mblogService = mblogService;
		this.commentService = commentService;
		this.studentService = studentService;
	}
	
	@SuppressWarnings("unchecked")
	public InitDatabase(){
		String[] contexts = new String[] {"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"};  
		XmlWebApplicationContext context = new XmlWebApplicationContext();  
        context.setConfigLocations(contexts);
        MockServletContext msc = new MockServletContext();  
        context.setServletContext(msc); 
        context.refresh(); 
        msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);  
        userService = (IUserService<User>) context.getBean("userService");
        mblogService = (IMblogService<MBlog>) context.getBean("mblogService");
        commentService = (ICommentService<Comment>) context.getBean("commentService");
        studentService = (IStudentService<Student>) context.getBean("studentService");
	}
	
	public void init() throws Exception{
		initRoleList();
		initStudent();
		initMBlog();
	}

	public static void main(String[] args) throws Exception {
		InitDatabase initDatabase = new InitDatabase();
		initDatabase.init();
	}
	
	/**
	 *  初始化Role :
	 *  student
	 *  teacher
	 *  counsellor
	 *  admin 
	 *  leader
	 */
	public void initRoleList(){
		userService.createRole("student");
		userService.createRole("teacher");
		userService.createRole("counsellor");
		userService.createRole("admin");
		userService.createRole("leader");
	}
	
	/**
	 * 初始化所有学生信息
	 * @throws FileNotFoundException 
	 */
	public void initStudent() throws Exception{
		String path = Thread.currentThread().getContextClassLoader().getResource("student.txt").getPath();
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String s = "09std";
		int i =0 ;
		String line;
		Role role = userService.getRole("student");
		while((line = br.readLine()) != null){
			SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
			Date date = dateformat.parse("1990-07-20");
			int firstAnno = line.indexOf(',');
			int lastAnno = line.lastIndexOf(',');
			String loginName;
			if(firstAnno == lastAnno){
				loginName = s+i;
				i++;
			}else{
				loginName = line.substring(lastAnno+1);
			}
			String realName;
			if(firstAnno == lastAnno){
				realName = line.substring(lastAnno+1);
			}else{
				realName = line.substring(firstAnno+1, lastAnno);
			}
			int stno = Integer.parseInt(line.substring(0, firstAnno));
			addStudent(date, "hobby", loginName, "123", true, realName, "remark", stno, "talent",role);	
		}
	}
	
	public void initMBlog() throws Exception{
		String path = Thread.currentThread().getContextClassLoader().getResource("mblog.txt").getPath();
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		for(int i = 1;i<455;i++){
			User user =userService.find(User.class, i);
			for(int k = 0;k<5;k++){
				addMblog(br.readLine(), user);
			}
		}
	}
	
	private void addStudent(Date date,String hobby,String loginName,String password,boolean sex,String realName,String remarks,int stno,String talent,Role role){
		System.out.println(date+" "+hobby+" "+loginName+" "+password+" "+sex+" " +realName+" "+remarks+" "+stno+" "+talent);
		Student student = new Student();
		student.getRoleList().add(role);
		student.setBirthday(date);
		student.setHobby(hobby);//
		student.setLoginName(loginName);
		student.setLoginPlace(null);
		student.setPassword(password);
		student.setSex(sex);
		student.setRealName(realName);
		student.setRemarks(remarks);
		student.setStno(stno);
		student.setTalent(talent);
		studentService.create(student);
	}
	
	private void addMblog(String content,User publisher){
		MBlog mBlog = new MBlog();
		mBlog.setContent(content);
		mBlog.setCreateDate(new Date());
		mBlog.setPublisher(publisher);
		mblogService.create(mBlog);
		
	}

}
