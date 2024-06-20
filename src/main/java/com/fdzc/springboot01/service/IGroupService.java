package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.vo.GroupUserVo;
import com.fdzc.springboot01.entity.vo.HotGroupVo;
import com.fdzc.springboot01.entity.Chat;
import com.fdzc.springboot01.entity.Group;
import com.fdzc.springboot01.entity.UserGroup;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IGroupService {

    PageDTO<Group> findAllGroup(int page, int pagesize, Integer id, String name, String description);

    Group findById(Integer id);

    Integer addOneGroup(Group group);

    Integer updateOneGroup(Group group);

    Integer deleteOneGroup(Integer id);

    List<UserGroup> findAllUserGroup();

    List<GroupUserVo> findGroupUser(Integer id);

    Integer addOneUserGroup(UserGroup userGroup);

    Integer updateOneUserGroup(UserGroup userGroup);

    Integer deleteOneUserGroup(UserGroup userGroup);

    List<Chat> findAllChat();

    Integer addOneChat(Chat chat);

    Integer updateOneChat(Chat chat);

    Integer deleteOneChat(Integer id);

    Integer deleteMultipleGroup(IdDTO idDTO);

    void download(HttpServletResponse response);

    List<HotGroupVo> findHotGroup(Integer num);

    Long countNum();

}
