package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.entity.dto.UserDTO;
import com.fdzc.springboot01.service.impl.UserService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/users")
public class UserController {

    @Resource
    UserService userService;

    @GetMapping
    public Result findAll(int page,int pagesize,Integer id,String name) {
        return Result.success(userService.findAllUser(page,pagesize,id,name));
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

    @PostMapping("/multiple")
    public Result deleteMultipleUser(@RequestBody IdDTO idDTO) {
        return Result.success(userService.deleteMultipleUser(idDTO));
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        userService.download(response);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        return Result.success(userService.login(userDTO));
    }

    @PostMapping("/register")
    public Result register(@RequestBody UserDTO userDTO) {
        return Result.success(userService.register(userDTO));
    }
}
