package service;

import java.util.List;

import entity.User;

public interface UserOperation {
	public abstract List<User> searchUser(String key);
	public abstract int  deleteUser(int no);
	public abstract int  insertUser(User s);
	public abstract int  updateUser(User s);
}
