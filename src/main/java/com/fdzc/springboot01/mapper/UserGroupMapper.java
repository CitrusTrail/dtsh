package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.common.vo.GroupUserVo;
import com.fdzc.springboot01.entity.UserGroup;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface UserGroupMapper extends BaseMapper<UserGroup> {

    public List<GroupUserVo> selectGroupUser(Integer id);

}
