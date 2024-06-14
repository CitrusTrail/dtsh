package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public List<User> findAllUser() {
        return userMapper.selectList(null);
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
