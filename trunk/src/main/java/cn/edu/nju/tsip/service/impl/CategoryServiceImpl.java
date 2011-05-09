package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Category;
import cn.edu.nju.tsip.service.ICategoryService;
import cn.edu.nju.tsip.service.ServiceException;

@Service("categoryService")
@Transactional
public class CategoryServiceImpl<T extends Category> extends ServiceImpl<T> implements ICategoryService<T> {

	@Override
	public void create(T baseBean) {
		if(dao.findUniqueBy("from Category as category where category.name =? and category.owner.id=?", baseBean.getName(),baseBean.getOwner().getId())!=null){
			throw new ServiceException();//发现同一个用户的分类名称相同则抛出异常
		}else{
			dao.persist(baseBean);
		}
	}//==========================problem report ==========================
	//有的create要检验，有点不用检验，通过统一boolean还是通过抛出exception处理呢

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	public T getCategory(String name, int userId) {
		return dao.findUniqueBy("from Category as category where category.name =? and category.id =?", name, userId);
	}

}
