package com.fdzc.springboot01.vo;

import lombok.Data;

@Data
public class UserVo {

    private String userName;
    private Integer gender;
    private String birthday;
    private Integer deptId;
    private String deptName;
    private String description;
    private String address;

}
