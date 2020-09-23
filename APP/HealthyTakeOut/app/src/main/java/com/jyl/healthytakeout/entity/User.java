package com.jyl.healthytakeout.entity;

import java.io.Serializable;

public class User implements Serializable {
    private int userno;
    private String username;
    private String password;
    private String gender;
    private int age;
    private float height;
    private float weight;
    private float targetweight;
    private String preference;

    public User(int userno, String username, String password, String gender, int age, float height, float weight, float targetweight, String preference) {
        this.userno = userno;
        this.username = username;
        this.password = password;
        this.gender = gender;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.targetweight = targetweight;
        this.preference = preference;
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

    public String getPassword() {
        return password;
    }

    public float getTargetweight() {
        return targetweight;
    }

    public void setTargetweight(float targetweight) {
        this.targetweight = targetweight;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public float getHeight() {
        return height;
    }

    public void setHeight(float height) {
        this.height = height;
    }

    public float getWeight() {
        return weight;
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public String getPreference() {
        return preference;
    }

    public void setPreference(String preference) {
        this.preference = preference;
    }
}
