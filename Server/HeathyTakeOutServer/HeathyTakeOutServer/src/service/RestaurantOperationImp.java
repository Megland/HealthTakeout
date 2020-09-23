package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Restaurant;

public class RestaurantOperationImp implements RestaurantOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Restaurant> searchRestaurant(String key) {
		// TODO Auto-generated method stub
		return dbOp.findRestaurantInfo(key);
	}

	@Override
	public int deleteRestaurant(int no) {
		// TODO Auto-generated method stub
		int i = dbOp.deleteRestaurantInfo(no);
		return i;
	}

	@Override
	public int insertRestaurant(Restaurant s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertRestaurantInfo(s);
		return i;
	}

	@Override
	public int updateRestaurant(Restaurant s) {
		// TODO Auto-generated method stub
		int i=dbOp.updateRestaurant(s);
		return i;
	}

}
