package service;

import java.util.List;

import entity.Trendcomment;

public interface TrendCommentOperation {
	public abstract List<Trendcomment> searchTrendcomment(String key);
	public abstract int  deleteTrendcomment(int no);
	public abstract int  insertTrendcomment(Trendcomment s);
}
