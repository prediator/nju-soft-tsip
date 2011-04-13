package cn.edu.nju.tsip.entity;

import java.sql.Date;
import java.util.List;

import javax.persistence.*;

@Entity
public class MBlog extends BaseEntity {
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User publisher;
	
	private String content;
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private MBlog flwBlog;
	
	private boolean deleted;
	
	@OneToMany
	private List<Comment> comments;

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

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public MBlog getFlwBlog() {
		return flwBlog;
	}

	public void setFlwBlog(MBlog flwBlog) {
		this.flwBlog = flwBlog;
	}

	public boolean isDeleted() {
		return deleted;
	}

	public void setDeleted(boolean deleted) {
		this.deleted = deleted;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

}
