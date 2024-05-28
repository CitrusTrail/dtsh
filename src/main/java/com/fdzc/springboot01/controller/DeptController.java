package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.Dept;
import com.fdzc.springboot01.service.DeptService;
import com.fdzc.springboot01.vo.DeptVo;
import org.apache.log4j.Logger;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/departments")
public class DeptController {

    @Resource
    DeptService deptService;

    private Logger logger = Logger.getLogger(UserController.class);

    /**
     * 查询单个部门数据
     */
    @GetMapping("/{id}")
    public Dept findOne(@PathVariable Integer id) {
        logger.info("前端传来的参数为：" + id);
        return deptService.findOne(id);
    }

    /**
     * 查询所有部门数据
     */
    @GetMapping
    public List<Dept> findAll() {
        return deptService.findAll();
    }

    /**
     * 查询某个部门下的所有用户
     */
    @GetMapping("/deptUsers/{id}")
    public DeptVo findDeptUsers(@PathVariable Integer id) {
        logger.info("前端传来的参数为：" + id);
        return deptService.findDeptUsers(id);
    }

    /**
     * 添加单个部门数据
     */
    @PostMapping
    public String addOne(@RequestBody Dept dept) {
        logger.info("前端传来的参数为：" + dept);
        return deptService.addOne(dept);
    }

    /**
     * 添加所有部门数据
     */
    @PostMapping("/all")
    public String addAll(@RequestBody List<Dept> depts) {
        logger.info("前端传来的参数为：" + depts);
        return deptService.addAll(depts);
    }

    /**
     * 修改单个部门数据
     */
    @PutMapping
    public String updateOne(@RequestBody Dept dept) {
        logger.info("前端传来的参数为：" + dept);
        return deptService.updateOne(dept);
    }

    /**
     * 修改所有部门数据
     */
    @PutMapping("/all")
    public String updateAll(@RequestBody List<Dept> depts) {
        logger.info("前端传来的参数为：" + depts);
        return deptService.updateAll(depts);
    }

    /**
     * 删除单个部门数据
     */
    @DeleteMapping("/{id}")
    public String deleteOne(@PathVariable Integer id) {
        logger.info("前端传来的参数为：" + id);
        return deptService.deleteOne(id);
    }

    /**
     * 删除所有部门数据
     */
    @DeleteMapping("/all")
    public String deleteAll(@RequestBody Integer[] ids) {
        logger.info("前端传来的参数为：" + Arrays.toString(ids));
        return deptService.deleteAll(ids);
    }

}
