package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Teacher;
import cn.edu.nju.tsip.service.ITeacherService;

@Service("teacherService")
@Transactional
public class TeacherServiceImpl<T extends Teacher> extends ServiceImpl<T> implements ITeacherService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

}
