package cn.edu.nju.tsip.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Category extends BaseEntity {
	
	/**
	 * 分类名称
	 */
	@NotNull
	private String name;
	
	/**
	 * 分类中的文章列表，默认存在一个uncategory分类，可以删除
	 * @see Article
	 */
	@OneToMany(mappedBy="category",fetch = FetchType.LAZY)
    @JoinColumn
	private List<Article> articles;
	
	/**
	 * 分类的拥有者，间接指定了文章作者，由于要联级查询，所以在文章中存放作者，同时可以让用户把分享的文章归类到自己的类别中
	 * @see User
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn
	@NotNull
	private User owner;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Article> getArticles() {
		return articles;
	}

	public void setArticles(List<Article> articles) {
		this.articles = articles;
	}

	public User getOwner() {
		return owner;
	}

	public void setOwner(User owner) {
		this.owner = owner;
	}

}
