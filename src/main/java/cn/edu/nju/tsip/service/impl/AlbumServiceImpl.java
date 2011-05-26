package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Album;
import cn.edu.nju.tsip.service.IAlbumService;
import cn.edu.nju.tsip.service.ServiceException;

@Service("albumService")
@Transactional
public class AlbumServiceImpl<T extends Album> extends ServiceImpl<T> implements IAlbumService<T> {

	@Override
	public void create(T baseBean) {
		if(dao.findUniqueBy("from Album as album where album.name =? and album.owner.id=?", baseBean.getName(),baseBean.getOwner().getId())!=null){
			throw new ServiceException("相册名相同");//发现同一个用户的相册名称相同则抛出异常
		}else{
			dao.persist(baseBean);
		}
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	public T delete(int userId, String albumName) {
		return dao.findUniqueBy("from Album as album where album.name =? and album.owner.id =?", albumName,userId);
	}

}
