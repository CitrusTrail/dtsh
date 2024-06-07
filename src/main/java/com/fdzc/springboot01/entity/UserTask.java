package com.fdzc.springboot01.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserTask {

    private Integer userId;
    private Integer taskId;
    private String completedTime;
}
