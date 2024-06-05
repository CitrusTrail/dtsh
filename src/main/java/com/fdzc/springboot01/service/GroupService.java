package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.Chat;
import com.fdzc.springboot01.entity.Group;
import com.fdzc.springboot01.entity.UserGroup;
import com.fdzc.springboot01.mapper.ChatMapper;
import com.fdzc.springboot01.mapper.GroupMapper;
import com.fdzc.springboot01.mapper.UserGroupMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GroupService {

    @Resource
    ChatMapper chatMapper;

    @Resource
    GroupMapper groupMapper;

    @Resource
    UserGroupMapper userGroupMapper;

    public List<Chat> findAllChat() {
        return chatMapper.selectList(null);
    }

    public List<Group> findAllGroup() {
        return groupMapper.selectList(null);
    }

    public List<UserGroup> findAllUserGroup() {
        return userGroupMapper.selectList(null);
    }

    public String addOneChat(Chat chat) {
        Integer res = chatMapper.insert(chat);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String addOneGroup(Group group) {
        Integer res = groupMapper.insert(group);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String addOneUserGroup(UserGroup userGroup) {
        Integer res = userGroupMapper.insert(userGroup);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneChat(Chat chat) {
        Integer res = chatMapper.updateById(chat);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String updateOneGroup(Group group) {
        Integer res = groupMapper.updateById(group);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String updateOneUserGroup(UserGroup userGroup) {
        Integer res = userGroupMapper.updateById(userGroup);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneChat(Integer id) {
        Integer res = chatMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    public String deleteOneGroup(Integer id) {
        Integer res = groupMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    public String deleteOneUserGroup(Integer id) {
        Integer res = userGroupMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

}