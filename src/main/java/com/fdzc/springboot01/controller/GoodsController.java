package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.Buy;
import com.fdzc.springboot01.entity.Goods;
import com.fdzc.springboot01.service.impl.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    GoodsService service;

    @GetMapping
    public Result findAllGoods(int page,int pagesize,Integer id,String name,String description) {
        return Result.success(service.findAllGoods(page,pagesize,id,name,description));
    }

    @GetMapping("/{id}")
    public Result findById(@PathVariable Integer id) {
        return Result.success(service.findById(id));
    }

    @PostMapping
    public Result addOneGoods(@RequestBody Goods goods) {
        return Result.success(service.addOneGoods(goods));
    }

    @PutMapping
    public Result updateOneGoods(@RequestBody Goods goods) {
        return Result.success(service.updateOneGoods(goods));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneGoods(@PathVariable Integer id) {
        return Result.success(service.deleteOneGoods(id));
    }

    @GetMapping("/buy")
    public Result findAllBuy() {
        return Result.success(service.findAllBuy());
    }

    @PostMapping("/buy")
    public Result addOneBuy(@RequestBody Buy buy) {
        return Result.success(service.addOneBuy(buy));
    }

    @PutMapping("/buy")
    public Result updateOneBuy(@RequestBody Buy buy) {
        return Result.success(service.updateOneBuy(buy));
    }

    @DeleteMapping("/buy/{id}")
    public Result deleteOneBuy(@PathVariable Integer id) {
        return Result.success(service.deleteOneBuy(id));
    }

    @PostMapping("/multiple")
    public Result deleteMultipleGoods(@RequestBody IdDTO idDTO) {
        return Result.success(service.deleteMultipleGoods(idDTO));
    }

    @GetMapping("/download")
    public void download(HttpServletResponse response) {
        service.download(response);
    }

    @GetMapping("/buy/{id}")
    public Result findUserGoods(@PathVariable Integer id) {
        return Result.success(service.findUserGoods(id));
    }

}
