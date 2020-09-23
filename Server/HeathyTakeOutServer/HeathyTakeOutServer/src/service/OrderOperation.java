package service;
import java.util.List;
import entity.Order;

public interface OrderOperation {
	public abstract List<Order> searchOrder(String key);
	public abstract int  deleteOrder(int no);
	public abstract int  insertOrder(Order s);
	public abstract int  updateOrders(Order s);
}






