package cn.edu.nju.tsip.entity;

import java.util.List;

import javax.persistence.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

@Entity
public class Role extends BaseEntity {
	
	@Column(nullable = false, unique = true)
	private String name;
	
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SUBSELECT)
	private List<Authority> authorityList = Lists.newArrayList();

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Authority> getAuthorityList() {
		return authorityList;
	}

	public void setAuthorityList(List<Authority> authorityList) {
		this.authorityList = authorityList;
	}

}
