package cn.edu.nju.tsip.test.web;

import java.util.Map;
import java.util.Set;

import org.junit.Test;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import cn.edu.nju.tsip.test.util.ControllerTest;
import cn.edu.nju.tsip.web.MessageController;

public class MessageControllerTest  extends ControllerTest<MessageController>{

	@Override
	protected String getBeanName() {
		return "messageController";
	}
	
	@Test
	public void testCreateMessage(){
		request.getSession().setAttribute("id", 466);
		request.getSession().setAttribute("role", "teacher");
		Map<String, Object> param = Maps.newHashMap();
		param.put("content", "寒假将要到来，清同学注意。。。");
		param.put("title", "关于放寒假的通知");
		Set<Map<String, Integer>> receivers = Sets.newHashSet();
		Map<String, Integer> id1 = Maps.newHashMap();
		id1.put("id", 1);
		receivers.add(id1);
		
		Map<String, Integer> id2 = Maps.newHashMap();
		id2.put("id", 2);
		receivers.add(id2);
		
		param.put("receivers", receivers);
		controller.addMessage(param, response, request.getSession());
	}

}
