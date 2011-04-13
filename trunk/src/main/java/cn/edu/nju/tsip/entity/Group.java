package cn.edu.nju.tsip.entity;

import java.util.Set;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

@Entity
public class Group extends BaseEntity {
	
	private String name;
	
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SUBSELECT)
	private Set<User> users;

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
