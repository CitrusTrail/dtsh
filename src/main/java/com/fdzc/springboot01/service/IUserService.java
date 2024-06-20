package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.entity.dto.UserDTO;

import javax.servlet.http.HttpServletResponse;

public interface IUserService {

    PageDTO<User> findAllUser(int page, int pagesize, Integer id, String name);

    User findById(Integer id);

    Integer addOneUser(User user);

    Integer updateOneUser(User user);

    Integer deleteOneUser(Integer id);

    Integer deleteMultipleUser(IdDTO idDTO);

    void download(HttpServletResponse response);

    User findByNamePassword(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

    UserDTO register(UserDTO userDTO);

    Long countNum();

}
