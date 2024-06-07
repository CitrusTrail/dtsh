package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
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
    public Result findAll() {
        return Result.success(userService.findAllUser());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(userService.findById(id));
    }

    @PostMapping
    public Result addOne(@RequestBody User user) {
        return Result.success(userService.addOneUser(user));
    }

    @PutMapping
    public Result updateOne(@RequestBody User user) {
        return Result.success(userService.updateOneUser(user));
    }

    @DeleteMapping("/{id}")
    public Result deleteOne(@PathVariable Integer id) {
        return Result.success(userService.deleteOneUser(id));
    }

}
