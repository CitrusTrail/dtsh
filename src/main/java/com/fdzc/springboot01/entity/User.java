package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Component;

@Component
public class User {
    @TableId
    private Integer id;
    private String name;
    private String password;
    private String address;
    private String tel;
    private Integer point;
    private String avatar;

    public User() {
    }

    public User(Integer id, String name, String password, String address, String tel, Integer point, String avatar) {
        this.id = id;
        this.name = name;
        this.password = password;
        this.address = address;
        this.tel = tel;
        this.point = point;
        this.avatar = avatar;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", address='" + address + '\'' +
                ", tel='" + tel + '\'' +
                ", point=" + point +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
