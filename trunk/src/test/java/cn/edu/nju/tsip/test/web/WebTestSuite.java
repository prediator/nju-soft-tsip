package cn.edu.nju.tsip.test.web;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

@RunWith(Suite.class)

@Suite.SuiteClasses({
	ArticleControllerTest.class,
	BBSControllerTest.class,
	FileControllerTest.class,
	HomeControllerTest.class,
	LetterControllerTest.class,
	MblogControllerTest.class,
	MessageControllerTest.class,
	PictureControllerTest.class,
	StudentControllerTest.class,
	UserControllerTest.class

})
public class WebTestSuite {

}
