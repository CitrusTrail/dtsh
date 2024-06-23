package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class User {
    @TableId(value="id",type= IdType.AUTO)
    @ExcelProperty(value = "用户编号")
    private Integer id;
    @ExcelProperty(value = "用户名称")
    private String name;
    @ExcelProperty(value = "密码")
    private String password;
    @ExcelProperty(value = "地址")
    private String address;
    @ExcelProperty(value = "联系电话")
    private String tel;
    @ExcelProperty(value = "积分")
    private Integer point;
    @ExcelProperty(value = "碳排放")
    private Double carbon;
    @ExcelProperty(value = "头像")
    private String avatar;
}
