package cn.edu.nju.tsip.service;

import cn.edu.nju.tsip.entity.MBlog;

public interface IMblogService<T extends MBlog> extends IService<T> {
	
	public boolean delete(int mblogId,int userId);

}
