package com.fdzc.springboot01.controller;

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

    @GetMapping("/chats")
    public List<Chat> findAllChat() {
        return service.findAllChat();
    }

    @GetMapping
    public List<Group> findAllGroup() {
        return service.findAllGroup();
    }

    @GetMapping("/userGroups")
    public List<UserGroup> findAllUserGroup() {
        return service.findAllUserGroup();
    }

    @PostMapping("/chats")
    public String addOneChat(Chat chat) {
        return service.addOneChat(chat);
    }

    @PostMapping
    public String addOneGroup(Group group) {
        return service.addOneGroup(group);
    }

    @PostMapping("/userGroups")
    public String addOneUserGroup(UserGroup userGroup) {
        return service.addOneUserGroup(userGroup);
    }

    @PutMapping("/chats")
    public String updateOneChat(Chat chat) {
        return service.updateOneChat(chat);
    }

    @PutMapping
    public String updateOneGroup(Group group) {
        return service.updateOneGroup(group);
    }

    @PutMapping("/userGroups")
    public String updateOneUserGroup(UserGroup userGroup) {
        return service.updateOneUserGroup(userGroup);
    }

    @DeleteMapping("/chats/{id}")
    public String deleteOneChat(Integer id) {
        return service.deleteOneChat(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOneGroup(Integer id) {
        return service.deleteOneGroup(id);
    }

    @DeleteMapping("/userGroups/{id}")
    public String deleteOneUserGroup(Integer id) {
        return service.deleteOneUserGroup(id);
    }

}
