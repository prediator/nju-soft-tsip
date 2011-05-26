package cn.edu.nju.tsip.service.impl;

import java.util.List;

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

	public List<T> getFiles() {
		return dao.list("from UploadFile as file order by file.createDate desc");
	}

}
