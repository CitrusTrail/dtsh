package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.common.dto.IdDTO;
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

    public PageDTO<Goods> findAllGoods(int page, int pagesize) {
        PageDTO<Goods> pageDTO = new PageDTO<>();
        Page<Goods> taskPage = goodsMapper.selectPage(new Page<>(page, pagesize), null);
        pageDTO.setRecords(taskPage.getRecords());
        pageDTO.setTotal(taskPage.getTotal());
        return pageDTO;
    }

    public Goods findById(Integer id) {
        return goodsMapper.selectById(id);
    }

    public Integer addOneGoods(Goods goods) {
        return goodsMapper.insert(goods);
    }

    public Integer updateOneGoods(Goods goods) {
        return goodsMapper.updateById(goods);
    }

    public Integer deleteOneGoods(Integer id) {
        return goodsMapper.deleteById(id);
    }

    public List<Buy> findAllBuy() {
        return buyMapper.selectList(null);
    }

    public Integer addOneBuy(Buy buy) {
        return buyMapper.insert(buy);
    }

    public Integer updateOneBuy(Buy buy) {
        return buyMapper.updateById(buy);
    }

    public Integer deleteOneBuy(Integer id) {
        return buyMapper.deleteById(id);
    }

    public Integer deleteMultipleGoods(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneGoods(id);
        }
        return res;
    }

}
