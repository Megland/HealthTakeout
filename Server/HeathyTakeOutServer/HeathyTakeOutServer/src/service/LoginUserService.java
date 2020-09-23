package service;

import dao.DatabaseOperation;

public class LoginUserService {
    DatabaseOperation dbOp=new DatabaseOperation();
    public int loginCheck(String name,String password) {
    	return  dbOp.LoginUser(name, password);
    }
}
