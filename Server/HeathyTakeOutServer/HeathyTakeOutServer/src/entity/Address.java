package entity;

public class Address {
	private int addressno;
	private String contactperson;
	private String gender;
	private String phone;
	private String addressdetail;
	private int userno;
	public Address(int addressno, String contactperson, String gender, String phone, String addressdetail, int userno) {
		super();
		this.addressno = addressno;
		this.contactperson = contactperson;
		this.gender = gender;
		this.phone = phone;
		this.addressdetail = addressdetail;
		this.userno = userno;
	}
	
	public int getAddressno() {
		return addressno;
	}
	public void setAddressno(int addressno) {
		this.addressno = addressno;
	}
	public String getContactperson() {
		return contactperson;
	}
	public void setContactperson(String contactperson) {
		this.contactperson = contactperson;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getAddressdetail() {
		return addressdetail;
	}
	public void setAddressdetail(String addressdetail) {
		this.addressdetail = addressdetail;
	}
	public int getUserno() {
		return userno;
	}
	public void setUserno(int userno) {
		this.userno = userno;
	}
	
	

}
