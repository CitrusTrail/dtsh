package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.Travel;
import com.fdzc.springboot01.service.impl.TravelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Resource
    TravelService service;

    @GetMapping
    public Result findAllTravel(int page,int pagesize,Integer id,Integer userId,String mode) {
        return Result.success(service.findAllTravel(page,pagesize,id,userId,mode));
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

    @PostMapping("/multiple")
    public Result deleteMultipleTravel(@RequestBody IdDTO idDTO) {
        return Result.success(service.deleteMultipleTravel(idDTO));
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        service.download(response);
    }

    @GetMapping("/mode")
    public Result findAllMode() {
        return Result.success(service.findAllMode());
    }
}
