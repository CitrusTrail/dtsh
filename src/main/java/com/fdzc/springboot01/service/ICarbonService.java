package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.UserCarbon;

import java.util.List;

public interface ICarbonService {

    List<UserCarbon> findAllUserCarbon();

    Integer addOneUserCarbon(UserCarbon userCarbon);

    Integer updateOneUserCarbon(UserCarbon userCarbon);

    Integer deleteOneUserCarbon(UserCarbon userCarbon);

}
