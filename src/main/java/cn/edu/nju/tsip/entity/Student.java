package cn.edu.nju.tsip.entity;
import java.util.Date;

import javax.persistence.*;

@Entity
public class Student extends User {
	
	private int stno;
	
	private String remarks;
	
	private String hobby;
	
	private String talent;
	
	private boolean sex;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date birthday;

	public int getStno() {
		return stno;
	}

	public void setStno(int stno) {
		this.stno = stno;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public String getHobby() {
		return hobby;
	}

	public void setHobby(String hobby) {
		this.hobby = hobby;
	}

	public String getTalent() {
		return talent;
	}

	public void setTalent(String talent) {
		this.talent = talent;
	}

	public boolean isSex() {
		return sex;
	}

	public void setSex(boolean sex) {
		this.sex = sex;
	}

	public Date getBirthday() {
		return birthday;
	}

	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


}
