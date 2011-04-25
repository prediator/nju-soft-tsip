package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Comment;
import cn.edu.nju.tsip.service.ICommentService;

@Service("commentService")
@Transactional
public class CommentServiceImpl <T extends Comment> extends ServiceImpl<T> implements ICommentService<T>{

	@Override
	public void create(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

}
