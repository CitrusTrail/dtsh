package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Buy {
    @TableId(value="id",type= IdType.AUTO)
    @ExcelProperty(value = "订单编号")
    private Integer id;
    @ExcelProperty(value = "用户编号")
    private Integer userId;
    @ExcelProperty(value = "商品编号")
    private Integer goodsId;
    @ExcelProperty(value = "兑换时间")
    private String time;
    @ExcelProperty(value = "积分")
    private Integer point;
    @ExcelProperty(value = "数量")
    private Integer num;
}
