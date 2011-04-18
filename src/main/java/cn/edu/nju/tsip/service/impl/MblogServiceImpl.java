package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IMblogService;

@Service("mblogService")
@Transactional
public class MblogServiceImpl<T extends User> extends ServiceImpl<T> implements
		IMblogService<T> {

	@Override
	public void create(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

}
