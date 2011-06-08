package cn.edu.nju.tsip.entity;

import javax.persistence.Entity;

@Entity
public class Course extends BaseEntity{
	
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
