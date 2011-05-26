package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Picture;
import cn.edu.nju.tsip.service.IPictureService;

@Service("pictureService")
@Transactional
public class PictureServiceImpl<T extends Picture> extends ServiceImpl<T> implements IPictureService<T> {

	@Override
	public void create(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

}
