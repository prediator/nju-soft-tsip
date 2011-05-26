package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.UploadFile;

public interface IFileService<T extends UploadFile> extends IService<T> {
	
	public List<T> getFiles();

}
