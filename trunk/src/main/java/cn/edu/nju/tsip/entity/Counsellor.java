package cn.edu.nju.tsip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
public class Counsellor extends User {
	/**
	 * 辅导员学号（貌似辅导员是学生）
	 */
	private int csno;
	
	/**
	 * 备注，或者是自我介绍
	 */
	private String remarks;
	
	/**
	 * 兴趣爱好
	 */
	private String hobby;
	
	/**
	 * 擅长
	 */
	private String talent;
	
	/**
	 * 性别
	 */
	private boolean sex;
	
	/**
	 * 出生日期，格式年月日
	 */
	@Temporal(value = TemporalType.DATE)
	private Date birthday;

	public int getCsno() {
		return csno;
	}

	public void setCsno(int csno) {
		this.csno = csno;
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
