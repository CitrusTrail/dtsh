package com.fdzc.springboot01.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.springboot01.entity.dto.UserDTO;
import com.fdzc.springboot01.entity.Admin;
import com.fdzc.springboot01.mapper.AdminMapper;
import com.fdzc.springboot01.utils.TokenUtils;
import org.springframework.stereotype.Service;
import com.fdzc.springboot01.service.IAdminService;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService implements IAdminService {

    @Resource
    AdminMapper adminMapper;

    public List<Admin> findAllAdmin() {
        return adminMapper.selectList(null);
    }

    public Admin findById(Integer id) {
        return adminMapper.selectById(id);
    }

    public Admin findOneAdmin(Integer id) {
        return adminMapper.selectById(id);
    }

    public Integer addOneAdmin(Admin admin) {
        return adminMapper.insert(admin);
    }

    public Integer updateOneAdmin(Admin admin) {
        return adminMapper.updateById(admin);
    }

    public Integer deleteOneAdmin(Integer id) {
        return adminMapper.deleteById(id);
    }

    public Admin findByNamePassword(UserDTO userDTO) {
        QueryWrapper<Admin> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        Admin one = null;
        try {
            one = adminMapper.selectOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
        }
        return one;
    }

    public UserDTO login(UserDTO userDTO) {
        Admin one = findByNamePassword(userDTO);
        if (one != null) {
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            userDTO.setId(one.getId());
        }
        return userDTO;
    }

}
