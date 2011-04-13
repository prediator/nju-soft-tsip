package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;

@Entity
@Inheritance(strategy = InheritanceType.JOINED )
public class User extends BaseEntity {
	
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false, unique = true)
	private String loginName;
	
	@NotNull
	@Size(min = 2, max = 25)
	private String password;
	
	@NotNull
	@Size(min = 2, max = 25)
	private String realName; //真实姓名
	
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SUBSELECT)
	private List<Role> roleList = Lists.newArrayList();
	
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Picture headImg;
	
	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn
	private Set<Group> groups;
	
	@OneToMany(mappedBy="publisher",fetch = FetchType.LAZY)
	private Set<Message> publishMsgs;
	
	@OneToMany(mappedBy="publisher",fetch = FetchType.LAZY)
	@OrderBy("createDate desc")
	private List<MBlog> mBlogs;
	
	@OneToMany(mappedBy="owner")
	private Set<Album> albums;
	
	@OneToMany(mappedBy="owner")
	private Set<Category> categories;
	
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	private Set<Message_User> user2Messages;

	public String getLoginName() {
		return loginName;
	}

	public void setLoginName(String loginName) {
		this.loginName = loginName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRealName() {
		return realName;
	}

	public void setRealName(String realName) {
		this.realName = realName;
	}

	public List<Role> getRoleList() {
		return roleList;
	}

	public void setRoleList(List<Role> roleList) {
		this.roleList = roleList;
	}

	public Picture getHeadImg() {
		return headImg;
	}

	public void setHeadImg(Picture headImg) {
		this.headImg = headImg;
	}

	public Set<Group> getGroups() {
		return groups;
	}

	public void setGroups(Set<Group> groups) {
		this.groups = groups;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	public Set<Message_User> getUser2Messages() {
		return user2Messages;
	}

	public void setUser2Messages(Set<Message_User> user2Messages) {
		this.user2Messages = user2Messages;
	} 

	public List<MBlog> getmBlogs() {
		return mBlogs;
	}

	public void setmBlogs(List<MBlog> mBlogs) {
		this.mBlogs = mBlogs;
	}

	public Set<Album> getAlbums() {
		return albums;
	}

	public void setAlbums(Set<Album> albums) {
		this.albums = albums;
	}
	
	public Set<Category> getCategories() {
		return categories;
	}

	public void setCategories(Set<Category> categories) {
		this.categories = categories;
	}
	
	public Set<Message> getPublishMsgs() {
		return publishMsgs;
	}

	public void setPublishMsgs(Set<Message> publishMsgs) {
		this.publishMsgs = publishMsgs;
	}

}
