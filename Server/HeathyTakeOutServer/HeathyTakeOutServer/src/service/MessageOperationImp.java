package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Message;

public class MessageOperationImp implements MessageOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Message> searchMessage(String key) {
		// TODO Auto-generated method stub
		return dbOp.findMessageInfo(key);
	}
	@Override
	public int insertMessage(Message s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertMessageInfo(s);
		return i;
	}
}
