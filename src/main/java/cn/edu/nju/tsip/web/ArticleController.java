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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import cn.edu.nju.tsip.entity.Article;
import cn.edu.nju.tsip.entity.Category;
import cn.edu.nju.tsip.service.IArticleService;
import cn.edu.nju.tsip.service.ICategoryService;

@Controller
public class ArticleController {
	
	private Validator validator;
	
	private IArticleService<Article> articleService;
	
	private ICategoryService<Category> categoryService;

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
		
		return null;
		
	}
	
	private Map<String, String> validationMessages(Set<ConstraintViolation<Article>> failures) {
		Map<String, String> failureMessages = new HashMap<String, String>();
		for (ConstraintViolation<Article> failure : failures) {
			failureMessages.put(failure.getPropertyPath().toString(), failure.getMessage());
		}
		return failureMessages;
	}

}
