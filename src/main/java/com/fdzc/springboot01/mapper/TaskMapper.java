package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.vo.HotTaskVo;
import com.fdzc.springboot01.entity.Task;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface TaskMapper extends BaseMapper<Task> {

    List<Task> selectAllTask(@Param("id") Integer id,@Param("name") String name,@Param("description") String description);

    List<HotTaskVo> selectHotTask(Integer num);

}
