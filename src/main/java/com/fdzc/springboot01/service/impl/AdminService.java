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

    /**
     * 查询所有管理员信息。
     *
     * @return 包含所有管理员信息的列表。
     */
    public List<Admin> findAllAdmin() {
        return adminMapper.selectList(null);
    }

    /**
     * 根据管理员ID查找管理员信息。
     *
     * @param id 管理员ID
     * @return 对应ID的管理员对象，如果不存在则返回 null。
     */
    public Admin findOneAdmin(Integer id) {
        return adminMapper.selectById(id);
    }

    /**
     * 添加一个新的管理员。
     *
     * @param admin 要添加的管理员对象
     * @return 添加结果的字符串，格式为 "添加成功：1，添加失败：0"，其中1表示成功，0表示失败。
     */
    public String addOneAdmin(Admin admin) {
        Integer res = adminMapper.insert(admin);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    /**
     * 更新一个现有的管理员信息。
     *
     * @param admin 更新后的管理员对象
     * @return 更新结果的字符串，格式为 "修改成功：1，修改失败：0"，其中1表示成功，0表示失败。
     */
    public String updateOneAdmin(Admin admin) {
        Integer res = adminMapper.updateById(admin);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    /**
     * 删除一个管理员。
     *
     * @param id 要删除的管理员的ID
     * @return 删除结果的字符串，格式为 "删除成功：1，删除失败：0"，其中1表示成功，0表示失败。
     */
    public String deleteOneAdmin(Integer id) {
        Integer res = adminMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }


    public Admin findNamePassword(UserDTO userDTO) {
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
        Admin one = findNamePassword(userDTO);
        if (one != null) {
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
        } else {
        }
        return userDTO;
    }

}
