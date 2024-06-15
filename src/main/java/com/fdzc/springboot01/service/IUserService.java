package com.fdzc.springboot01.service;

import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.common.dto.PageDTO;
import com.fdzc.springboot01.entity.User;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    PageDTO<User> findAllUser(int page, int pagesize, Integer id, String name);

    User findById(Integer id);

    Integer addOneUser(User user);

    Integer updateOneUser(User user);

    Integer deleteOneUser(Integer id);

    Integer deleteMultipleUser(IdDTO idDTO);

    void download(HttpServletResponse response);

}
