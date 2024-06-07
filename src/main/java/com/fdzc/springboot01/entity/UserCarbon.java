package com.fdzc.springboot01.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserCarbon {

    private Integer userId;
    private String date;
    private Double carbon;
}
