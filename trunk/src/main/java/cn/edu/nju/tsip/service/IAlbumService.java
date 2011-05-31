package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.Album;
import cn.edu.nju.tsip.entity.Picture;

public interface IAlbumService<T extends Album> extends IService<T> {
	
	public T get(int userId,String albumName);
	
	public List<T> getAlbums(int userId);
	
	public Picture getCover(int albumId);

}
