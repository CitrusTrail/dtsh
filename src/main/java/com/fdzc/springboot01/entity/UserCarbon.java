package com.fdzc.springboot01.entity;

public class UserCarbon {

    private Integer userId;
    private String date;
    private Double carbon;

    public UserCarbon() {
    }

    public UserCarbon(Integer userId, String date, Double carbon) {
        this.userId = userId;
        this.date = date;
        this.carbon = carbon;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public Double getCarbon() {
        return carbon;
    }

    public void setCarbon(Double carbon) {
        this.carbon = carbon;
    }

    @Override
    public String toString() {
        return "UserCarbon{" +
                "userId=" + userId +
                ", date='" + date + '\'' +
                ", carbon=" + carbon +
                '}';
    }
}
