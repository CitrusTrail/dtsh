package com.fdzc.springboot01.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.vo.HotTaskVo;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;
import com.fdzc.springboot01.entity.vo.TaskVo;
import com.fdzc.springboot01.mapper.TaskMapper;
import com.fdzc.springboot01.mapper.UserMapper;
import com.fdzc.springboot01.mapper.UserTaskMapper;
import com.fdzc.springboot01.service.ITaskService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService implements ITaskService {

    @Resource
    TaskMapper taskMapper;

    @Resource
    UserTaskMapper userTaskMapper;

    @Resource
    UserMapper userMapper;

    public PageDTO<Task> findAllTask(int page, int pagesize, Integer id, String name, String description) {
        int offset = (page - 1) * pagesize;
        PageDTO<Task> pageDTO = new PageDTO<>();
        List<Task> tasks = taskMapper.selectAllTask(id, name, description);
        pageDTO.setRecords(tasks.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(tasks.size());
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

    public Integer deleteMultipleTask(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneTask(id);
        }
        return res;
    }

    public void downloadTask(HttpServletResponse response) {
        List<Task> list = taskMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),Task.class).autoCloseStream(Boolean.FALSE).sheet("任务列表").doWrite(list);
        } catch (Exception e) {
        }
    }

    public List<TaskVo> findUserTasks(Integer id) {
        return userTaskMapper.selectUserTasks(id);
    }

    public PageDTO<UserTask> findAllUserTask(int page, int pagesize, Integer id, Integer taskId, Integer userId) {
        int offset = (page - 1) * pagesize;
        PageDTO<UserTask> pageDTO = new PageDTO<>();
        List<UserTask> userTasks = userTaskMapper.selectAllUserTask(id, taskId, userId);
        pageDTO.setRecords(userTasks.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(userTasks.size());
        return pageDTO;
    }

    public UserTask findUserTask(Integer id) {
        return userTaskMapper.selectById(id);
    }

    private UserTask findUserTask(UserTask userTask) {
        return userTaskMapper.selectUserTask(userTask);
    }

    public Integer addOneUserTask(UserTask userTask) {
        if(findUserTask(userTask) == null) {
            return userTaskMapper.insert(userTask);
        } else {
            return -1;
        }
    }

    public Integer updateOneUserTask(UserTask userTask) {
        String oldStatus = userTaskMapper.selectById(userTask.getId()).getStatus();
        String newStatus = userTask.getStatus();
        int res =  userTaskMapper.updateById(userTask);
        if(res == 1) {
            User user = userMapper.selectById(userTask.getUserId());
            Task task = taskMapper.selectById(userTask.getTaskId());
            if(oldStatus==null||!oldStatus.equals(newStatus)){
                if(newStatus.equals("通过")){
                    user.setPoint(user.getPoint()+task.getPoint());
                }else if(newStatus.equals("不通过")){
                    user.setPoint(user.getPoint()-task.getPoint());
                }
            }
            userMapper.updateById(user);
        }
        return res;
    }

    public Integer deleteOneUserTask(Integer id) {
        return userTaskMapper.deleteById(id);
    }

    public Integer deleteMultipleUserTask(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneUserTask(id);
        }
        return res;
    }

    public void downloadUserTask(HttpServletResponse response) {
        List<UserTask> list = userTaskMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),UserTask.class).autoCloseStream(Boolean.FALSE).sheet("任务列表").doWrite(list);
        } catch (Exception e) {
        }
    }

    public List<HotTaskVo> findHotTask(Integer num) {
        return taskMapper.selectHotTask(num);
    }

    public Long countNum() {
        return taskMapper.selectCount(null);
    }

}
