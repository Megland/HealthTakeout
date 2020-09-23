package service;

import java.util.List;

import entity.Shopcomment;

public interface ShopCommentOperation {
	public abstract List<Shopcomment> searchShopcomment(String key);
	public abstract int  deleteShopcomment(int no);
	public abstract int  insertShopcomment(Shopcomment s);
	public abstract int  updateShopcomment(Shopcomment s);
}
