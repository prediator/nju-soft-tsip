package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.MBlog;

public interface IMblogService<T extends MBlog> extends IService<T> {
	
	public boolean delete(int mblogId,int userId);
	public List<T> getMBlogs(int page);
	public List<T> getAllMBlogs();

}
