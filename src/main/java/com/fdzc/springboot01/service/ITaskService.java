package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.vo.HotTaskVo;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;
import com.fdzc.springboot01.entity.vo.TaskVo;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ITaskService {

    PageDTO<Task> findAllTask(int page, int pagesize, Integer id, String name, String description);

    Task findById(Integer id);

    Integer addOneTask(Task task);

    Integer updateOneTask(Task task);

    Integer deleteOneTask(Integer id);

    Integer deleteMultipleTask(IdDTO idDTO);

    void downloadTask(HttpServletResponse response);

    List<TaskVo> findUserTasks(Integer id);

    PageDTO<UserTask> findAllUserTask(int page, int pagesize, Integer id, Integer taskId, Integer userId);

    UserTask findUserTask(Integer id);

    Integer addOneUserTask(UserTask userTask);

    Integer updateOneUserTask(UserTask userTask);

    Integer deleteOneUserTask(Integer id);

    Integer deleteMultipleUserTask(IdDTO idDTO);

    void downloadUserTask(HttpServletResponse response);

    List<HotTaskVo> findHotTask(Integer num);

    Long countNum();

}
