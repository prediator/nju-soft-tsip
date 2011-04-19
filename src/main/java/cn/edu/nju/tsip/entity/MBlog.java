package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

/**
 * 定义微博（状态）的相关属性
 * @author ljj
 *
 */
@Entity
public class MBlog extends BaseEntity {
	
	/**
	 * 创建时间
	 */
	@NotNull
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate = new Date();
	
	/**
	 * 微博发布者
	 * @see User#mBlogs
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User publisher;
	
	/**
	 * 微博的内容，如果是转发的微博则此处是对转发的微博的评论
	 */
	@NotNull
	private String content;
	
	/**
	 * 如果微博是转发的，则此处指向转发的微博，转发是可以嵌套的，所以判断微博是否为转发应该根据<code>flwBlog</code>是否为空
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private MBlog flwBlog;
	
	/**
	 * 微博是不能从数据库中删除的，因为有很多转发的微博指向原来的微博，所以设置deleted属性，指定博客是否被删除
	 */
	private boolean deleted = false;
	
	/**
	 * 评论列表
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
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
