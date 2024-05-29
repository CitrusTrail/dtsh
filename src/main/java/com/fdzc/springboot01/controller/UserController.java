package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.service.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping
    public List<User> findAll() {
        return userService.findAllUser();
    }

    @PostMapping
    public String addOne(@RequestBody User user) {
        return userService.addOneUser(user);
    }

    @PutMapping
    public String updateOne(@RequestBody User user) {
        return userService.updateOneUser(user);
    }

    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Integer id) {
        return userService.deleteOneUser(id);
    }

}
