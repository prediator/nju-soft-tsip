package cn.edu.nju.tsip.entity;

import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Sets;

/**
 * @author ljj
 * @see User
 *
 */
@Entity
public class UserGroup extends BaseEntity {
	
	/**
	 * 联系人分组名，不能为空
	 */
	@NotNull
	private String name;
	
	/**
	 * 分组中的好友列表
	 */
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SUBSELECT)
	private Set<User> users = Sets.newHashSet();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<User> getUsers() {
		return users;
	}

	public void setUsers(Set<User> users) {
		this.users = users;
	}

}
