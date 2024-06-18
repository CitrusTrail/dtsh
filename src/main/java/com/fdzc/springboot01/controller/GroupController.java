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

    @PostMapping
    public Result addOneGroup(Group group) {
        return Result.success(service.addOneGroup(group));
    }

    @PutMapping
    public Result updateOneGroup(Group group) {
        return Result.success(service.updateOneGroup(group));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneGroup(Integer id) {
        return Result.success(service.deleteOneGroup(id));
    }

    @GetMapping("/userGroups")
    public Result findAllUserGroup() {
        return Result.success(service.findAllUserGroup());
    }

    @PostMapping("/userGroups")
    public Result addOneUserGroup(UserGroup userGroup) {
        return Result.success(service.addOneUserGroup(userGroup));
    }

    @PutMapping("/userGroups")
    public Result updateOneUserGroup(UserGroup userGroup) {
        return Result.success(service.updateOneUserGroup(userGroup));
    }

    @DeleteMapping("/userGroups/{id}")
    public Result deleteOneUserGroup(Integer id) {
        return Result.success(service.deleteOneUserGroup(id));
    }

    @GetMapping("/chats")
    public Result findAllChat() {
        return Result.success(service.findAllChat());
    }

    @PostMapping("/chats")
    public Result addOneChat(Chat chat) {
        return Result.success(service.addOneChat(chat));
    }

    @PutMapping("/chats")
    public Result updateOneChat(Chat chat) {
        return Result.success(service.updateOneChat(chat));
    }

    @DeleteMapping("/chats/{id}")
    public Result deleteOneChat(Integer id) {
        return Result.success(service.deleteOneChat(id));
    }

}
