package com.fdzc.springboot01.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.Buy;
import com.fdzc.springboot01.entity.Goods;
import com.fdzc.springboot01.entity.vo.BuyVo;
import com.fdzc.springboot01.mapper.BuyMapper;
import com.fdzc.springboot01.mapper.GoodsMapper;
import com.fdzc.springboot01.mapper.UserMapper;
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

    @Resource
    UserMapper userMapper;

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

    public Integer deleteMultipleGoods(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneGoods(id);
        }
        return res;
    }

    public void downloadGoods(HttpServletResponse response) {
        List<Goods> list = goodsMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),Goods.class).autoCloseStream(Boolean.FALSE).sheet("商品列表").doWrite(list);
        } catch (Exception e) {
        }
    }

    public PageDTO<Buy> findAllBuy(int page, int pagesize, Integer id, Integer userId, Integer goodsId) {
        int offset = (page - 1) * pagesize;
        PageDTO<Buy> pageDTO = new PageDTO<>();
        List<Buy> buy = buyMapper.selectAllBuy(id, userId, goodsId);
        pageDTO.setRecords(buy.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(buy.size());
        return pageDTO;
    }

    public Buy findBuyById(Integer id) {
        return buyMapper.selectById(id);
    }

    public List<BuyVo> findUserGoods(Integer id) {
        return buyMapper.selectUserGoods(id);
    }

    public Integer addOneBuy(Buy buy) {
        Goods goods = goodsMapper.selectById(buy.getGoodsId());
        if(goods.getStock() < buy.getNum()) {
            return -1;
        }
        User user = userMapper.selectById(buy.getUserId());
        if(user.getPoint() < buy.getPoint()) {
            return -2;
        }
        int res = buyMapper.insert(buy);
        if(res == 1){
            user.setPoint(user.getPoint()-buy.getPoint());
            userMapper.updateById(user);
            goods.setStock(goods.getStock()-buy.getNum());
            goodsMapper.updateById(goods);
        }
        return res;
    }

    public Integer updateOneBuy(Buy buy) {
        return buyMapper.updateById(buy);
    }

    public Integer deleteOneBuy(Integer id) {
        return buyMapper.deleteById(id);
    }

    public Integer deleteMultipleBuy(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneBuy(id);
        }
        return res;
    }

    public void downloadBuy(HttpServletResponse response) {
        List<Buy> list = buyMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),Buy.class).autoCloseStream(Boolean.FALSE).sheet("订单列表").doWrite(list);
        } catch (Exception e) {
        }
    }



}
