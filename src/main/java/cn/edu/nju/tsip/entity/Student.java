package cn.edu.nju.tsip.entity;
import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Student extends User {
	
	/**
	 * 学生学号
	 */
	private int stno;
	
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
	
	@OneToMany(mappedBy="student",fetch = FetchType.LAZY)
	private Set<CourseInfo> courseInfos;

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

	public void setCourseInfos(Set<CourseInfo> courseInfos) {
		this.courseInfos = courseInfos;
	}

	public Set<CourseInfo> getCourseInfos() {
		return courseInfos;
	}


}
