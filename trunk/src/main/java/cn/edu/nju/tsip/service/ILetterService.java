package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.Letter;

public interface ILetterService<T extends Letter> extends IService<T> {
	
	public void create(String content,int sender,int receiver);
	
	public List<T> getUnreadedLetters(int senderId,int receiverId);
	
	public List<T> getUnreadedLetters(int receiverId);

}
