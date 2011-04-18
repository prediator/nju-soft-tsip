package cn.edu.nju.tsip.test.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.mock.web.MockServletContext;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.XmlWebApplicationContext;

import cn.edu.nju.tsip.web.UserController;

public class HomeControllerTest extends TestCase{
	
	private HttpServletRequest request = new MockHttpServletRequest();  
    private HttpServletResponse response = new MockHttpServletResponse();  
    private XmlWebApplicationContext context;  
    private MockServletContext msc;  
    private UserController userController;
    
    protected void setUp() throws Exception {  
        String[] contexts = new String[] {"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"};  
        context = new XmlWebApplicationContext();  
        context.setConfigLocations(contexts);  
        msc = new MockServletContext();  
        context.setServletContext(msc);  
        context.refresh();  
        msc.setAttribute(WebApplicationContext.ROOT_WEB_APPLICATION_CONTEXT_ATTRIBUTE, context);  
        userController = (UserController) context.getBean("userController");
    } 
    
    @Test
    public void testNothing(){
    	
    }

}
