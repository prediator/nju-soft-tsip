package cn.edu.nju.tsip.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class CourseInfo extends BaseEntity{
	
	private int semester;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Course course;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Student student;

	public int getSemester() {
		return semester;
	}

	public void setSemester(int semester) {
		this.semester = semester;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Student getStudent() {
		return student;
	}

	public void setStudent(Student student) {
		this.student = student;
	}
	
	

}
