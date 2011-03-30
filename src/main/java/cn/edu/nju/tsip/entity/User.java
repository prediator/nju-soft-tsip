package cn.edu.nju.tsip.entity;

import javax.persistence.Entity;

@Entity
public class User extends BaseEntity {
	
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
