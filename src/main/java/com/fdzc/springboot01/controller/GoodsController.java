package com.fdzc.springboot01.controller;

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
    GoodsService srvice;

    @GetMapping
    public List<Goods> findAllGoods() {
        return srvice.findAllGoods();
    }

    @GetMapping("/buy")
    public List<Buy> findAllBuy() {
        return srvice.findAllBuy();
    }

    @PostMapping
    public String addOneGoods(Goods goods) {
        return srvice.addOneGoods(goods);
    }

    @PostMapping("/buy")
    public String addOneBuy(Buy buy) {
        return srvice.addOneBuy(buy);
    }

    @PutMapping
    public String updateOneGoods(Goods goods) {
        return srvice.updateOneGoods(goods);
    }

    @PutMapping("/buy")
    public String updateOneBuy(Buy buy) {
        return srvice.updateOneBuy(buy);
    }

    @DeleteMapping("/{id}")
    public String deleteOneGoods(Integer id) {
        return srvice.deleteOneGoods(id);
    }

    @DeleteMapping("/buy/{id}")
    public String deleteOneBuy(Integer id) {
        return srvice.deleteOneBuy(id);
    }

}
