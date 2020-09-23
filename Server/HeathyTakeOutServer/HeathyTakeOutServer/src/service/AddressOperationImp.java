package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Address;

public class AddressOperationImp implements AddressOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Address> searchAddress(String key) {
		// TODO Auto-generated method stub
		return dbOp.findAddressInfo(key);
	}

	@Override
	public int deleteAddress(int no) {
		// TODO Auto-generated method stub
		int i = dbOp.deleteAddressInfo(no);
		return i;
	}

	@Override
	public int insertAddress(Address s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertAddressInfo(s);
		return i;
	}

	@Override
	public int updateAddress(Address s) {
		// TODO Auto-generated method stub
		int i=dbOp.updateAddress(s);
		return i;
	}

}
