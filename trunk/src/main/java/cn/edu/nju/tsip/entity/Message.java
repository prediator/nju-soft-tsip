package cn.edu.nju.tsip.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Message extends BaseEntity {
	
	private String content;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User publisher;
	
	@OneToMany(mappedBy="message",fetch = FetchType.LAZY)
	private Set<Message_User> message2Users;
	
	private List<Comment> comments;

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

}
