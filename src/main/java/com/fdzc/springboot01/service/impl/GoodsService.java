package com.fdzc.springboot01.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.Buy;
import com.fdzc.springboot01.entity.Goods;
import com.fdzc.springboot01.mapper.BuyMapper;
import com.fdzc.springboot01.mapper.GoodsMapper;
import com.fdzc.springboot01.service.IGoodsService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsService implements IGoodsService {

    @Resource
    GoodsMapper goodsMapper;

    @Resource
    BuyMapper buyMapper;

    public PageDTO<Goods> findAllGoods(int page, int pagesize, Integer id, String name, String description) {
        int offset = (page - 1) * pagesize;
        com.fdzc.springboot01.entity.dto.PageDTO<Goods> pageDTO = new com.fdzc.springboot01.entity.dto.PageDTO<>();
        List<Goods> goods = goodsMapper.selectAllGoods(id, name, description);
        pageDTO.setRecords(goods.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(goods.size());
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

    public void download(HttpServletResponse response) {
        List<Goods> list = goodsMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),Goods.class).autoCloseStream(Boolean.FALSE).sheet("商品列表").doWrite(list);
        } catch (Exception e) {
        }
    }

}
