package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Album;
import cn.edu.nju.tsip.entity.Picture;
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

	public T get(int userId, String albumName) {
		return dao.findUniqueBy("from Album as album where album.name =? and album.owner.id =?", albumName,userId);
	}

	public List<T> getAlbums(int userId) {
		return dao.list("from Album as album where album.owner.id = "+userId);
	}

	public Picture getCover(int albumId) {
		return (Picture) dao.createQuery("select max(picture.createDate) from Picture picture where pictures_id = "+albumId).uniqueResult();
		//====================problem=================================
		//没有在album中设置cover，且picture中没有指向album，albumservice中的dao不能返回picture
	}

}
