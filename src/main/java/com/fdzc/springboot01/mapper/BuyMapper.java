package com.fdzc.springboot01.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.fdzc.springboot01.entity.Buy;
import com.fdzc.springboot01.entity.Goods;
import com.fdzc.springboot01.entity.vo.BuyVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface BuyMapper extends BaseMapper<Buy> {

    List<Buy> selectAllBuy(@Param("id") Integer id, @Param("userId") Integer userId, @Param("goodsId") Integer goodsId);

    List<BuyVo> selectUserGoods(Integer id);
}
