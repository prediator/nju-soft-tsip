package cn.edu.nju.tsip.service;

import cn.edu.nju.tsip.entity.DisuGroup;

public interface IDisugroupService<T extends DisuGroup> extends IService<T> {
	
	public T find(String name);

}
