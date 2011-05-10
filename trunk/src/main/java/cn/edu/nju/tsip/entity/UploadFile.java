package cn.edu.nju.tsip.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class UploadFile extends BaseEntity {
	
	/**
	 * 上传的文件名
	 */
	private String name;
	
	/**
	 * 件所在的位置的相对地址，用于url的指向之用
	 */
	private String location;
	
	/**
	 * 对文件的描述，也就是显示给用户看的
	 */
	private String description;
	
	/**
	 * 上传时间
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 上传者
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User publisher;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getLocation() {
		return location;
	}

}
