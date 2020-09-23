package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Trendcomment;

public class TrendCommentOperationImp implements TrendCommentOperation{
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Trendcomment> searchTrendcomment(String key) {
		// TODO Auto-generated method stub
		return dbOp.findTrendcommentInfo(key);
	}

	@Override
	public int deleteTrendcomment(int no) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int insertTrendcomment(Trendcomment s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertTrendcommentInfo(s);
		return i;
	}

}
