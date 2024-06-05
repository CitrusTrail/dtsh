package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
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

    @GetMapping
    public Result findAllTask() {
        return Result.success(service.findAllTask());
    }

    @GetMapping("/userTask")
    public Result findAllUserTask() {
        return Result.success(service.findAllUserTask());
    }

    @PostMapping
    public Result addOneTask(Task task) {
        return Result.success(service.addOneTask(task));
    }

    @PostMapping("/userTask")
    public Result addOneUserTask(UserTask userTask) {
        return Result.success(service.addOneUserTask(userTask));
    }

    @PutMapping
    public Result updateOneTask(Task task) {
        return Result.success(service.updateOneTask(task));
    }

    @PutMapping("/userTask")
    public Result updateOneUserTask(UserTask userTask) {
        return Result.success(service.updateOneUserTask(userTask));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneTask(Integer id) {
        return Result.success(service.deleteOneTask(id));
    }

    @DeleteMapping("/userTask/{id}")
    public Result deleteOneUserTask(UserTask userTask) {
        return Result.success(service.deleteOneUserTask(userTask));
    }

}
