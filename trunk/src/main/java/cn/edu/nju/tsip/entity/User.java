package cn.edu.nju.tsip.entity;

import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

@Entity
public class User extends BaseEntity {
	
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false, unique = true)
	private String loginName;
	
	@NotNull
	@Size(min = 2, max = 25)
	private String password;
	
	@NotNull
	@Size(min = 2, max = 25)
	private String name;
	
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SUBSELECT)
	private List<Role> roleList = Lists.newArrayList();

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getLoginName() {
		return loginName;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

}
