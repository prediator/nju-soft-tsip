package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Lists;

/**
 * 定义评论的相关属性，评论是可以嵌套的，所有能评论的实体都含有评论列表
 * @author ljj
 * @see #author
 * @see #authorIP
 * @see #cmntChild
 * @see #content
 * @see #createDate
 * 
 */
@Entity
public class Comment extends BaseEntity {
	
	/**
	 * 评论内容
	 */
	@NotNull
	private String content;
	
	/**
	 * 评论者
	 * @see User
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@NotNull
	private User author;
	
	/**
	 * 评论这的ip地址，暂时忽略
	 */
	private String authorIP;
	
	/**
	 * 一个评论下，可能有多个子评论，子评论下，可能还有多个子评论，如果一个评论（不管是对文章，相册的评论 还是对评论的评论），
	 * 他的cmntChilds.size()>0则说明存在子评论<br>
	 *   comment1 for article ----------------------<br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; childcomment1_1 for comment1---<br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; childcomment1_2 for comment1---<br>
	 *   comment2 for article ---------------<br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; chilecomment2_1 for comment2---<br>
	 *   &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; childcomment2_2 for comment2---<br>
	 * 
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private List<Comment> cmntChilds = Lists.newArrayList();
	
	/**
	 * 创建时间
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public User getAuthor() {
		return author;
	}

	public void setAuthor(User author) {
		this.author = author;
	}

	public String getAuthorIP() {
		return authorIP;
	}

	public void setAuthorIP(String authorIP) {
		this.authorIP = authorIP;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public void setCmntChilds(List<Comment> cmntChilds) {
		this.cmntChilds = cmntChilds;
	}

	public List<Comment> getCmntChilds() {
		return cmntChilds;
	}

}
