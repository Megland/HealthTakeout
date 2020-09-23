package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Food;

public class FoodOperationImp implements FoodOperation {
	DatabaseOperation dbOp = new DatabaseOperation();

	@Override
	public List<Food> searchFood(String key) {
		// TODO Auto-generated method stub
		return dbOp.findFoodInfo(key);
	}
	
	@Override
	public List<Food> searchFoodbyrestaurantid(String key) {
		// TODO Auto-generated method stub
		return dbOp.findFoodInfobyRestaurantid(key);
	}

	@Override
	public int deleteFood(int no) {
		int i = dbOp.deleteFoodInfo(no);
		return i;
	}

	@Override
	public int insertFood(Food s) {
		int i = dbOp.insertFoodInfo(s);
		return i;
	}

	@Override
	public int updateFood(Food s) {
		int i=dbOp.updateFood(s);
		return i;
	}

}

