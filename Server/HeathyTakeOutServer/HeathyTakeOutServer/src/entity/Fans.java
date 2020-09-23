package entity;

public class Fans {
	
	private int fansid;
	private int userno;
	private int fansuserno;
	
	public Fans(int fansid, int userno, int fansuserno) {
		super();
		this.fansid = fansid;
		this.userno = userno;
		this.fansuserno = fansuserno;
	}
	public int getFansid() {
		return fansid;
	}
	public void setFansid(int fansid) {
		this.fansid = fansid;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getFansuserno() {
		return fansuserno;
	}
	public void setFansuserno(int fansuserno) {
		this.fansuserno = fansuserno;
	}
	
	
}
