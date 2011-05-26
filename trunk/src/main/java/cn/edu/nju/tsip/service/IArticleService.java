package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.Article;

public interface IArticleService<T extends Article> extends IService<T> {
	
	public List<T> getArticles(int start,int end);

}
