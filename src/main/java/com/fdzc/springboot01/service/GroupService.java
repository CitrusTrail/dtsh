package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.common.dto.IdDTO;
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

    public PageDTO<Group> findAllGroup(int page,int pagesize) {
        PageDTO<Group> pageDTO = new PageDTO<>();
        Page<Group> taskPage = groupMapper.selectPage(new Page<>(page, pagesize), null);
        pageDTO.setRecords(taskPage.getRecords());
        pageDTO.setTotal(taskPage.getTotal());
        return pageDTO;
    }

    public Group findById(Integer id) {
        return groupMapper.selectById(id);
    }

    public Integer addOneGroup(Group group) {
        return groupMapper.insert(group);
    }

    public Integer updateOneGroup(Group group) {
        return groupMapper.updateById(group);
    }

    public Integer deleteOneGroup(Integer id) {
        return groupMapper.deleteById(id);
    }

    public List<UserGroup> findAllUserGroup() {
        return userGroupMapper.selectList(null);
    }

    public Integer addOneUserGroup(UserGroup userGroup) {
        return userGroupMapper.insert(userGroup);
    }

    public Integer updateOneUserGroup(UserGroup userGroup) {
        return userGroupMapper.updateById(userGroup);
    }

    public Integer deleteOneUserGroup(UserGroup userGroup) {
        return userGroupMapper.deleteById(userGroup);
    }

    public List<Chat> findAllChat() {
        return chatMapper.selectList(null);
    }

    public Integer addOneChat(Chat chat) {
        return chatMapper.insert(chat);
    }

    public Integer updateOneChat(Chat chat) {
        return chatMapper.updateById(chat);
    }

    public Integer deleteOneChat(Integer id) {
        return chatMapper.deleteById(id);
    }

    public Integer deleteMultipleGroup(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneGroup(id);
        }
        return res;
    }

}
