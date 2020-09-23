package service;

import java.util.List;

import entity.Address;

public interface AddressOperation {
	public abstract List<Address> searchAddress(String key);
	public abstract int  deleteAddress(int no);
	public abstract int  insertAddress(Address s);
	public abstract int  updateAddress(Address s);
}
