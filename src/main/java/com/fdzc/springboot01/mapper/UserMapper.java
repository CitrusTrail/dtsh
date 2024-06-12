package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<User> {

    List<User> selectAllUser(@Param("id") Integer id, @Param("name") String name);

}
