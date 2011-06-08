package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.Student;;

public interface IStudentService<T extends Student> extends IService<T> {
	
	public List<T> getAll();
	
	public List<T> search(String grade,String remarks,String talent,String hobby,Boolean sex,String name);

}
