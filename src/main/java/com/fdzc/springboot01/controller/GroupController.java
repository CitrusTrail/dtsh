package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.Chat;
import com.fdzc.springboot01.entity.Group;
import com.fdzc.springboot01.entity.UserGroup;
import com.fdzc.springboot01.service.GroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Resource
    GroupService service;

    @GetMapping
    public Result findAllGroup() {
        return Result.success(service.findAllGroup());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(service.findById(id));
    }

    @PostMapping
    public Result addOneGroup(@RequestBody Group group) {
        return Result.success(service.addOneGroup(group));
    }

    @PutMapping
    public Result updateOneGroup(@RequestBody Group group) {
        return Result.success(service.updateOneGroup(group));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneGroup(@PathVariable Integer id) {
        return Result.success(service.deleteOneGroup(id));
    }

    @GetMapping("/userGroups")
    public Result findAllUserGroup() {
        return Result.success(service.findAllUserGroup());
    }

    @PostMapping("/userGroups")
    public Result addOneUserGroup(@RequestBody UserGroup userGroup) {
        return Result.success(service.addOneUserGroup(userGroup));
    }

    @PutMapping("/userGroups")
    public Result updateOneUserGroup(@RequestBody UserGroup userGroup) {
        return Result.success(service.updateOneUserGroup(userGroup));
    }

    @DeleteMapping("/userGroups")
    public Result deleteOneUserGroup(@RequestBody UserGroup userGroup) {
        return Result.success(service.deleteOneUserGroup(userGroup));
    }

    @GetMapping("/chats")
    public Result findAllChat() {
        return Result.success(service.findAllChat());
    }

    @PostMapping("/chats")
    public Result addOneChat(@RequestBody Chat chat) {
        return Result.success(service.addOneChat(chat));
    }

    @PutMapping("/chats")
    public Result updateOneChat(@RequestBody Chat chat) {
        return Result.success(service.updateOneChat(chat));
    }

    @DeleteMapping("/chats/{id}")
    public Result deleteOneChat(@PathVariable Integer id) {
        return Result.success(service.deleteOneChat(id));
    }

}
