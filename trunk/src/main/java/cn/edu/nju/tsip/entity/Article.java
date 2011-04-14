package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

/**
 * 定义日志文章的属性，包括分享的文章，在创建新文章时调用validator，检查属性，在分享的文章中不要检查！
 * @author ljj
 *
 */
@Entity
public class Article extends BaseEntity {
	
	/**
	 * 文章标题
	 */
	@NotNull
	private String title;
	
	/**
	 * 创建日期
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 文章发布者，如果分享的文章，则这里是分享的人
	 * @see User
	 */
	@NotNull
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User publisher;
	
	/**
	 * 文章内容，如果是分享，这里是对文章说明，分享里有之类的
	 */
	@NotNull
	private String content;
	
	/**
	 * 最后修改日期
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date updateDate;
	
	/**
	 * 所在分类，如果是分享的，则把文章放在share里面
	 * @see Category
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Category category;
	
	/**
	 * 文章是否可见，对自己可见，还是对所有可见，默认对所有可见
	 */
	private boolean visible = true;
	
	/**
	 * 如果是分享的文章，则指向分享的文章，默认把分享的文章放在share的category中
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Article shareArticle;
	
	/**
	 * 评论列表
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private List<Comment> comments = Lists.newArrayList();

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

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

	public Date getUpdateDate() {
		return updateDate;
	}

	public void setUpdateDate(Date updateDate) {
		this.updateDate = updateDate;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Article getShareArticle() {
		return shareArticle;
	}

	public void setShareArticle(Article shareArticle) {
		this.shareArticle = shareArticle;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getCategory() {
		return category;
	}

}
