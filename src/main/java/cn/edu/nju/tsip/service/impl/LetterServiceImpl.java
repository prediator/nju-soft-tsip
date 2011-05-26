package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.Letter;
import cn.edu.nju.tsip.service.ILetterService;

@Service("letterService")
@Transactional
public class LetterServiceImpl<T extends Letter> extends ServiceImpl<T> implements ILetterService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	public void create(String content, int sender, int receiver) {
		
	}

	@SuppressWarnings("unchecked")
	public List<T> getUnreadedLetters(int senderId, int receiveId) {
		return dao.createQuery("from Letter as letter where letter.sender.id = :sid and letter.receiver.id = :rid and letter.readed = false")
			.setParameter("sid", senderId).setParameter("rid", receiveId).list();
	}

	@SuppressWarnings("unchecked")
	public List<T> getUnreadedLetters(int receiverId) {
		return dao.createQuery("from Letter as letter where letter.receiver.id = :rid and letter.readed = false")
			.setParameter("rid", receiverId).list();
	}
	
	public List<T> getLatestLetters(int receiverId,int senderId,int start,int end){
		return dao.list("from Letter as letter where (letter.sender.id =? and letter.receiver.id =?) or (letter.sender.id =? and letter.receiver.id =?) order by letter.createDate desc", start, end-start +1, 
				receiverId,senderId,senderId,receiverId);
		
	}

}
