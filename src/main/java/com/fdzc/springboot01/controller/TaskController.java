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

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(service.findById(id));
    }

    @PostMapping
    public Result addOneTask(@RequestBody Task task) {
        return Result.success(service.addOneTask(task));
    }

    @PutMapping
    public Result updateOneTask(@RequestBody Task task) {
        return Result.success(service.updateOneTask(task));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneTask(@PathVariable Integer id) {
        return Result.success(service.deleteOneTask(id));
    }

    @GetMapping("/userTask")
    public Result findAllUserTask() {
        return Result.success(service.findAllUserTask());
    }

    @PostMapping("/userTask")
    public Result addOneUserTask(@RequestBody UserTask userTask) {
        return Result.success(service.addOneUserTask(userTask));
    }

    @PutMapping("/userTask")
    public Result updateOneUserTask(@RequestBody UserTask userTask) {
        return Result.success(service.updateOneUserTask(userTask));
    }

    @DeleteMapping("/userTask")
    public Result deleteOneUserTask(@RequestBody UserTask userTask) {
        return Result.success(service.deleteOneUserTask(userTask));
    }

}
