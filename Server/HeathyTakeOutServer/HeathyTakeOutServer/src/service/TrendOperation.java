package service;

import java.util.List;

import entity.Trend;

public interface TrendOperation {
	public abstract List<Trend> searchTrend(String key);
	public abstract int  deleteTrend(int no);
	public abstract int  insertTrend(Trend s);
}
