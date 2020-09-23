package entity;

public class Feedback {
	private int feedbackno;
	private String feedbackdetail;
	private int userno;
	private String username;
	private String contactdetail;
	public int getFeedbackno() {
		return feedbackno;
	}
	public void setFeedbackno(int feedbackno) {
		this.feedbackno = feedbackno;
	}
	public String getFeedbackdetail() {
		return feedbackdetail;
	}
	public void setFeedbackdetail(String feedbackdetail) {
		this.feedbackdetail = feedbackdetail;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getContactdetail() {
		return contactdetail;
	}
	public void setContactdetail(String contactdetail) {
		this.contactdetail = contactdetail;
	}
	
	public Feedback(int feedbackno, String feedbackdetail, int userno, String username, String contactdetail) {
		super();
		this.feedbackno = feedbackno;
		this.feedbackdetail = feedbackdetail;
		this.userno = userno;
		this.username = username;
		this.contactdetail = contactdetail;
	}
	
	
}
