package cn.edu.nju.tsip.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Message_User extends BaseEntity {
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Message message;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;
	
	private boolean readed;

	public Message getMessage() {
		return message;
	}

	public void setMessage(Message message) {
		this.message = message;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isReaded() {
		return readed;
	}

	public void setReaded(boolean readed) {
		this.readed = readed;
	}

}
