package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

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

    public Integer addOneUser(User user) {
        return userMapper.insert(user);
    }

    public Integer updateOneUser(User user) {
        return userMapper.updateById(user);
    }

    public Integer deleteOneUser(Integer id) {
        return userMapper.deleteById(id);
    }

    public Integer deleteMultipleUser(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneUser(id);
        }
        return res;
    }

}
