package cn.edu.nju.tsip.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

import javax.persistence.*;
import javax.validation.constraints.*;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

@Entity
@Inheritance(strategy = InheritanceType.JOINED )
public class User extends BaseEntity {
	
	/**
	 * 登录帐号，如ljj09
	 */
	@NotNull
	@Size(min = 2, max = 25)
	@Column(nullable = false, unique = true)
	private String loginName;
	
	/**
	 * 密码
	 */
	@NotNull
	@Size(min = 2, max = 25)
	private String password;
	
	/**
	 * 真实姓名
	 */
	@NotNull
	@Size(min = 2, max = 25)
	private String realName; 
	
	@ManyToMany
	@JoinTable
	@Fetch(FetchMode.SUBSELECT)
	private List<Role> roleList = Lists.newArrayList();
	
	/**
	 * 用户头像文件所在位置，null的时候没有头像
	 */
	@OneToOne(fetch = FetchType.LAZY)
	@JoinColumn
	private Picture headImg;
	
	/**
	 * 用户的联系人分组
	 */
	@OneToMany(fetch = FetchType.LAZY)
    @JoinColumn
	private Set<UserGroup> userGroups = Sets.newHashSet();
	
	/**
	 * 用户总共发布的消息列表
	 * @see Message
	 */
	@OneToMany(mappedBy="publisher",fetch = FetchType.LAZY)
	private Set<Message> publishMsgs = Sets.newHashSet();
	
	/**
	 * 用户发表的微博（状态）列表，包括转发的微博
	 * @see MBlog
	 */
	@OneToMany(mappedBy="publisher",fetch = FetchType.LAZY)
	@OrderBy("createDate desc")
	private List<MBlog> mBlogs = Lists.newArrayList();
	
	/**
	 * 用户相册列表，包括分享的相册
	 * @see Album
	 */
	@OneToMany(mappedBy="owner")
	private Set<Album> albums = Sets.newHashSet();
	
	/**
	 * 用户的文章分类列表，用户发表的或分享的文章在<code>Category.article3</code>
	 * @see Category
	 */
	@OneToMany(mappedBy="owner")
	private Set<Category> categories = Sets.newHashSet();
	
	/**
	 * 用户创建日期，年月日时分秒
	 */
	@Temporal(value = TemporalType.TIMESTAMP)
	private Date createDate;
	
	/**
	 * 用户收到的message，以及对message所做的操作
	 * @see Message_User
	 */
	@OneToMany(mappedBy="user",fetch = FetchType.LAZY)
	private Set<Message_User> user2Messages = Sets.newHashSet();
	
	private boolean online = false;
	
	private String loginPlace;

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

	public Set<UserGroup> getUserGroups() {
		return userGroups;
	}

	public void setGroups(Set<UserGroup> userGroups) {
		this.userGroups = userGroups;
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

	public void setOnline(boolean online) {
		this.online = online;
	}

	public boolean isOnline() {
		return online;
	}

	public void setLoginPlace(String loginPlace) {
		this.loginPlace = loginPlace;
	}

	public String getLoginPlace() {
		return loginPlace;
	}

}
