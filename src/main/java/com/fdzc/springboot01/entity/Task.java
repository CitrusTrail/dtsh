package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Task {
    @TableId(value="id",type= IdType.AUTO)
    @ExcelProperty(value = "任务编号")
    private Integer id;
    @ExcelProperty(value = "任务名称")
    private String name;
    @ExcelProperty(value = "任务简介")
    private String description;
    @ExcelProperty(value = "碳排放")
    private Double carbon;
    @ExcelProperty(value = "积分")
    private Integer point;
    @ExcelProperty(value = "图片")
    private String image;
}
