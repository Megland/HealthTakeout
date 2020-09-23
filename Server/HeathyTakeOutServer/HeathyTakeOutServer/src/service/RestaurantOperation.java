package service;

import java.util.List;

import entity.Restaurant;;

public interface RestaurantOperation {
	public abstract List<Restaurant> searchRestaurant(String key);
	public abstract int  deleteRestaurant(int no);
	public abstract int  insertRestaurant(Restaurant s);
	public abstract int  updateRestaurant(Restaurant s);
}
