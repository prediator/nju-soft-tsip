package cn.edu.nju.tsip.service;

import cn.edu.nju.tsip.entity.Album;

public interface IAlbumService<T extends Album> extends IService<T> {
	
	public T delete(int userId,String albumName);

}
