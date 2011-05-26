package cn.edu.nju.tsip.test.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Letter;
import cn.edu.nju.tsip.service.ILetterService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;

@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class LetterServiceTest {
	
	ILetterService<Letter> letterService;

	public ILetterService<Letter> getLetterService() {
		return letterService;
	}

	@Autowired
	public void setLetterService(ILetterService<Letter> letterService) {
		this.letterService = letterService;
	}
	
	@Test
	public void addLetterTest(){
		List<Letter> letters=letterService.getLatestLetters(1, 2, 0, 29);
		for(Letter letter : letters){
			System.out.println(letter.getSender().getRealName() + " to " + letter.getReceiver().getRealName()+" : "+letter.getContent());
		}
	}

}
