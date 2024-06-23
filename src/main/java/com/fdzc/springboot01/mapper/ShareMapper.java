package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.Share;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface ShareMapper extends BaseMapper<Share> {
    List<Share> selectAllShare(@Param("id") Integer id, @Param("userId") Integer userId, @Param("content") String content);

    @Select("select * from share where user_id=#{id} order by id desc")
    List<Share> selectUserShare(Integer id);
}
