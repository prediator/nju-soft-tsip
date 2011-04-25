package cn.edu.nju.tsip.service;

import cn.edu.nju.tsip.entity.Role;
import cn.edu.nju.tsip.entity.User;

public interface IUserService<T extends User> extends IService<T> {
	
	/**
	 * 通过帐号和密码获取用户
	 * @param loginName
	 * @return
	 */
	public T getUser(String loginName,String password);
	
	public boolean createRole(String name);
	
	public  Role getRole(String name);

}
