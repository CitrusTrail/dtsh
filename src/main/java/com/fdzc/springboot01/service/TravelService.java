package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.Travel;
import com.fdzc.springboot01.mapper.TravelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class TravelService {

    @Resource
    TravelMapper travelMapper;

    public List<Travel> findAllTravel() {
        return travelMapper.selectList(null);
    }

    public Travel findById(Integer id) {
        return travelMapper.selectById(id);
    }

    public String addOneTravel(Travel travel) {
        Integer res = travelMapper.insert(travel);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneTravel(Travel travel) {
        Integer res = travelMapper.updateById(travel);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneTravel(Integer id) {
        Integer res = travelMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

}
