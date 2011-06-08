package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.BBS;

public interface IBBSService<T extends BBS> extends IService<T>{
	
	public T get(int bbsId,int userId);
	
	public List<T> getBBSs();

}
