package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.service.UserService;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    private Logger logger = Logger.getLogger(UserController.class);

    /**
     * 查询所有用户数据
     */
    @GetMapping
    public List<User> findAll() {
        return userService.findAllUser();
    }

    /**
     * 添加单个用户数据
     */
    @PostMapping
    public String addOne(@RequestBody User user) {
        logger.info("前端传来的参数为：" + user);
        return userService.addOneUser(user);
    }

    /**
     * 修改单个用户数据
     */
    @PutMapping
    public String updateOne(@RequestBody User user) {
        logger.info("前端传来的参数为：" + user);
        return userService.updateOneUser(user);
    }

    /**
     * 删除单个用户数据
     */
    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Integer id) {
        logger.info("前端传来的参数为：" + id);
        return userService.deleteOneUser(id);
    }

}
