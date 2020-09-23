package entity;

public class Food {
	private int foodno;
	private String category;
	private String foodname;
	private float price;
	private float calories;
	private String supplier;
	private String information;
	
	public Food(){}
	public Food(int foodno, String category, String foodname, float price, float calories, String supplier,
			String information) {
		super();
		this.foodno = foodno;
		this.category = category;
		this.foodname = foodname;
		this.price = price;
		this.calories = calories;
		this.supplier = supplier;
		this.information = information;
	}

	public int getFoodno() {
		return foodno;
	}

	public void setFoodno(int foodno) {
		this.foodno = foodno;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getFoodname() {
		return foodname;
	}

	public void setFoodname(String foodname) {
		this.foodname = foodname;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public float getCalories() {
		return calories;
	}

	public void setCalories(float calories) {
		this.calories = calories;
	}

	public String getSupplier() {
		return supplier;
	}

	public void setSupplier(String supplier) {
		this.supplier = supplier;
	}

	public String getInformation() {
		return information;
	}

	public void setInformation(String information) {
		this.information = information;
	}
}
