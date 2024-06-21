package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTaskMapper extends BaseMapper<UserTask> {

    @Select("select * from user_task where user_id=#{userId} and task_id=#{taskId} limit 1")
    UserTask selectUserTaskById(UserTask userTask);

    List<Task> selectUserTask(Integer id);

}
