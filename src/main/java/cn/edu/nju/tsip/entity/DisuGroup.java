package cn.edu.nju.tsip.entity;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.google.common.collect.Sets;

@Entity
public class DisuGroup extends BaseEntity {
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable
	private Set<User> users = Sets.newHashSet();
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable
	private User author;
	
	private String name;

	
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

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}
}
