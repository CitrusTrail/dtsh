package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;
import com.fdzc.springboot01.mapper.TaskMapper;
import com.fdzc.springboot01.mapper.UserTaskMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TaskService {

    @Resource
    TaskMapper taskMapper;

    @Resource
    UserTaskMapper userTaskMapper;

    public List<Task> findAllTask() {
        return taskMapper.selectList(null);
    }

    public List<UserTask> findAllUserTask() {
        return userTaskMapper.selectList(null);
    }

    public String addOneTask(Task task) {
        Integer res = taskMapper.insert(task);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String addOneUserTask(UserTask userTask) {
        Integer res = userTaskMapper.insert(userTask);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneTask(Task task) {
        Integer res = taskMapper.updateById(task);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String updateOneUserTask(UserTask userTask) {
        Integer res = userTaskMapper.updateById(userTask);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneTask(Integer id) {
        Integer res = taskMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    public String deleteOneUserTask(UserTask userTask) {
        Integer res = userTaskMapper.deleteById(userTask);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

}
