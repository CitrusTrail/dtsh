package com.fdzc.springboot01.entity;

import org.springframework.stereotype.Component;

@Component
public class Dept {

    private Integer id;
    private String deptName;
    private String description;
    private String address;

    public Dept() {
    }

    public Dept(Integer id, String deptName, String description, String address) {
        this.id = id;
        this.deptName = deptName;
        this.description = description;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Override
    public String toString() {
        return "Dept{" +
                "id=" + id +
                ", deptName='" + deptName + '\'' +
                ", description='" + description + '\'' +
                ", address='" + address + '\'' +
                '}';
    }
}
