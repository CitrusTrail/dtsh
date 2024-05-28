package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.Dept;
import com.fdzc.springboot01.mapper.DeptMapper;
import com.fdzc.springboot01.vo.DeptVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class DeptService {

    @Resource
    DeptMapper deptMapper;

    /**
     * 查询单个部门数据
     */
    public Dept findOne(Integer id) {
        return deptMapper.selectById(id);
    }

    /**
     * 查询所有部门数据
     */
    public List<Dept> findAll() {
        return deptMapper.selectAll();
    }

    /**
     * 查询某个部门下的所有用户
     */
    public DeptVo findDeptUsers(Integer id) {
        return deptMapper.selectDeptUsers(id);
    }

    /**
     * 添加单个部门数据
     */
    public String addOne(Dept dept) {
        Integer res = deptMapper.insert(dept);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    /**
     * 添加所有部门数据
     */
    public String addAll(List<Dept> depts) {
        Integer res = 0;
        for (Dept dept : depts) {
            res += deptMapper.insert(dept);
        }
        return String.format("添加成功：%d，添加失败：%d", res, depts.size() - res);
    }

    /**
     * 修改单个部门数据
     */
    public String updateOne(Dept dept) {
        Integer res = deptMapper.update(dept);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    /**
     * 修改所有部门数据
     */
    public String updateAll(List<Dept> depts) {
        Integer res = 0;
        for (Dept dept : depts) {
            res += deptMapper.update(dept);
        }
        return String.format("修改成功：%d，修改失败：%d", res, depts.size() - res);
    }

    /**
     * 删除单个部门数据
     */
    public String deleteOne(Integer id) {
        Integer res = deptMapper.delete(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

    /**
     * 删除所有部门数据
     */
    public String deleteAll(Integer[] ids) {
        Integer res = 0;
        for (Integer id : ids) {
            res += deptMapper.delete(id);
        }
        return String.format("删除成功：%d，删除失败：%d", res, ids.length - res);
    }
}