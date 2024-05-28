package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.Admin;
import com.fdzc.springboot01.mapper.AdminMapper;
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

}
