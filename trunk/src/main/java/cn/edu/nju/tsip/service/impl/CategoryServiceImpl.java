package cn.edu.nju.tsip.service.impl;

import cn.edu.nju.tsip.entity.Category;
import cn.edu.nju.tsip.service.ICategoryService;

public class CategoryServiceImpl<T extends Category> extends ServiceImpl<T> implements ICategoryService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	public T getCategory(String name, int userId) {
		return dao.findUniqueBy("from Category as category where category.name =? and category.id =?", name, userId);
	}

}
