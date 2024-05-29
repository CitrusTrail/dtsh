package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.UserCarbon;
import com.fdzc.springboot01.service.CarbonService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/carbons")
public class CarbonController {

    @Resource
    CarbonService service;

    @GetMapping
    public List<UserCarbon> findAllUserCarbon() {
        return service.findAllUserCarbon();
    }

    @PostMapping
    public String addOneUserCarbon(UserCarbon userCarbon) {
        return service.addOneUserCarbon(userCarbon);
    }

    @PutMapping
    public String updateOneUserCarbon(UserCarbon userCarbon) {
        return service.updateOneUserCarbon(userCarbon);
    }

    @DeleteMapping("/{id}")
    public String deleteOneUserCarbon(Integer id) {
        return service.deleteOneUserCarbon(id);
    }

}
