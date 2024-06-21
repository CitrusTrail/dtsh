package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.Chat;
import com.fdzc.springboot01.entity.Group;
import com.fdzc.springboot01.entity.UserGroup;
import com.fdzc.springboot01.service.impl.GroupService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/groups")
public class GroupController {

    @Resource
    GroupService service;

    @GetMapping
    public Result findAllGroup(int page,int pagesize,Integer id,String name,String description) {
        return Result.success(service.findAllGroup(page,pagesize,id,name,description));
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

    @GetMapping("/userGroup")
    public Result findAllUserGroup() {
        return Result.success(service.findAllUserGroup());
    }

    @GetMapping("/userGroup/{id}")
    public Result findUserGroup(@PathVariable Integer id) {
        return Result.success(service.findUserGroup(id));
    }

    @GetMapping("/groupUser/{id}")
    public Result findGroupUser(@PathVariable Integer id) {
        return Result.success(service.findGroupUser(id));
    }

    @PostMapping("/userGroup")
    public Result addOneUserGroup(@RequestBody UserGroup userGroup) {
        return Result.success(service.addOneUserGroup(userGroup));
    }

    @PutMapping("/userGroup")
    public Result updateOneUserGroup(@RequestBody UserGroup userGroup) {
        return Result.success(service.updateOneUserGroup(userGroup));
    }

    @DeleteMapping("/userGroup")
    public Result deleteOneUserGroup(@RequestBody UserGroup userGroup) {
        return Result.success(service.deleteOneUserGroup(userGroup));
    }

    @GetMapping("/chat")
    public Result findAllChat() {
        return Result.success(service.findAllChat());
    }

    @PostMapping("/chat")
    public Result addOneChat(@RequestBody Chat chat) {
        return Result.success(service.addOneChat(chat));
    }

    @PutMapping("/chat")
    public Result updateOneChat(@RequestBody Chat chat) {
        return Result.success(service.updateOneChat(chat));
    }

    @DeleteMapping("/chat/{id}")
    public Result deleteOneChat(@PathVariable Integer id) {
        return Result.success(service.deleteOneChat(id));
    }

    @PostMapping("/multiple")
    public Result deleteMultipleGroup(@RequestBody IdDTO idDTO) {
        return Result.success(service.deleteMultipleGroup(idDTO));
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        service.download(response);
    }

    @GetMapping("/hot")
    public Result findHotGroup(Integer num) {
        return Result.success(service.findHotGroup(num));
    }

    @GetMapping("/num")
    public Result countNum() {
        return Result.success(service.countNum());
    }
}
