package cn.edu.nju.tsip.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.edu.nju.tsip.dao.IDao;
import cn.edu.nju.tsip.entity.Message;
import cn.edu.nju.tsip.entity.Message_User;
import cn.edu.nju.tsip.service.IMessageService;

@Service("messageService")
@Transactional
public class MessageServiceImpl<T extends Message> extends ServiceImpl<T> implements IMessageService<T> {
	
	@Autowired
	private IDao<Message_User> muDao;

	public IDao<Message_User> getMuDao() {
		return muDao;
	}

	public void setMuDao(IDao<Message_User> muDao) {
		this.muDao = muDao;
	}

	@Override
	public void create(T baseBean) {
		dao.save(baseBean);
		
	}

	@Override
	public void delete(T baseBean) {
		
	}

	public List<T> getPublishMessages(int userId) {
		return dao.list("from Message as message where message.publisher.id ="+userId);
	}

	public T getPublishMessage(int userId, int messageId) {
		return dao.findUniqueBy("from Message as message where message.publisher.id =? and message.id =?", userId,messageId);
	}

	public Message_User getReceiveMessage(int userId, int messageId) {
		return muDao.findUniqueBy("from Message_User as mu wehere mu.message.id =? and mu.user.id =?", messageId,userId);
		
	}

	public void updateMessage_User(Message_User mu) {
		muDao.update(mu);
	}

}
