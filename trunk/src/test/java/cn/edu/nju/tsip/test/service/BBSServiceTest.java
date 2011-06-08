package cn.edu.nju.tsip.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.BBS;
import cn.edu.nju.tsip.service.IBBSService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;


@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class BBSServiceTest {
	
	private IBBSService<BBS> bbsService;

	@Autowired
	public void setBbsService(IBBSService<BBS> bbsService) {
		this.bbsService = bbsService;
	}

	public IBBSService<BBS> getBbsService() {
		return bbsService;
	}

}
