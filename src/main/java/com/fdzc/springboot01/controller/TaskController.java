package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;
import com.fdzc.springboot01.service.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Resource
    TaskService service;

    @GetMapping("/task")
    public List<Task> findAllTask() {
        return service.findAllTask();
    }

    @GetMapping("/userTask")
    public List<UserTask> findAllUserTask() {
        return service.findAllUserTask();
    }

    @PostMapping("/task")
    public String addOneTask(Task task) {
        return service.addOneTask(task);
    }

    @PostMapping("/userTask")
    public String addOneUserTask(UserTask userTask) {
        return service.addOneUserTask(userTask);
    }

    @PutMapping("/task")
    public String updateOneTask(Task task) {
        return service.updateOneTask(task);
    }

    @PutMapping("/userTask")
    public String updateOneUserTask(UserTask userTask) {
        return service.updateOneUserTask(userTask);
    }

    @DeleteMapping("/task/{id}")
    public String deleteOneTask(Integer id) {
        return service.deleteOneTask(id);
    }

    @DeleteMapping("/userTask/{id}")
    public String deleteOneUserTask(UserTask userTask) {
        return service.deleteOneUserTask(userTask);
    }

}
