package com.fdzc.springboot01.vo;

import com.fdzc.springboot01.entity.User;
import lombok.Data;

import java.util.List;

@Data
public class DeptVo {

    private Integer id;
    private String deptName;
    private String description;
    private String address;
    private List<User> users;

}
