package cn.edu.nju.tsip.test.web;

import java.io.IOException;
import java.util.Map;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.junit.Test;
import com.google.common.collect.Maps;

import cn.edu.nju.tsip.test.util.ControllerTest;
import cn.edu.nju.tsip.web.MblogController;

public class MblogControllerTest extends ControllerTest<MblogController> {

	@Override
	protected String getBeanName() {
		return "mblogController";
	}
	
	@Test
	public void testAddMBlog(){
		request.getSession().setAttribute("id", 1);
		Map<String, Object> param = Maps.newHashMap();
		param.put("content", "it is a test");
		controller.addMBlog(param, response, request.getSession());
		
	}
	
	@Test
	public void testGetMbogs() throws JsonGenerationException, JsonMappingException, IOException{
		request.getSession().setAttribute("id", 1);
		Map<String, Object> param = Maps.newHashMap();
		Map<String,? extends Object> map = controller.getMBlogs(param, response, request.getSession());
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper.writeValueAsString(map));
		//System.out.println("begin to write");
		//String path = Thread.currentThread().getContextClassLoader().getResource("output.txt").getPath();
		//FileWriter fileWriter = new FileWriter(path);
		//BufferedWriter bWriter = new BufferedWriter(fileWriter);
		//bWriter.write("adfasd");
		//fileWriter.write("adfsdafdsa");
		//bWriter.write(mapper.writeValueAsString(map));
		//bWriter.close();
		//fileWriter.close();
		//List<Map<String, Object>> rList = (List<Map<String, Object>>) map.get("mblogs");
	}
}
