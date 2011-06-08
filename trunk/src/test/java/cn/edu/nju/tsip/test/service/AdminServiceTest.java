package cn.edu.nju.tsip.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Admin;
import cn.edu.nju.tsip.service.IAdminService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;

@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class AdminServiceTest {
	
	private IAdminService<Admin> adminService;

	@Autowired
	public void setAdminService(IAdminService<Admin> adminService) {
		this.adminService = adminService;
	}

	public IAdminService<Admin> getAdminService() {
		return adminService;
	}

}
