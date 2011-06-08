package cn.edu.nju.tsip.entity;

import java.util.List;
import java.util.Set;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

/**
 * Message中定义了消息的相关属性
 * @author ljj
 * @see #title
 * @see #content
 * @see #comments
 * @see #message2Users
 * @see #publisher
 *
 */
@Entity
public class Message extends BaseEntity {
	
	/**
	 * 消息标题，不能为空
	 */
	@NotNull
	private String title;
	
	/**
	 * 消息内容
	 */
	@NotNull
	private String content;
	
	/**
	 * 消息发布者
	 * @see User
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User publisher;
	
	/**
	 * 消息所对应的每个用户状态
	 * @see Message_User
	 */
	@OneToMany(cascade = CascadeType.ALL,mappedBy="message",fetch = FetchType.LAZY)
	private Set<Message_User> message2Users = Sets.newHashSet();
	
	/**
	 * 对消息的评论，也就是对消息的回复
	 * @see Comment
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private List<Comment> comments = Lists.newArrayList();

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getPublisher() {
		return publisher;
	}

	public void setPublisher(User publisher) {
		this.publisher = publisher;
	}

	public Set<Message_User> getMessage2Users() {
		return message2Users;
	}

	public void setMessage2Users(Set<Message_User> message2Users) {
		this.message2Users = message2Users;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}
}