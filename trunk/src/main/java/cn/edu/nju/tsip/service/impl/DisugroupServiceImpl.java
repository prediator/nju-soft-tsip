package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.DisuGroup;
import cn.edu.nju.tsip.service.IDisugroupService;

@Service("disugroupService")
@Transactional
public class DisugroupServiceImpl<T extends DisuGroup> extends ServiceImpl<T> implements IDisugroupService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	public T find(String name) {
		return dao.findUniqueBy("from DisuGroup as disuGroup where disuGroup.name =?", name);
	}

}
