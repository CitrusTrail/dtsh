package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.common.dto.IdDTO;
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

    public Integer addOneTask(Task task) {
        return taskMapper.insert(task);
    }

    public Integer updateOneTask(Task task) {
        return taskMapper.updateById(task);
    }

    public Integer deleteOneTask(Integer id) {
        return taskMapper.deleteById(id);
    }

    public List<UserTask> findAllUserTask() {
        return userTaskMapper.selectList(null);
    }

    public Integer addOneUserTask(UserTask userTask) {
        return userTaskMapper.insert(userTask);
    }

    public Integer updateOneUserTask(UserTask userTask) {
        return userTaskMapper.updateById(userTask);
    }

    public Integer deleteOneUserTask(UserTask userTask) {
        return userTaskMapper.deleteById(userTask);
    }

    public Integer deleteMultipleTask(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneTask(id);
        }
        return res;
    }

    public List<HotTaskVo> findHotTask(Integer num) {
        return taskMapper.selectHotTask(num);
    }

}
