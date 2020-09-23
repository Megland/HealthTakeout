package service;

import dao.DatabaseOperation;

public class LoginService {
    DatabaseOperation dbOp=new DatabaseOperation();
    public boolean loginCheck(String name,String password) {
    	return  dbOp.LoginfindMUser(name, password);
    }
}
