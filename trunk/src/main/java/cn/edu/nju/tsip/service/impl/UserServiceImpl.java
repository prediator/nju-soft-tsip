package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.dao.IDao;
import cn.edu.nju.tsip.entity.Role;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IUserService;

@Service("userService")
@Transactional
public class UserServiceImpl<T extends User> extends ServiceImpl<T> implements IUserService<T> {
	
	private IDao<Role> roleDao;

	@Autowired
	public void setRoleDao(IDao<Role> roleDao) {
		this.roleDao = roleDao;
	}

	public IDao<Role> getRoleDao() {
		return roleDao;
	}

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

	public boolean createRole(String name) {
		if(roleDao.findUniqueBy("from Role as role where role.name=?", name)!=null){
			return false;
		}else{
			Role role = new Role();
			role.setName(name);
			roleDao.persist(role);
			return true;
		}
	}

	public Role getRole(String name) {
		return roleDao.findUniqueBy("from Role as role where role.name =?", name);
	}

	@SuppressWarnings("unchecked")
	public List<T> getOnlineUsers() {
		return dao.createQuery("from User as user where user.online = true").list();
	}

}
