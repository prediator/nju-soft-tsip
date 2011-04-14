package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl<T extends User> extends ServiceImpl<T> implements
		IUserService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);

	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub

	}

	public T getUser(String loginName,String password) {
		return dao.findUniqueBy("from User as user where user.loginName =? and user.password =?", loginName,password);
	}

}
