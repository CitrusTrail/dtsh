package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Constants;
import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.common.dto.UserDTO;
import com.fdzc.springboot01.entity.Admin;
import com.fdzc.springboot01.service.AdminService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/admins")
public class AdminController {

    @Resource
    AdminService adminService;

    @GetMapping
    public List<Admin> findAllAdmin() {
        return adminService.findAllAdmin();
    }

    @PostMapping
    public String addOneAdmin(@RequestBody Admin admin) {
        return adminService.addOneAdmin(admin);
    }

    @PutMapping
    public String updateOneAdmin(@RequestBody Admin admin) {
        return adminService.updateOneAdmin(admin);
    }

    @DeleteMapping("/{id}")
    public String deleteOneAdmin(@PathVariable Integer id) {
        return adminService.deleteOneAdmin(id);
    }

    @PostMapping("/login")
    public Result login(@RequestBody UserDTO userDTO) {
        UserDTO dto = adminService.login(userDTO);
        return Result.success(dto);
    }

}
