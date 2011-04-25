package cn.edu.nju.tsip.test.web;

import java.util.Map;

import org.junit.Test;
import org.junit.runner.Request;

import com.google.common.collect.Maps;

import sun.applet.resources.MsgAppletViewer;

import cn.edu.nju.tsip.test.util.ControllerTest;
import cn.edu.nju.tsip.web.MblogController;

public class MblogControllerTest extends ControllerTest<MblogController> {

	@Override
	protected String getBeanName() {
		return "mblogController";
	}
	
	@Test
	public void testAddMBlog(){
		request.getSession().setAttribute("id", "1");
		Map<String, Object> param = Maps.newHashMap();
		controller.getMBlogs(param, response, request.getSession());
		assertEquals("home", "home");
	}
}
