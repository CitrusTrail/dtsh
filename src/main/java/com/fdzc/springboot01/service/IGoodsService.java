package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.Buy;
import com.fdzc.springboot01.entity.Goods;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface IGoodsService {

    PageDTO<Goods> findAllGoods(int page, int pagesize, Integer id, String name, String description);

    Goods findById(Integer id);

    Integer addOneGoods(Goods goods);

    Integer updateOneGoods(Goods goods);

    Integer deleteOneGoods(Integer id);

    List<Buy> findAllBuy();

    Integer addOneBuy(Buy buy);

    Integer updateOneBuy(Buy buy);

    Integer deleteOneBuy(Integer id);

    Integer deleteMultipleGoods(IdDTO idDTO);

    void download(HttpServletResponse response);

}
