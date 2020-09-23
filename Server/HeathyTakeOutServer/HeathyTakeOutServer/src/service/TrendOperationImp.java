package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Trend;

public class TrendOperationImp implements TrendOperation {
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Trend> searchTrend(String key) {
		// TODO Auto-generated method stub
		return dbOp.findTrendInfo(key);
	}

	@Override
	public int deleteTrend(int no) {
		// TODO Auto-generated method stub
		int i = dbOp.deleteTrendInfo(no);
		return i;
	}

	@Override
	public int insertTrend(Trend s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertTrendInfo(s);
		return i;
	}

}
