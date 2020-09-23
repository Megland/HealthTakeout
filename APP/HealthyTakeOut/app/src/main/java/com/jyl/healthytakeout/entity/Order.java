package com.jyl.healthytakeout.entity;

public class Order {
    private int orderid;
    private int userno;
    private int restaurantid;
    private String username;
    private String restaurantname;
    private String orderdetails;
    private float totalprice;
    private String starttime;
    private String orderstatus;
    private int addressno;
    private String orderaddress;

    public Order(int orderid, int userno, int restaurantid, String username, String restaurantname, String orderdetails,
                 float totalprice, String starttime, String orderstatus, int addressno, String orderaddress) {
        super();
        this.orderid = orderid;
        this.userno = userno;
        this.restaurantid = restaurantid;
        this.username = username;
        this.restaurantname = restaurantname;
        this.orderdetails = orderdetails;
        this.totalprice = totalprice;
        this.starttime = starttime;
        this.orderstatus = orderstatus;
        this.addressno = addressno;
        this.orderaddress = orderaddress;
    }

    public int getOrderid() {
        return orderid;
    }

    public void setOrderid(int orderid) {
        this.orderid = orderid;
    }

    public int getUserno() {
        return userno;
    }

    public void setUserno(int userno) {
        this.userno = userno;
    }

    public int getRestaurantid() {
        return restaurantid;
    }

    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getRestaurantname() {
        return restaurantname;
    }

    public void setRestaurantname(String restaurantname) {
        this.restaurantname = restaurantname;
    }

    public String getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(String orderdetails) {
        this.orderdetails = orderdetails;
    }

    public float getTotalprice() {
        return totalprice;
    }

    public void setTotalprice(float totalprice) {
        this.totalprice = totalprice;
    }

    public String getStarttime() {
        return starttime;
    }

    public void setStarttime(String starttime) {
        this.starttime = starttime;
    }

    public String getOrderstatus() {
        return orderstatus;
    }

    public void setOrderstatus(String orderstatus) {
        this.orderstatus = orderstatus;
    }

    public int getAddressno() {
        return addressno;
    }

    public void setAddressno(int addressno) {
        this.addressno = addressno;
    }

    public String getOrderaddress() {
        return orderaddress;
    }

    public void setOrderaddress(String orderaddress) {
        this.orderaddress = orderaddress;
    }
}
