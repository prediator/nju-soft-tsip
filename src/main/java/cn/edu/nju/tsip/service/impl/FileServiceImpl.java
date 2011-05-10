package cn.edu.nju.tsip.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.UploadFile;
import cn.edu.nju.tsip.service.IFileService;

@Service("fileService")
@Transactional
public class FileServiceImpl<T extends UploadFile> extends ServiceImpl<T> implements IFileService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

}
