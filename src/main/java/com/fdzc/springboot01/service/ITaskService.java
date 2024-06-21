package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.vo.HotTaskVo;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ITaskService {

    PageDTO<Task> findAllTask(int page, int pagesize, Integer id, String name, String description);

    Task findById(Integer id);

    Integer addOneTask(Task task);

    Integer updateOneTask(Task task);

    Integer deleteOneTask(Integer id);

    List<UserTask> findAllUserTask();

    List<Task> findUserTask(Integer id);

    UserTask findUserTaskById(UserTask userTask);

    Integer addOneUserTask(UserTask userTask);

    Integer updateOneUserTask(UserTask userTask);

    Integer deleteOneUserTask(UserTask userTask);

    Integer deleteMultipleTask(IdDTO idDTO);

    void download(HttpServletResponse response);

    List<HotTaskVo> findHotTask(Integer num);

    Long countNum();

}
