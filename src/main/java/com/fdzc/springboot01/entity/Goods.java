package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Goods {
    @TableId
    @ExcelProperty(value = "商品编号")
    private Integer id;
    @ExcelProperty(value = "商品名称")
    private String name;
    @ExcelProperty(value = "积分")
    private Integer point;
    @ExcelProperty(value = "库存")
    private Integer stock;
    @ExcelProperty(value = "商品简介")
    private String description;
}
