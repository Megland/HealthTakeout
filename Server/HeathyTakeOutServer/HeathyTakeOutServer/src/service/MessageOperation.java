package service;

import java.util.List;


import entity.Message;

public interface MessageOperation {
	public abstract List<Message> searchMessage(String key);
	public abstract int  insertMessage(Message s);
}
