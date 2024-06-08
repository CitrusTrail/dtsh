package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.entity.Travel;
import com.fdzc.springboot01.mapper.TravelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class TravelService {

    @Resource
    TravelMapper travelMapper;

    public PageDTO<Travel> findAllTravel(int page, int pagesize) {
        PageDTO<Travel> pageDTO = new PageDTO<>();
        Page<Travel> taskPage = travelMapper.selectPage(new Page<>(page, pagesize), null);
        pageDTO.setRecords(taskPage.getRecords());
        pageDTO.setTotal(taskPage.getTotal());
        return pageDTO;
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
