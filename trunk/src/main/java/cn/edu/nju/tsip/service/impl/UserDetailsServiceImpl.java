package cn.edu.nju.tsip.service.impl;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.dao.IDao;
import cn.edu.nju.tsip.entity.Authority;
import cn.edu.nju.tsip.entity.Role;
import cn.edu.nju.tsip.entity.User;

import com.google.common.collect.Sets;

@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{
	
	@Autowired
	private IDao<User> dao;

	public UserDetails loadUserByUsername(String username)
			throws UsernameNotFoundException, DataAccessException {
		User user = dao.find(User.class, 1);
		
		if (user == null) {
			throw new UsernameNotFoundException("用户" + username + " 不存在");
		}

		//-- miniweb示例中无以下属性, 暂时全部设为true. --//
		//boolean enabled = true;
		//boolean accountNonExpired = true;
		//boolean credentialsNonExpired = true;
		//boolean accountNonLocked = true;
		
		return new org.springframework.security.core.userdetails.User(user.getLoginName(), user
				.getPassword(), true, true, true, true, obtainGrantedAuthorities(user));
	}
	
	/**
	 * 获得用户所有角色的权限集合.
	 */
	private Set<GrantedAuthority> obtainGrantedAuthorities(User user) {
		Set<GrantedAuthority> authSet = Sets.newHashSet();
		for (Role role : user.getRoleList()) {
			for (Authority authority : role.getAuthorityList()) {
				authSet.add(new GrantedAuthorityImpl(authority.getPrefixedName()));
			}
		}
		return authSet;
	}

	public void setDao(IDao<User> dao) {
		this.dao = dao;
	}

	public IDao<User> getDao() {
		return dao;
	}

}
