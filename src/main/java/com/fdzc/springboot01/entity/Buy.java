package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Buy {
    @TableId
    private Integer id;
    private Integer userId;
    private Integer goodsId;
    private String time;
    private Integer point;
    private Integer num;
}
