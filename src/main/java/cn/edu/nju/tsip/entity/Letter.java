package cn.edu.nju.tsip.entity;

import java.util.Date;

import javax.persistence.*;

@Entity
public class Letter extends BaseEntity {
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User sender;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User receiver;
	
	private String content;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;

	public User getSender() {
		return sender;
	}

	public void setSender(User sender) {
		this.sender = sender;
	}

	public User getReceiver() {
		return receiver;
	}

	public void setReceiver(User receiver) {
		this.receiver = receiver;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

}
