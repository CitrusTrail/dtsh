package com.fdzc.springboot01.entity.vo;

import lombok.Data;

@Data
public class BuyVo {
    private Integer id;
    private Integer goodsId;
    private String name;
    private String time;
    private Integer point;
    private Integer num;
    private String image;
}
