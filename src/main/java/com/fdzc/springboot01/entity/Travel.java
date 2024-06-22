package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Travel {
    @TableId(value="id",type= IdType.AUTO)
    @ExcelProperty(value = "出行编号")
    private Integer id;
    @ExcelProperty(value = "用户编号")
    private Integer userId;
    @ExcelProperty(value = "出行开始时间")
    private String startTime;
    @ExcelProperty(value = "出行结束时间")
    private String endTime;
    @ExcelProperty(value = "出行时长")
    private Integer totalTime;
    @ExcelProperty(value = "出行方式")
    private String mode;
    @ExcelProperty(value = "碳排放")
    private Double carbon;
    @ExcelProperty(value = "出行详情")
    private String description;
}
