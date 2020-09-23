package entity;

public class Restaurant {
	private int restaurantid;
	private String restaurantname;
	private String introduction;
	private String restaurantaddress;
	
	public Restaurant(int restaurantid, String restaurantname, String introduction, String restaurantaddress) {
		super();
		this.restaurantid = restaurantid;
		this.restaurantname = restaurantname;
		this.introduction = introduction;
		this.restaurantaddress = restaurantaddress;
	}

	public int getRestaurantid() {
		return restaurantid;
	}

	public void setRestaurantid(int restaurantid) {
		this.restaurantid = restaurantid;
	}

	public String getRestaurantname() {
		return restaurantname;
	}

	public void setRestaurantname(String restaurantname) {
		this.restaurantname = restaurantname;
	}

	public String getIntroduction() {
		return introduction;
	}

	public void setIntroduction(String introduction) {
		this.introduction = introduction;
	}

	public String getRestaurantaddress() {
		return restaurantaddress;
	}

	public void setRestaurantaddress(String restaurantaddress) {
		this.restaurantaddress = restaurantaddress;
	}

	public Restaurant() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
