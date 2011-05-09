package cn.edu.nju.tsip.entity;

import java.util.Date;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 定义悄悄话的属性。悄悄话也是在线聊天的基础
 * @author ljj
 *
 */
@Entity
public class Letter extends BaseEntity {
	
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
	
	private boolean readed = false;

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

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

	public boolean isReaded() {
		return readed;
	}

}
