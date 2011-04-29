package cn.edu.nju.tsip.service;

import cn.edu.nju.tsip.entity.Category;

public interface ICategoryService<T extends Category> extends IService<T> {
	
	public T getCategory(String name,int userId);

}
