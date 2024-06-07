package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import org.springframework.stereotype.Component;

@Component
public class Buy {
    @TableId
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String time;
    private Integer point;
    private Integer num;

    public Buy() {
    }

    public Buy(Integer id, Integer userId, Integer goodsId, String time, Integer point, Integer num) {
        this.id = id;
        this.userId = userId;
        this.goodsId = goodsId;
        this.time = time;
        this.point = point;
        this.num = num;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(Integer goodsId) {
        this.goodsId = goodsId;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getPoint() {
        return point;
    }

    public void setPoint(Integer point) {
        this.point = point;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Override
    public String toString() {
        return "Buy{" +
                "id=" + id +
                ", userId=" + userId +
                ", goodsId=" + goodsId +
                ", time='" + time + '\'' +
                ", point=" + point +
                ", num=" + num +
                '}';
    }
}
