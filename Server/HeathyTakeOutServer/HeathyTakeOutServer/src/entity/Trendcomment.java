package entity;

public class Trendcomment {
	private int trendcommentno;
	private int trendno;
	private String content;
	private String commenttime;
	private int userno;
	private String username;
	
	public Trendcomment(int trendcommentno, int trendno, String content, String commenttime, int userno,
			String username) {
		super();
		this.trendcommentno = trendcommentno;
		this.trendno = trendno;
		this.content = content;
		this.commenttime = commenttime;
		this.userno = userno;
		this.username = username;
	}
	
	public int getTrendcommentno() {
		return trendcommentno;
	}
	public void setTrendcommentno(int trendcommentno) {
		this.trendcommentno = trendcommentno;
	}
	public int getTrendno() {
		return trendno;
	}
	public void setTrendno(int trendno) {
		this.trendno = trendno;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public String getCommenttime() {
		return commenttime;
	}
	public void setCommenttime(String commenttime) {
		this.commenttime = commenttime;
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
	
	

}
