package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.entity.UserTask;
import com.fdzc.springboot01.service.impl.TaskService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/tasks")
public class TaskController {

    @Resource
    TaskService service;

    @GetMapping
    public Result findAllTask(int page,int pagesize,Integer id,String name,String description) {
        return Result.success(service.findAllTask(page,pagesize,id,name,description));
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

    @PostMapping("/multiple")
    public Result deleteMultipleTask(@RequestBody IdDTO idDTO) {
        return Result.success(service.deleteMultipleTask(idDTO));
    }

    @GetMapping("/download")
    public void downloadTask(HttpServletResponse response) {
        service.downloadTask(response);
    }


    @GetMapping("/userTask/users/{id}")
    public Result findUserTasks(@PathVariable Integer id) {
        return Result.success(service.findUserTasks(id));
    }

    @GetMapping("/userTask")
    public Result findAllUserTask(int page,int pagesize,Integer id,Integer taskId,Integer userId) {
        return Result.success(service.findAllUserTask(page,pagesize,id,taskId,userId));
    }

    @GetMapping("/userTask/{id}")
    public Result findUserTask(@PathVariable Integer id) {
        return Result.success(service.findUserTask(id));
    }

    @PostMapping("/userTask")
    public Result addOneUserTask(@RequestBody UserTask userTask) {
        return Result.success(service.addOneUserTask(userTask));
    }

    @PutMapping("/userTask")
    public Result updateOneUserTask(@RequestBody UserTask userTask) {
        return Result.success(service.updateOneUserTask(userTask));
    }

    @DeleteMapping("/userTask/{id}")
    public Result deleteOneUserTask(@PathVariable Integer id) {
        return Result.success(service.deleteOneUserTask(id));
    }

    @PostMapping("/userTask/multiple")
    public Result deleteMultipleUserTask(@RequestBody IdDTO idDTO) {
        return Result.success(service.deleteMultipleUserTask(idDTO));
    }

    @GetMapping("/userTask/download")
    public void downloadUserTask(HttpServletResponse response) {
        service.downloadUserTask(response);
    }

    @GetMapping("/hot")
    public Result findHotTask(Integer num) {
        return Result.success(service.findHotTask(num));
    }

    @GetMapping("/num")
    public Result countNum() {
        return Result.success(service.countNum());
    }
}
