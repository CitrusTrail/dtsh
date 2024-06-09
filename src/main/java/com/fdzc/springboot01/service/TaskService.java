package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.common.vo.HotTaskVo;
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

    public PageDTO<Task> findAllTask(int page, int pagesize) {
        PageDTO<Task> pageDTO = new PageDTO<>();
        Page<Task> taskPage = taskMapper.selectPage(new Page<>(page, pagesize), null);
        pageDTO.setRecords(taskPage.getRecords());
        pageDTO.setTotal(taskPage.getTotal());
        return pageDTO;
    }

    public Task findById(Integer id) {
        return taskMapper.selectById(id);
    }

    public String addOneTask(Task task) {
        Integer res = taskMapper.insert(task);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneTask(Task task) {
        Integer res = taskMapper.updateById(task);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneTask(Integer id) {
        Integer res = taskMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    public List<UserTask> findAllUserTask() {
        return userTaskMapper.selectList(null);
    }

    public String addOneUserTask(UserTask userTask) {
        Integer res = userTaskMapper.insert(userTask);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneUserTask(UserTask userTask) {
        Integer res = userTaskMapper.updateById(userTask);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneUserTask(UserTask userTask) {
        Integer res = userTaskMapper.deleteById(userTask);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    public List<HotTaskVo> findHotTask(Integer num) {
        return taskMapper.selectHotTask(num);
    }

}
