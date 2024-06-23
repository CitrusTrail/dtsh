package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class UserTask {
    @TableId(value="id",type= IdType.AUTO)
    @ExcelProperty(value = "编号")
    private Integer id;
    @ExcelProperty(value = "用户编号")
    private Integer userId;
    @ExcelProperty(value = "任务编号")
    private Integer taskId;
    @ExcelProperty(value = "完成时间")
    private String completedTime;
    @ExcelProperty(value = "审核图片")
    private String image;
    @ExcelProperty(value = "审核状态")
    private String status;
}
