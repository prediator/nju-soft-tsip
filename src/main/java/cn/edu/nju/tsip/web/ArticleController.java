package cn.edu.nju.tsip.web;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nju.tsip.entity.Article;
import cn.edu.nju.tsip.entity.Category;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IArticleService;
import cn.edu.nju.tsip.service.ICategoryService;
import cn.edu.nju.tsip.service.IUserService;
import cn.edu.nju.tsip.service.ServiceException;

@Controller
public class ArticleController {
	
	private Validator validator;
	
	private IArticleService<Article> articleService;
	
	private ICategoryService<Category> categoryService;
	
	private IUserService<User> userService;

	public IUserService<User> getUserService() {
		return userService;
	}

	@Autowired
	public void setUserService(IUserService<User> userService) {
		this.userService = userService;
	}

	public Validator getValidator() {
		return validator;
	}

	@Autowired
	public void setValidator(Validator validator) {
		this.validator = validator;
	}

	public IArticleService<Article> getArticleService() {
		return articleService;
	}

	public ICategoryService<Category> getCategoryService() {
		return categoryService;
	}

	@Autowired
	public void setCategoryService(ICategoryService<Category> categoryService) {
		this.categoryService = categoryService;
	}

	@Autowired
	public void setArticleService(IArticleService<Article> articleService) {
		this.articleService = articleService;
	}
	
	@RequestMapping(value="/client/article/add",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> addArticle(@RequestBody Map<String, Object> param, HttpServletResponse response,HttpSession session){
		Article article = new Article();
		article.setVisible((Boolean) param.get("visible"));
		article.setContent((String) param.get("content"));
		article.setTitle((String) param.get("title"));
		article.setCategory(categoryService.getCategory((String) param.get("category_name"), (Integer)session.getAttribute("id")));
		if(param.containsKey("shareArticleId")){
			article.setShareArticle(articleService.find(Article.class,(Integer) param.get("shareArticleId")));
		}
		Set<ConstraintViolation<Article>> failures = validator.validate(article);
		if (!failures.isEmpty()) {
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return validationMessages(failures);
		} else {
			return Collections.singletonMap("status", "true");
		}
	}
	
	@RequestMapping(value="/client/category/add",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> addCategory(@RequestBody Map<String, Object> param, HttpServletResponse response,HttpSession session){
		Category category  = new Category();
		category.setName((String) param.get("name"));
		category.setOwner(userService.find(User.class, (Integer)session.getAttribute("id")));
		categoryService.create(category);
		return Collections.singletonMap("status", "true");
	}
	
	@RequestMapping(value="/client/category/delete",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> deleteCategory(@RequestBody Map<String, Object> param, HttpServletResponse response,HttpSession session){
		Category category = categoryService.getCategory((String)param.get("name"), (Integer)session.getAttribute("id"));
		if(category!=null){
			categoryService.delete(category);
			return Collections.singletonMap("status", "true");
		}else{
			return  Collections.singletonMap("status", "false");
		}
	}
	
	@RequestMapping(value="/client/article/delete",method=RequestMethod.POST)
	public @ResponseBody Map<String, String> deleteArticle(@RequestBody Map<String, Object> param, HttpServletResponse response,HttpSession session){
		Article article = articleService.find(Article.class, (Integer)param.get("id"));
		if(article.getPublisher().getId().intValue() == (Integer)session.getAttribute("id")){
			articleService.delete(article);
			return Collections.singletonMap("status", "true");
		}else{
			return  Collections.singletonMap("status", "false");
		}
	}
	
	private Map<String, String> validationMessages(Set<ConstraintViolation<Article>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<Article> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}
	
	@ExceptionHandler(ServiceException.class)
	public @ResponseBody Map<String, String> handleServiceException(ServiceException exception){
		Map<String, String> failureMessages = new HashMap<String, String>();
		failureMessages.put("status", "false");
		failureMessages.put("error", exception.getMessage());
		return failureMessages;
	}

}
