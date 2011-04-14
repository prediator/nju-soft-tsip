package cn.edu.nju.tsip.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * 该类比较特殊，只是把<code>Message</code>和<code>User</code>类的链接，增加相关的属性。
 * 其中发布的message只有一个，但是对应到用户时是每个用户一个message_user
 * @see User#user2Messages
 * @see Message#message2Users
 * @author ljj
 *
 */
@Entity
public class Message_User extends BaseEntity {
	
	/**
	 * 用户收到的消息
	 * @see User#user2Messages
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Message message;
	
	/**
	 * 消息指向的用户
	 * @see Message#message2Users
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User user;
	
	/**
	 * 消息是否被读
	 */
	private boolean readed = false;

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
