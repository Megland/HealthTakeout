package service;

import java.util.List;

import dao.DatabaseOperation;
import entity.Feedback;

public class FeedbackOperationImp implements FeedbackOperation{
	DatabaseOperation dbOp = new DatabaseOperation();
	@Override
	public List<Feedback> searchFeedback(String key) {
		// TODO Auto-generated method stub
		return dbOp.findFeedbackInfo(key);
	}

	@Override
	public int deleteFeedback(int no) {
		// TODO Auto-generated method stub
		int i = dbOp.deleteFeedbackInfo(no);
		return i;
	}

	@Override
	public int insertFeedback(Feedback s) {
		// TODO Auto-generated method stub
		int i = dbOp.insertFeedbackInfo(s);
		return i;
	}

	@Override
	public int updateFeedback(Feedback s) {
		// TODO Auto-generated method stub
		int i=dbOp.updateFeedback(s);
		return i;
	}

}
