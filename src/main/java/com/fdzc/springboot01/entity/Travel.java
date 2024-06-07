package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Travel {
    @TableId
    private Integer id;
    private Integer userId;
    private String startTime;
    private String endTime;
    private Integer totalTime;
    private String mode;
    private Double carbon;
    private String description;
}
