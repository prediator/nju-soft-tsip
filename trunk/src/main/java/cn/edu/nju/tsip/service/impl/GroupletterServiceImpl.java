package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.entity.GroupLetter;
import cn.edu.nju.tsip.service.IGroupletterService;

@Service("groupletterService")
@Transactional
public class GroupletterServiceImpl<T extends GroupLetter> extends ServiceImpl<T> implements IGroupletterService<T> {

	@Override
	public void create(T baseBean) {
		dao.persist(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		// TODO Auto-generated method stub
		
	}

	@SuppressWarnings("unchecked")
	public List<T> getUnreadedLetters(int receiverId) {
		return dao.createQuery("from GroupLetter as letter where letter.receiver.id = :rid and letter.readed = false")
		.setParameter("rid", receiverId).list();
	}

}
