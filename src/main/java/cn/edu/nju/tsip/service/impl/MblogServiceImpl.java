package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.MBlog;
import cn.edu.nju.tsip.entity.User;
import cn.edu.nju.tsip.service.IMblogService;

@Service("mblogService")
@Transactional
public class MblogServiceImpl<T extends MBlog> extends ServiceImpl<T> implements IMblogService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		
		
	}

	public boolean delete(int mblogId, int userId) {
		T mblog = dao.findUniqueBy("from MBlog as mblog where mblog.id=? mblog.publisher.id=?", mblogId,userId);
		if(mblog==null){
			return false;
		}else{
			mblog.setDeleted(true);
			dao.update(mblog);
			return true;
		}
	}

	public List<T> getMBlogs(int page) {
		//dao.list("from MBlog as mblog", "", maxSize, params);
		return null;
	}

	public List<T> getAllMBlogs() {
		return dao.list("from MBlog as mblog where mblog.deleted = false");
	}

}
