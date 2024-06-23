package com.fdzc.springboot01.entity.vo;

import lombok.Data;

@Data
public class TaskVo {
    private Integer id;
    private Integer taskId;
    private String name;
    private String description;
    private Double carbon;
    private Integer point;
    private String image;
    private String status;
    private String completedTime;
}
