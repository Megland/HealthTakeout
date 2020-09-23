package com.jyl.healthytakeout.entity;

public class Shopcomment {
    private int shopcommentno;
    private int restaurantid;
    private String content;
    private String commenttime;
    private int userno;
    private String username;
    public Shopcomment(int shopcommentno, int restaurantid, String content, String commenttime, int userno,
                       String username) {
        super();
        this.shopcommentno = shopcommentno;
        this.restaurantid = restaurantid;
        this.content = content;
        this.commenttime = commenttime;
        this.userno = userno;
        this.username = username;
    }
    public int getShopcommentno() {
        return shopcommentno;
    }
    public void setShopcommentno(int shopcommentno) {
        this.shopcommentno = shopcommentno;
    }
    public int getRestaurantid() {
        return restaurantid;
    }
    public void setRestaurantid(int restaurantid) {
        this.restaurantid = restaurantid;
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
