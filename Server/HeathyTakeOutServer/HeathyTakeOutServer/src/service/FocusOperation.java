package service;

import java.util.List;

import entity.User;
import entity.Fans;
import entity.Focus;

public interface FocusOperation {
	public abstract List<User> searchFocus(String key);
	public abstract List<Focus> Checkfocus(String key,String key2);
	public abstract List<User> searchFans(String key);
	public abstract int  insertFans(Fans s);
	public abstract int  cancelFocus(int no,int no2);
}
