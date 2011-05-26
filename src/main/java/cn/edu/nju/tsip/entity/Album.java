package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

import com.google.common.collect.Sets;

@Entity
public class Album extends BaseEntity {
	
	/**
	 * 相册的名称，对相册的描述就是相册的名称
	 */
	@NotNull
	private String name;
	
	/**
	 * 创建时间
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 相册的拥有者，也就是创建者
	 * @see User
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User owner;
	
	/**
	 * 相册的图片集合
	 * @see Picture
	 */
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn
	private Set<Picture> pictures = Sets.newHashSet();
	
	/**
	 * 如果是分享别人的相册，则此处不为空，评论为空；同时<code>shareAlbum</code>指向分享的album
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Album shareAlbum;//如果是分享相册，则此处指向分享的相册，此时pictures为null

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

	public Set<Picture> getPictures() {
		return pictures;
	}

	public void setPictures(Set<Picture> pictures) {
		this.pictures = pictures;
	}

	public Album getShareAlbum() {
		return shareAlbum;
	}

	public void setShareAlbum(Album shareAlbum) {
		this.shareAlbum = shareAlbum;
	}

}
