package com.fdzc.springboot01.mapper;

import com.fdzc.springboot01.entity.Dept;
import com.fdzc.springboot01.vo.DeptVo;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface DeptMapper {

    List<Dept> selectAll();

    Dept selectById(Integer id);

    DeptVo selectDeptUsers(Integer id);

    Integer insert(Dept dept);

    Integer update(Dept dept);

    Integer delete(Integer id);

}
