package cn.edu.nju.tsip.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Category;
import cn.edu.nju.tsip.service.ICategoryService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;


@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class CategoryServiceTest {
	
	private ICategoryService<Category> categoryService;

	@Autowired
	public void setCategoryService(ICategoryService<Category> categoryService) {
		this.categoryService = categoryService;
	}

	public ICategoryService<Category> getCategoryService() {
		return categoryService;
	}

}
