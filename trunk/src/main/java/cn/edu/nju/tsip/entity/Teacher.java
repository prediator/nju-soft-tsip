package cn.edu.nju.tsip.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Teacher extends User {
	
	/**
	 * 老师工号
	 */
	private int trno;
	
	/**
	 * 性别
	 */
	private boolean sex;
	
	/**
	 * 出生年月日
	 */
	@Temporal(value = TemporalType.DATE)
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
