package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.UserCarbon;
import com.fdzc.springboot01.mapper.UserCarbonMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarbonService {

    @Resource
    UserCarbonMapper userCarbonMapper;

    public List<UserCarbon> findAllUserCarbon() {
        return userCarbonMapper.selectList(null);
    }

    public String addOneUserCarbon(UserCarbon userCarbon) {
        Integer res = userCarbonMapper.insert(userCarbon);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneUserCarbon(UserCarbon userCarbon) {
        Integer res = userCarbonMapper.updateById(userCarbon);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneUserCarbon(Integer id) {
        Integer res = userCarbonMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

}
