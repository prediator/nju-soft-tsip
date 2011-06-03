package cn.edu.nju.tsip.service;

import java.util.List;

import cn.edu.nju.tsip.entity.Message;
import cn.edu.nju.tsip.entity.Message_User;

public interface IMessageService<T extends Message> extends IService<T> {
	
	public List<T> getPublishMessages(int userId);
	
	public T getPublishMessage(int userId,int messageId);
	
	public Message_User getReceiveMessage(int userId,int messageId);
	
	public void updateMessage_User(Message_User mu);

}
