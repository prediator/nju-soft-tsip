package cn.edu.nju.tsip.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Picture;
import cn.edu.nju.tsip.service.IPictureService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;


@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class PictureServiceTest {
	
	private IPictureService<Picture> pictureService;

	@Autowired
	public void setPictureService(IPictureService<Picture> pictureService) {
		this.pictureService = pictureService;
	}

	public IPictureService<Picture> getPictureService() {
		return pictureService;
	}

}
