package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
public class Album extends BaseEntity {
	
	private String name;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private User owner;
	
	private Set<Picture> pictures;
	
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
