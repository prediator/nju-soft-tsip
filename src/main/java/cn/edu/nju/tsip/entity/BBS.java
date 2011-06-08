package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.List;
import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

/**
 * 公告的属性的定义
 * @author ljj
 *
 */
@Entity
public class BBS extends BaseEntity {
	
	/**
	 * 公告内容
	 */
	@NotNull
	private String content;
	
	/**
	 * 标题
	 */
	@NotNull
	private String title;
	
	/**
	 * 公告发布者
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User publisher;
	
	/**
	 * 公告被查看的次数，注意这里可能出现一个人阅读多次的问题
	 */
	private int readTimes;
	
	/**
	 * 评论列表
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private List<Comment> comments = Lists.newArrayList();
	
	@NotNull
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate = new Date();

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

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setReadTimes(int readTimes) {
		this.readTimes = readTimes;
	}

	public int getReadTimes() {
		return readTimes;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getTitle() {
		return title;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Date getCreateDate() {
		return createDate;
	}

}
