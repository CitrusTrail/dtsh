package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.Travel;
import com.fdzc.springboot01.service.TravelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Resource
    TravelService service;

    @GetMapping
    public Result findAllTravel() {
        return Result.success(service.findAllTravel());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(service.findById(id));
    }

    @PostMapping
    public Result addOneTravel(@RequestBody Travel travel) {
        return Result.success(service.addOneTravel(travel));
    }

    @PutMapping
    public Result updateOneTravel(@RequestBody Travel travel) {
        return Result.success(service.updateOneTravel(travel));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneTravel(@PathVariable Integer id) {
        return Result.success(service.deleteOneTravel(id));
    }

}
