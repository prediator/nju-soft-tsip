package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Article;
import cn.edu.nju.tsip.service.IArticleService;

@Service("articleService")
@Transactional
public class ArticleServiceImpl<T extends Article> extends ServiceImpl<T> implements IArticleService<T> {

	@Override
	public void create(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

}
