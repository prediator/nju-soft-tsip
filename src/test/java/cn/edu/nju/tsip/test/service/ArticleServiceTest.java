package cn.edu.nju.tsip.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Article;
import cn.edu.nju.tsip.service.IArticleService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;


@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class ArticleServiceTest {
	
	private IArticleService<Article> articleService;

	@Autowired
	public void setArticleService(IArticleService<Article> articleService) {
		this.articleService = articleService;
	}

	public IArticleService<Article> getArticleService() {
		return articleService;
	}

}
