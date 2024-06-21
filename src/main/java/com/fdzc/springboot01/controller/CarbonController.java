package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.UserCarbon;
import com.fdzc.springboot01.service.impl.CarbonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/carbons")
public class CarbonController {

    @Resource
    CarbonService service;

    @GetMapping
    public Result findAllUserCarbon() {
        return Result.success(service.findAllUserCarbon());
    }

    @PostMapping
    public Result addOneUserCarbon(UserCarbon userCarbon) {
        return Result.success(service.addOneUserCarbon(userCarbon));
    }

    @PutMapping
    public Result updateOneUserCarbon(UserCarbon userCarbon) {
        return Result.success(service.updateOneUserCarbon(userCarbon));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneUserCarbon(Integer id) {
        return Result.success(service.deleteOneUserCarbon(id));
    }

}
