package com.fdzc.springboot01.entity;

import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserGroup {

    private Integer userId;
    private Integer groupId;
}
