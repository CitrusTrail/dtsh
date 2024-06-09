package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
@TableName("`group`")
public class Group {
    @TableId
    @ExcelProperty(value = "小组编号")
    private Integer id;
    @ExcelProperty(value = "小组名称")
    private String name;
    @ExcelProperty(value = "小组简介")
    private String description;
    @ExcelProperty(value = "创建者编号")
    private Integer creator;
}
