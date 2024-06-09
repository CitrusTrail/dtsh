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

    public Integer addOneUserCarbon(UserCarbon userCarbon) {
        return userCarbonMapper.insert(userCarbon);
    }

    public Integer updateOneUserCarbon(UserCarbon userCarbon) {
        return userCarbonMapper.updateById(userCarbon);
    }

    public Integer deleteOneUserCarbon(UserCarbon userCarbon) {
        return userCarbonMapper.deleteById(userCarbon);
    }

}
