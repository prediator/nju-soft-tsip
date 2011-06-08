package cn.edu.nju.tsip.test.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;

import cn.edu.nju.tsip.entity.Leader;
import cn.edu.nju.tsip.service.ILeaderService;
import cn.edu.nju.tsip.test.util.SpringJUnit45ClassRunner;


@RunWith(SpringJUnit45ClassRunner.class)
@ContextConfiguration(locations={"classpath:spring/root-context.xml","classpath:spring/appServlet/servlet-context.xml","classpath:spring/appServlet/controllers.xml"})
public class LeaderServiceTest {
	
	private ILeaderService<Leader> leaderService;

	@Autowired
	public void setLeaderService(ILeaderService<Leader> leaderService) {
		this.leaderService = leaderService;
	}

	public ILeaderService<Leader> getLeaderService() {
		return leaderService;
	}

}
