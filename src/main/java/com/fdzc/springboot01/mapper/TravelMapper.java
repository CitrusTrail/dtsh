package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.vo.ModeVo;
import com.fdzc.springboot01.entity.Travel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface TravelMapper extends BaseMapper<Travel> {

    List<Travel> selectAllTravel(@Param("id") Integer id, @Param("userId") Integer userId, @Param("mode") String mode);

    @Select("select mode as name,count(id) as value from travel group by mode")
    List<ModeVo> selectAllMode();
}
