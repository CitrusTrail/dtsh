package com.fdzc.springboot01.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    @TableId
    private Integer id;
    private String name;
    private String password;
    private String address;
    private String tel;
    private Integer point;
    private String avatar;
}
