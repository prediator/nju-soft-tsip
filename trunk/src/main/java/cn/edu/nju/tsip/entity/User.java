package cn.edu.nju.tsip.entity;

import javax.persistence.Entity;
import javax.validation.constraints.*;

@Entity
public class User extends BaseEntity {
	
	@NotNull
	@Size(min = 2, max = 25)
	private String name;

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

}
