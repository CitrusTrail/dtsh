package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TravelMapper extends BaseMapper<Travel> {
    List<Travel> selectAllTravel(@Param("id") Integer id, @Param("userId") Integer userId, @Param("mode") String mode);
}
