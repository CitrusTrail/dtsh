package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.mapper.UserMapper;
import com.fdzc.springboot01.vo.UserVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    /**
     * 查询单个用户数据
     */
    public User findOne(Integer id) {
        return userMapper.selectById(id);
    }

    /**
     * 查询所有用户数据
     */
    public List<User> findAll() {
        return userMapper.selectAll();
    }

    /**
     * 查询用户的部门信息
     */
    public List<UserVo> findUserDept() {
        return userMapper.selectUserDept();
    }

    /**
     * 添加单个用户数据
     */
    public String addOne(User user) {
        user.setPassword(user.getPassword() + "_insert");
        Integer res = userMapper.insert(user);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    /**
     * 添加所有用户数据
     */
    public String addAll(List<User> users) {
        Integer res = 0;
        for (User user : users) {
            user.setPassword(user.getPassword() + "_insert");
            res += userMapper.insert(user);
        }
        return String.format("添加成功：%d，添加失败：%d", res, users.size() - res);
    }

    /**
     * 修改单个用户数据
     */
    public String updateOne(User user) {
        user.setPassword(user.getPassword() + "_update");
        Integer res = userMapper.update(user);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    /**
     * 修改所有用户数据
     */
    public String updateAll(List<User> users) {
        Integer res = 0;
        for (User user : users) {
            user.setPassword(user.getPassword() + "_update");
            res += userMapper.update(user);
        }
        return String.format("修改成功：%d，修改失败：%d", res, users.size() - res);
    }

    /**
     * 删除单个用户数据
     */
    public String deleteOne(Integer id) {
        Integer res = userMapper.delete(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    /**
     * 删除所有用户数据
     */
    public String deleteAll(Integer[] ids) {
        Integer res = 0;
        for (Integer id : ids) {
            res += userMapper.delete(id);
        }
        return String.format("删除成功：%d，删除失败：%d", res, ids.length - res);
    }
}
