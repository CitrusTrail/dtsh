package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.springboot01.common.dto.UserDTO;
import com.fdzc.springboot01.entity.Admin;
import com.fdzc.springboot01.mapper.AdminMapper;
import com.fdzc.springboot01.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class AdminService {

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

    public String addOneAdmin(Admin admin) {
        Integer res = adminMapper.insert(admin);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneAdmin(Admin admin) {
        Integer res = adminMapper.updateById(admin);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneAdmin(Integer id) {
        Integer res = adminMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
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
