package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Component;

@Component
public class Task {
    @TableId
    private Integer id;
    private String name;
    private String description;
    private Double carbon;
    private Integer point;

    public Task() {
    }

    public Task(Integer id, String name, String description, Double carbon, Integer point) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.carbon = carbon;
        this.point = point;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Double getCarbon() {
        return carbon;
    }

    public void setCarbon(Double carbon) {
        this.carbon = carbon;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "Task{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", carbon=" + carbon +
                ", point=" + point +
                '}';
    }
}
