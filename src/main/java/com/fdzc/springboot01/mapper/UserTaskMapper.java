package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserTaskMapper extends BaseMapper<UserTask> {

    List<UserTask> selectAllUserTask(@Param("id") Integer id, @Param("taskId") Integer taskId, @Param("userId") Integer userId);

    @Select("select * from user_task where user_id=#{userId} and task_id=#{taskId} limit 1")
    UserTask selectUserTask(UserTask userTask);

    List<Task> selectUserTasks(Integer id);

}
