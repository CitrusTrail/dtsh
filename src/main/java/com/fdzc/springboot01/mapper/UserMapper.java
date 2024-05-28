package com.fdzc.springboot01.mapper;

import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.vo.UserVo;
import org.apache.ibatis.annotations.*;

import java.util.List;


@Mapper
public interface UserMapper {

    List<User> selectAll();

    User selectById(Integer id);

    List<UserVo> selectUserDept();

    Integer insert(User user);

    Integer update(User user);

    Integer delete(Integer id);

}
