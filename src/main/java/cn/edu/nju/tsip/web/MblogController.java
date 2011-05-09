package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
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

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import cn.edu.nju.tsip.entity.Comment;
import cn.edu.nju.tsip.entity.MBlog;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.ICommentService;
import cn.edu.nju.tsip.service.IMblogService;
import cn.edu.nju.tsip.service.IUserService;

@Controller
public class MblogController {
	
	private Validator validator;
	
	private IMblogService<MBlog> mblogService;
	
	private IUserService<User> userService;
	
	private ICommentService<Comment> commentService;
	
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
	
	public ICommentService<Comment> getCommentService() {
		return commentService;
	}

	@Autowired
	public void setCommentService(ICommentService<Comment> commentService) {
		this.commentService = commentService;
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
		Set<ConstraintViolation<MBlog>> failures = validator.validate(mBlog);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessages(failures);
		} else {
			mblogService.create(mBlog);
			return Collections.singletonMap("status", "true");
		}
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
	
	/**
	 * status:"true",
 		 id:1324, 
 		 createDate:"yyyy-mm-dd HH:mm:ss",         //原创状态
 		 publisher:{name:"ljj",
 		            id:"12323"},
 		 content:"a comment to the mblog", 
 		 flwMBlog:{id:1323,
 		 		   createDate:"yyyy-mm-dd HH:mm:ss",         //转发的微博，并不会显示原来的微博的评论
 		 		   publisher:{name:"ljj",
 		            		  id:"12323"},
 		 		   content:"what a good mblog"},
 		  comments:[{id:2326,
 		            content:"what a good comment!",    //没有子评论的评论
 		            createDate:"yyyy-mm-dd HH:mm:ss",
 		            author:{name:"lhh",
 		                    id:1325}},
 		            {id:2327,
 		             content:"what a great comment!",
 		             createDate:"yyyy-mm-dd HH:mm:ss",
 		             author:{name:"jzh",
 		                    id:1326}},
 		            {id:2328,
 		             content:"what a great comment!",  //有子评论的评论
 		             createDate:"yyyy-mm-dd HH:mm:ss",
 		             author:{name:"jzh",
 		                    id:1326},
 		             cmntChilds:[{id:2329,
 		                           content:"a comment for the comment!",
 		                           createDate:"yyyy-mm-dd HH:mm:ss",
 		                           author:{name:"jzh",
 		                                   id:1326}},
 		                           {id:2330,
 		                           content:"another comment for the comment",
 		                           createDate:"yyyy-mm-dd HH:mm:ss",
 		                           author:{name:"jzh",
 		                                   id:1326}}
 		                           //...more comments
 		                           ]          
	 * @param param
	 * @param response
	 * @param session
	 * @return
	 */
	@RequestMapping(value="/client/mblog/getMblogDatail",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMBlogDetail(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client get MBlog");
		MBlog mblog = mblogService.find(MBlog.class, (Integer)param.get("id"));
		Map<String,Object> result = Maps.newHashMap();
		if(mblog == null) {
			result.put("status", "false");
			result.put("error", "参数错误");
		}
		//************************problem2
		result.put("status", "true");
		result.put("id", mblog.getId());
		result.put("createDate", mblog.getCreateDate());
		result.put("content", mblog.getContent());
		Map<String,Object> publisher = Maps.newHashMap();
		publisher.put("id", mblog.getPublisher().getId());
		publisher.put("name", mblog.getPublisher().getRealName());
		result.put("publisher", publisher);
		MBlog _mblog = mblog.getFlwBlog();
		Map<String,Object> _result = result;
		for(;_mblog!=null;_mblog = _mblog.getFlwBlog()){
			Map<String,Object> subResult = Maps.newHashMap();
			_result.put("flwMBlog", subResult);
			_result = subResult;
			_result.put("status", "true");
			_result.put("id", mblog.getId());
			_result.put("createDate", mblog.getCreateDate());
			_result.put("content", mblog.getContent());
			publisher = Maps.newHashMap();
			publisher.put("id", mblog.getPublisher().getId());
			publisher.put("name", mblog.getPublisher().getRealName());
			_result.put("publisher", publisher);
		}
		//add comment
		result.put("comments",commentsMessage(mblog.getComments()));
		return result;
	}
	
	private List<Map<String, Object>> commentsMessage(List<Comment> comments){
		List<Map<String, Object>> result = Lists.newArrayList();
		for(Comment comment:comments){
			Map<String, Object> cMap = Maps.newHashMap();
			cMap.put("id", comment.getId());
			cMap.put("content", comment.getContent());
			cMap.put("createDate", comment.getCreateDate());
			Map<String, Object> author = Maps.newHashMap();
			author.put("id", comment.getAuthor().getId());
			author.put("name", comment.getAuthor().getRealName());
			cMap.put("author", author);
			result.add(cMap);
		}
		return result;
	}
	
	@RequestMapping(value="/client/mblog/getAll",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> getMBlogs(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response,HttpSession session){
		logger.info("client get MBlog");
		List<Map<String, Object>> rList = Lists.newArrayList();
		List<MBlog> mBlogs = mblogService.getAllMBlogs();
		for(MBlog mblog:mBlogs){
			Map<String, Object> tempResult = Maps.newHashMap();
			tempResult.put("status", "true");
			tempResult.put("id", mblog.getId());
			tempResult.put("createDate", mblog.getCreateDate());
			tempResult.put("content", mblog.getContent());
			Map<String,Object> publisher = Maps.newHashMap();
			publisher.put("id", mblog.getPublisher().getId());
			publisher.put("name", mblog.getPublisher().getRealName());
			tempResult.put("publisher", publisher);
			MBlog _mblog = mblog.getFlwBlog();
			Map<String,Object> _result = tempResult;
			for(;_mblog!=null;_mblog = _mblog.getFlwBlog()){
				Map<String,Object> subResult = Maps.newHashMap();
				_result.put("flwMBlog", subResult);
				_result = subResult;
				_result.put("status", "true");
				_result.put("id", mblog.getId());
				_result.put("createDate", mblog.getCreateDate());
				_result.put("content", mblog.getContent());
				publisher = Maps.newHashMap();
				publisher.put("id", mblog.getPublisher().getId());
				publisher.put("name", mblog.getPublisher().getRealName());
				_result.put("publisher", publisher);
			}
			//add comment
			tempResult.put("comments",commentsMessage(mblog.getComments()));
			rList.add(tempResult);
		}
		
		return Collections.singletonMap("mblogs",rList);
	}
	
	@RequestMapping(value="/client/mblog/addcomment",method=RequestMethod.POST)
	public @ResponseBody Map<String, ? extends Object> addcomment(@RequestBody Map<String, ? extends Object> param, HttpServletResponse response, HttpServletRequest request,HttpSession session){
		Comment comment = new Comment();
		comment.setAuthor(userService.find(User.class, (Integer)session.getAttribute("id")));
		comment.setAuthorIP(request.getRemoteAddr());
		comment.setContent((String)param.get("content"));
		comment.setCreateDate(new Date());
		MBlog mBlog = mblogService.find(MBlog.class, (Integer)param.get("id"));
		if(mBlog!=null){
			mBlog.getComments().add(comment);
			mblogService.update(mBlog);
			return Collections.singletonMap("status","true");
		}else{
			return Collections.singletonMap("status","false");
		}
		
		//------------------------problem---------------------------
		//update strategy
		
		
	}
	
	private Map<String, String> validationMessages(Set<ConstraintViolation<MBlog>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<MBlog> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}

}
