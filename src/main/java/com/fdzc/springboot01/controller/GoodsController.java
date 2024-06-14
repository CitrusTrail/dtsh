package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.entity.Buy;
import com.fdzc.springboot01.entity.Goods;
import com.fdzc.springboot01.service.GoodsService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/goods")
public class GoodsController {

    @Resource
    GoodsService service;

    @GetMapping
    public Result findAllGoods() {
        return Result.success(service.findAllGoods());
    }

    @PostMapping
    public Result addOneGoods(Goods goods) {
        return Result.success(service.addOneGoods(goods));
    }

    @PutMapping
    public Result updateOneGoods(Goods goods) {
        return Result.success(service.updateOneGoods(goods));
    }

    @DeleteMapping("/{id}")
    public Result deleteOneGoods(Integer id) {
        return Result.success(service.deleteOneGoods(id));
    }

    @GetMapping("/buy")
    public Result findAllBuy() {
        return Result.success(service.findAllBuy());
    }

    @PostMapping("/buy")
    public Result addOneBuy(Buy buy) {
        return Result.success(service.addOneBuy(buy));
    }

    @PutMapping("/buy")
    public Result updateOneBuy(Buy buy) {
        return Result.success(service.updateOneBuy(buy));
    }

    @DeleteMapping("/buy/{id}")
    public Result deleteOneBuy(Integer id) {
        return Result.success(service.deleteOneBuy(id));
    }

}
