package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Share {
    @TableId
    private Integer id;
    private Integer userId;
    private String content;
    private String time;
    private Integer likes;
}
