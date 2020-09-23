package com.jyl.healthytakeout.entity;

public class GoodsItem {
    public int foodno;
    public String category;
    public String foodname;
    public float price;
    public float calories;
    public String supplier;
    public String information;
    public int count;

    public GoodsItem(int foodno, String category, String foodname, float price, float calories, String supplier, String information, int count) {
        this.foodno = foodno;
        this.category = category;
        this.foodname = foodname;
        this.price = price;
        this.calories = calories;
        this.supplier = supplier;
        this.information = information;
        this.count = count;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
