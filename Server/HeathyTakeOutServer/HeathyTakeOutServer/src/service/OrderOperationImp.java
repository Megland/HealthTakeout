package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Order;

public class OrderOperationImp implements OrderOperation {
	DatabaseOperation dbOp = new DatabaseOperation();

	@Override
	public List<Order> searchOrder(String key) {
		// TODO Auto-generated method stub
		return dbOp.findOrderInfo(key);
	}

	@Override
	public int deleteOrder(int no) {
		// TODO Auto-generated method stub
		int i = dbOp.deleteOrderInfo(no);
		return i;
	}

	@Override
	public int insertOrder(Order s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertOrderInfo(s);
		return i;
	}

	@Override
	public int updateOrders(Order s) {
		// TODO Auto-generated method stub
		int i=dbOp.updateOrder(s);
		return i;
	}
}
