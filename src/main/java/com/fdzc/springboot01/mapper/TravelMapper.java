package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.Travel;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface TravelMapper extends BaseMapper<Travel> {

}
