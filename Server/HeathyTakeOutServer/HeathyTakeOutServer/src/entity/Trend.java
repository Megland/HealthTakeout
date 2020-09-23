package entity;

public class Trend {
	private int trendno;
	private int userno;
	private String username;
	private String trendtitle;
	private String trendcontent;
	private String releasetime;
	public int getTrendno() {
		return trendno;
	}
	public void setTrendno(int trendno) {
		this.trendno = trendno;
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
	public String getTrendtitle() {
		return trendtitle;
	}
	public void setTrendtitle(String trendtitle) {
		this.trendtitle = trendtitle;
	}
	public String getTrendcontent() {
		return trendcontent;
	}
	public void setTrendcontent(String trendcontent) {
		this.trendcontent = trendcontent;
	}
	public String getReleasetime() {
		return releasetime;
	}
	public void setReleasetime(String releasetime) {
		this.releasetime = releasetime;
	}
	
	public Trend(int trendno, int userno, String username, String trendtitle, String trendcontent, String releasetime) {
		super();
		this.trendno = trendno;
		this.userno = userno;
		this.username = username;
		this.trendtitle = trendtitle;
		this.trendcontent = trendcontent;
		this.releasetime = releasetime;
	}
	
	
}
