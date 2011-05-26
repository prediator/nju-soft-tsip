package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Article;
import cn.edu.nju.tsip.service.IArticleService;

@Service("articleService")
@Transactional
public class ArticleServiceImpl<T extends Article> extends ServiceImpl<T> implements IArticleService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	public List<T> getArticles(int start, int end) {
		return dao.list("form Article as article order by article.createDate desc", start, end - start+1);
	}

}
