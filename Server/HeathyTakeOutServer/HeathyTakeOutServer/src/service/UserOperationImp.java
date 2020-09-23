package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.User;

public class UserOperationImp implements UserOperation {
	DatabaseOperation dbOp = new DatabaseOperation();

	@Override
	public List<User> searchUser(String key) {
		// TODO Auto-generated method stub
		return dbOp.findUserInfo(key);
	}

	@Override
	public int deleteUser(int no) {
		int i = dbOp.deleteUserInfo(no);
		return i;
	}

	@Override
	public int insertUser(User s) {
		int i = dbOp.insertUserInfo(s);
		return i;
	}

	@Override
	public int updateUser(User s) {
		int i=dbOp.updateUser(s);
		return i;
	}

}
