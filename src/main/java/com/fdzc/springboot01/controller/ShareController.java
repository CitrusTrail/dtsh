package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.Share;
import com.fdzc.springboot01.service.ShareService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Resource
    ShareService service;

    @GetMapping
    public Result findAllShare() {
        return Result.success(service.findAllShare());
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(service.findById(id));
    }

    @PostMapping
    public Result addOneShare(@RequestBody Share share) {
        return Result.success(service.addOneShare(share));
    }

    @PutMapping
    public Result updateOneShare(@RequestBody Share share) {
        return Result.success(service.updateOneShare(share));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneShare(@PathVariable Integer id) {
        return Result.success(service.deleteOneShare(id));
    }

}
