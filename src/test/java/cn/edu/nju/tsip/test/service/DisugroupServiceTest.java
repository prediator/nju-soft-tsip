package cn.edu.nju.tsip.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.DisuGroup;
import cn.edu.nju.tsip.service.IDisugroupService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;


@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class DisugroupServiceTest {
	
	private IDisugroupService<DisuGroup> disugroupService;

	@Autowired
	public void setDisugroupService(IDisugroupService<DisuGroup> disugroupService) {
		this.disugroupService = disugroupService;
	}

	public IDisugroupService<DisuGroup> getDisugroupService() {
		return disugroupService;
	}

}
