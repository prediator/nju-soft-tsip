package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.*;
import javax.validation.constraints.*;

import com.google.common.collect.Lists;

@Entity
public class Picture extends BaseEntity {
	
	/**
	 * 图片的描述是用户浏览图片时的名字，这个名字可以很长，带有描述性质的，区别<code>name</code>属性
	 */
	@NotNull
	private String descritpion;
	
	/**
	 * 图片创建的时间
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 对图片的评论，如果是分享的图片，此处将没有评论
	 * @see Comment
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private List<Comment> comments = Lists.newArrayList();
	
	/**
	 * 图片的名字是图片的所在的相对位置，出现在超链接中，图片的绝对路径是固定的前部分路径加上图片的相对位置
	 * 区分description
	 */
	@NotNull
	private String name;
	
	/**
	 * 如果图片是分享的，则<code>sharePicture</code>不为空，此时指向真正的图片，你可以在description中加入你对图片的描述
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Picture sharePicture;

	public String getDescritpion() {
		return descritpion;
	}

	public void setDescritpion(String descritpion) {
		this.descritpion = descritpion;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public List<Comment> getComments() {
		return comments;
	}

	public void setComments(List<Comment> comments) {
		this.comments = comments;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Picture getSharePicture() {
		return sharePicture;
	}

	public void setSharePicture(Picture sharePicture) {
		this.sharePicture = sharePicture;
	}

}
