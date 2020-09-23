package service;

import java.util.List;

import entity.Feedback;

public interface FeedbackOperation {
	public abstract List<Feedback> searchFeedback(String key);
	public abstract int  deleteFeedback(int no);
	public abstract int  insertFeedback(Feedback s);
	public abstract int  updateFeedback(Feedback s);
}