package com.fdzc.springboot01.entity;

public class Goods {

    private Integer id;
    private String name;
    private Integer point;
    private Integer stock;
    private String description;

    public Goods() {
    }

    public Goods(Integer id, String name, Integer point, Integer stock, String description) {
        this.id = id;
        this.name = name;
        this.point = point;
        this.stock = stock;
        this.description = description;
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

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", point=" + point +
                ", stock=" + stock +
                ", description='" + description + '\'' +
                '}';
    }
}
