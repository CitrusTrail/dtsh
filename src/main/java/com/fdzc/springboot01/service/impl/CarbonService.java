package com.fdzc.springboot01.service.impl;

import com.fdzc.springboot01.entity.UserCarbon;
import com.fdzc.springboot01.mapper.UserCarbonMapper;
import com.fdzc.springboot01.service.ICarbonService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class CarbonService implements ICarbonService {

    @Resource
    UserCarbonMapper userCarbonMapper;

    /**
     * 查询所有用户碳排放信息。
     *
     * @return 包含所有用户碳排放信息的列表。
     */
    public List<UserCarbon> findAllUserCarbon() {
        return userCarbonMapper.selectList(null);
    }

    /**
     * 添加一条用户碳排放信息。
     *
     * @param userCarbon 要添加的用户碳排放对象
     * @return 插入操作影响的行数
     */
    public Integer addOneUserCarbon(UserCarbon userCarbon) {
        return userCarbonMapper.insert(userCarbon);
    }

    /**
     * 更新一条用户碳排放信息。
     *
     * @param userCarbon 更新后的用户碳排放对象
     * @return 更新操作影响的行数
     */
    public Integer updateOneUserCarbon(UserCarbon userCarbon) {
        return userCarbonMapper.updateById(userCarbon);
    }

    /**
     * 删除一条用户碳排放信息。
     *
     * @param userCarbon 要删除的用户碳排放对象
     * @return 删除操作影响的行数
     */
    public Integer deleteOneUserCarbon(UserCarbon userCarbon) {
        return userCarbonMapper.deleteById(userCarbon);
    }

}
