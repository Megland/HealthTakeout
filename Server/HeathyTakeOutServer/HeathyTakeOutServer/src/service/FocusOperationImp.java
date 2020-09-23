package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Fans;
import entity.Focus;
import entity.User;

public class FocusOperationImp implements FocusOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<User> searchFocus(String key) {
		// TODO Auto-generated method stub
		return dbOp.findFocusInfo(key);
	}
	@Override
	public List<Focus> Checkfocus(String key, String key2) {
		// TODO Auto-generated method stub
		return dbOp.Checkfocus(key, key2);
	}
	
	@Override
	public List<User> searchFans(String key) {
		// TODO Auto-generated method stub
		return dbOp.findFansInfo(key);
	}
	@Override
	public int insertFans(Fans s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertFocusInfo(s);
		return i;
	}
	@Override
	public int cancelFocus(int no, int no2) {
		// TODO Auto-generated method stub
		int i=dbOp.cancelFocus(no, no2);
		return i;
	}

}
