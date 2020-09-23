package service;

import java.util.List;
import entity.Food;

public interface FoodOperation {
	public abstract List<Food> searchFood(String key);
	public abstract List<Food> searchFoodbyrestaurantid(String key);
	public abstract int  deleteFood(int no);
	public abstract int  insertFood(Food s);
	public abstract int  updateFood(Food s);
}
