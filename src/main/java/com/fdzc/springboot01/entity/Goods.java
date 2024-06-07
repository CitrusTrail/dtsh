package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Goods {
    @TableId
    private Integer id;
    private String name;
    private Integer point;
    private Integer stock;
    private String description;
}
