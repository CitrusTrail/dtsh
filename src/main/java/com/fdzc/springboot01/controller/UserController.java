package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.service.UserService;
import com.fdzc.springboot01.vo.UserVo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    private Logger logger = Logger.getLogger(UserController.class);

    /**
     * 查询单个用户数据
     */
    @GetMapping("/{id}")
    public User findOne(@PathVariable Integer id) {
        logger.info("前端传来的参数为：" + id);
        return userService.findOne(id);
    }

    /**
     * 查询所有用户数据
     */
    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    /**
     * 查询用户的部门信息
     */
    @GetMapping("/userDept")
    public List<UserVo> findUserDept() {
        return userService.findUserDept();
    }

    /**
     * 添加单个用户数据
     */
    @PostMapping
    public String addOne(@RequestBody User user) {
        logger.info("前端传来的参数为：" + user);
        return userService.addOne(user);
    }

    /**
     * 添加所有用户数据
     */
    @PostMapping("/all")
    public String addAll(@RequestBody List<User> users) {
        logger.info("前端传来的参数为：" + users);
        return userService.addAll(users);
    }

    /**
     * 修改单个用户数据
     */
    @PutMapping
    public String updateOne(@RequestBody User user) {
        logger.info("前端传来的参数为：" + user);
        return userService.updateOne(user);
    }

    /**
     * 修改所有用户数据
     */
    @PutMapping("/all")
    public String updateAll(@RequestBody List<User> users) {
        logger.info("前端传来的参数为：" + users);
        return userService.updateAll(users);
    }

    /**
     * 删除单个用户数据
     */
    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Integer id) {
        logger.info("前端传来的参数为：" + id);
        return userService.deleteOne(id);
    }

    /**
     * 删除所有用户数据
     */
    @DeleteMapping("/all")
    public String deleteAll(@RequestBody Integer[] ids) {
        logger.info("前端传来的参数为：" + Arrays.toString(ids));
        return userService.deleteAll(ids);
    }

}
