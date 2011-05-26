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
	ILetterService<Letter> letterService;
	
	public InitDatabase(IUserService<User> userService,IMblogService<MBlog> mblogService,ICommentService<Comment> commentService,IStudentService<Student> studentService,ILetterService<Letter> letterService){
		this.userService = userService;
		this.mblogService = mblogService;
		this.commentService = commentService;
		this.studentService = studentService;
		this.letterService = letterService;
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
        letterService = (ILetterService<Letter>) context.getBean("letterService");
	}
	
	public void init() throws Exception{
		initRoleList();
		initStudent();
		initMBlog();
		initLetter();
	}

	private void initLetter() throws Exception{
		String path = Thread.currentThread().getContextClassLoader().getResource("letter.txt").getPath();
		File file = new File(path);
		FileReader fr = new FileReader(file);
		BufferedReader br = new BufferedReader(fr);
		String line;
		Date date = new Date();
		for(int i =1;i<=22;i++){
			for(int k =i+1;k<=22;k++){
				int counter = 0;
				User user1 = userService.find(User.class, i);
				User user2 = userService.find(User.class,k);
				while(((line = br.readLine()) != null) && (counter < 60)){
					if(line.isEmpty())
						continue;
					Letter letter = new Letter();
					letter.setContent(line);
					letter.setReaded(true);
					letter.setCreateDate(new Date(date.getTime()+counter*1000*10));
					if(counter%2==0){
						letter.setSender(user1);
						letter.setReceiver(user2);
					}else{
						letter.setSender(user2);
						letter.setReceiver(user1);
					}
					try{
						letterService.create(letter);
					}catch (Exception e) {
						counter--;
					}finally{
						
					}
					counter++;
				}
			}
		}
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
