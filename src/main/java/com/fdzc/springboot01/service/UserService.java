package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public PageDTO<User> findAllUser(int page, int pagesize) {
        PageDTO<User> pageDTO = new PageDTO<>();
        Page<User> taskPage = userMapper.selectPage(new Page<>(page, pagesize), null);
        pageDTO.setRecords(taskPage.getRecords());
        pageDTO.setTotal(taskPage.getTotal());
        return pageDTO;
    }

    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    public String addOneUser(User user) {
        Integer res = userMapper.insert(user);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneUser(User user) {
        Integer res = userMapper.updateById(user);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneUser(Integer id) {
        Integer res = userMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

}
