package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Shopcomment;

public class ShopCommentOperationImp implements ShopCommentOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Shopcomment> searchShopcomment(String key) {
		// TODO Auto-generated method stub
		return dbOp.findShopcommentInfo(key);
	}

	@Override
	public int deleteShopcomment(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertShopcomment(Shopcomment s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertShopcommentInfo(s);
		return i;
	}

	@Override
	public int updateShopcomment(Shopcomment s) {
		// TODO Auto-generated method stub
		return 0;
	}

}
