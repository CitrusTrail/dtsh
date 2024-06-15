package com.fdzc.springboot01.service;

import com.fdzc.springboot01.common.dto.UserDTO;
import com.fdzc.springboot01.entity.Admin;

import java.util.List;

public interface IAdminService {

    List<Admin> findAllAdmin();

    Admin findById(Integer id);

    Admin findOneAdmin(Integer id);

    Integer addOneAdmin(Admin admin);

    Integer updateOneAdmin(Admin admin);

    Integer deleteOneAdmin(Integer id);

    Admin findByNamePassword(UserDTO userDTO);

    UserDTO login(UserDTO userDTO);

}
