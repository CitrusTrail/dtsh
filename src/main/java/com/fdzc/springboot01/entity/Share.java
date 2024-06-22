package com.fdzc.springboot01.entity;

import com.alibaba.excel.annotation.ExcelProperty;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import lombok.Data;
import org.springframework.stereotype.Component;

@Component
@Data
public class Share {
    @TableId(value="id",type= IdType.AUTO)
    @ExcelProperty(value = "分享编号")
    private Integer id;
    @ExcelProperty(value = "用户编号")
    private Integer userId;
    @ExcelProperty(value = "分享内容")
    private String content;
    @ExcelProperty(value = "发送时间")
    private String time;
    @ExcelProperty(value = "点赞数")
    private Integer likes;
}
