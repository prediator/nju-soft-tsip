package cn.edu.nju.tsip.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class GroupLetter extends BaseEntity {
	/**
	 * 悄悄话发送者
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User sender;
	
	/**
	 * 悄悄话接收者
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User receiver;
	
	/**
	 * 悄悄话内容
	 */
	@NotNull
	private String content;
	
	/**
	 * 创建时间
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private DisuGroup disuGroup;
	
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

	public DisuGroup getDisuGroup() {
		return disuGroup;
	}

	public void setDisuGroup(DisuGroup disuGroup) {
		this.disuGroup = disuGroup;
	}

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	private boolean readed = false;

}
