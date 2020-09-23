package entity;

public class Focus {
	private int focusid;
	private int userno;
	private int focususerno;
	
	public Focus(int focusid, int userno, int focususerno) {
		super();
		this.focusid = focusid;
		this.userno = userno;
		this.focususerno = focususerno;
	}
	
	public int getFocusid() {
		return focusid;
	}
	public void setFocusid(int focusid) {
		this.focusid = focusid;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	public int getFocususerno() {
		return focususerno;
	}
	public void setFocususerno(int focususerno) {
		this.focususerno = focususerno;
	}

	
	
	
	
}
