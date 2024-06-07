package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@TableName("`group`")
public class Group {
    @TableId
    private Integer id;
    private String name;
    private String description;
    private Integer creator;
}
