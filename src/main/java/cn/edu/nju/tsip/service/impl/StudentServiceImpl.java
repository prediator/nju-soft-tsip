package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.dao.IDao;
import cn.edu.nju.tsip.entity.Role;
import cn.edu.nju.tsip.entity.Student;
import cn.edu.nju.tsip.service.IStudentService;

@Service("studentService")
@Transactional
public class StudentServiceImpl<T extends Student> extends ServiceImpl<T> implements IStudentService<T> {
	
	private IDao<Role> roleDao;

	public IDao<Role> getRoleDao() {
		return roleDao;
	}

	@Autowired
	public void setRoleDao(IDao<Role> roleDao) {
		this.roleDao = roleDao;
	}

	@Override
	public void create(T baseBean) {
		//baseBean.getRoleList().add(roleDao.findUniqueBy("from Role as role where role.name =?","student"));
		dao.save(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	public List<T> getAll() {
		return dao.list("from Student as student");
	}

}
