package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.GroupLetter;

public interface IGroupletterService<T extends GroupLetter> extends IService<T> {
	
	public List<T> getUnreadedLetters(int receiverId);

}
