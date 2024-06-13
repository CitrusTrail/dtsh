package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.common.vo.HotGroupVo;
import com.fdzc.springboot01.entity.Group;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface GroupMapper extends BaseMapper<Group> {

    List<Group> selectAllGroup(@Param("id") Integer id, @Param("name") String name, @Param("description") String description);

    List<HotGroupVo> selectHotGroup(Integer num);

}
