package cn.edu.nju.tsip.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Teacher extends User {
	
	private int trno;
	
	private boolean sex;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date birthday;

	public int getTrno() {
		return trno;
	}

	public void setTrno(int trno) {
		this.trno = trno;
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
