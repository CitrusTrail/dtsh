package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.common.dto.UserDTO;
import com.fdzc.springboot01.entity.Admin;
import com.fdzc.springboot01.service.impl.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Resource
    AdminService adminService;

    @GetMapping
    public Result findAllAdmin() {
        return Result.success(adminService.findAllAdmin());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(adminService.findById(id));
    }

    @PostMapping
    public Result addOneAdmin(@RequestBody Admin admin) {
        return Result.success(adminService.addOneAdmin(admin));
    }

    @PutMapping
    public Result updateOneAdmin(@RequestBody Admin admin) {
        return Result.success(adminService.updateOneAdmin(admin));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneAdmin(@PathVariable Integer id) {
        return Result.success(adminService.deleteOneAdmin(id));
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        UserDTO dto = adminService.login(userDTO);
        return Result.success(dto);
    }

}
