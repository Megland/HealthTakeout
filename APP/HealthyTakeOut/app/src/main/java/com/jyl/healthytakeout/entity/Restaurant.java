package com.jyl.healthytakeout.entity;

import java.io.Serializable;

public class Restaurant implements Serializable {
    private String restaurantid;
    private String restaurantname;
    private String introduction;
    private String restaurantaddress;

    public String getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(String restaurantid) {
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
    }

    public Restaurant(String restaurantid, String restaurantname, String introduction, String restaurantaddress) {
        this.restaurantid = restaurantid;
        this.restaurantname = restaurantname;
        this.introduction = introduction;
        this.restaurantaddress = restaurantaddress;
    }
}
