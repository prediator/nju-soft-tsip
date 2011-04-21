package cn.edu.nju.tsip.test.web;

import org.junit.Test;
import cn.edu.nju.tsip.test.util.ControllerTest;
import cn.edu.nju.tsip.web.HomeController;

public class HomeControllerTest extends ControllerTest<HomeController>{ 
    
    @Test
    public void testHome(){
    	assertEquals("home", controller.home());
    }

	@Override
	protected String getBeanName() {
		return "homeController";
	}

}
