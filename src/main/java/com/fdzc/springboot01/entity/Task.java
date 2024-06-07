package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Task {
    @TableId
    private Integer id;
    private String name;
    private String description;
    private Double carbon;
    private Integer point;
}
