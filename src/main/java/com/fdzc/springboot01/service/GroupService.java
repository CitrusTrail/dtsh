package com.fdzc.springboot01.service;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.common.dto.PageDTO;
import com.fdzc.springboot01.common.vo.GroupUserVo;
import com.fdzc.springboot01.entity.Chat;
import com.fdzc.springboot01.entity.Group;
import com.fdzc.springboot01.entity.UserGroup;
import com.fdzc.springboot01.mapper.ChatMapper;
import com.fdzc.springboot01.mapper.GroupMapper;
import com.fdzc.springboot01.mapper.UserGroupMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GroupService {

    @Resource
    ChatMapper chatMapper;

    @Resource
    GroupMapper groupMapper;

    @Resource
    UserGroupMapper userGroupMapper;

    public PageDTO<Group> findAllGroup(int page,int pagesize, Integer id, String name, String description) {
        int offset = (page - 1) * pagesize;
        PageDTO<Group> pageDTO = new PageDTO<>();
        List<Group> groups = groupMapper.selectAllGroup(id, name, description);
        pageDTO.setRecords(groups.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(groups.size());
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

    public List<GroupUserVo> findGroupUser(Integer id) {
        return userGroupMapper.selectGroupUser(id);
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

    public void download(HttpServletResponse response) {
        List<Group> list = groupMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),Group.class).autoCloseStream(Boolean.FALSE).sheet("小组列表").doWrite(list);
        } catch (Exception e) {
        }
    }

}
