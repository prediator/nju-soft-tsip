package cn.edu.nju.tsip.entity;

import java.util.List;
import java.util.Set;

import javax.persistence.*;

@Entity
public class BBS extends BaseEntity {
	
	private String content;
	
	private User publisher;
	
	private Set<User> readUsers;
	
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

	public Set<User> getReadUsers() {
		return readUsers;
	}

	public void setReadUsers(Set<User> readUsers) {
		this.readUsers = readUsers;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
