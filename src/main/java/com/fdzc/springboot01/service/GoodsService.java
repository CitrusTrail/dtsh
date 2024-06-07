package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.Buy;
import com.fdzc.springboot01.entity.Goods;
import com.fdzc.springboot01.mapper.BuyMapper;
import com.fdzc.springboot01.mapper.GoodsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class GoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Resource
    BuyMapper buyMapper;

    public List<Goods> findAllGoods() {
        return goodsMapper.selectList(null);
    }

    public Goods findById(Integer id) {
        return goodsMapper.selectById(id);
    }

    public String addOneGoods(Goods goods) {
        Integer res = goodsMapper.insert(goods);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneGoods(Goods goods) {
        Integer res = goodsMapper.updateById(goods);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneGoods(Integer id) {
        Integer res = goodsMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    public List<Buy> findAllBuy() {
        return buyMapper.selectList(null);
    }

    public String addOneBuy(Buy buy) {
        Integer res = buyMapper.insert(buy);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneBuy(Buy buy) {
        Integer res = buyMapper.updateById(buy);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneBuy(Integer id) {
        Integer res = buyMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

}
