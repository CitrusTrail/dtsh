package com.fdzc.springboot01.service;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.common.dto.PageDTO;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.mapper.UserMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Resource
    UserMapper userMapper;

    public PageDTO<User> findAllUser(int page, int pagesize, Integer id, String name) {
        int offset = (page - 1) * pagesize;
        PageDTO<User> pageDTO = new PageDTO<>();
        List<User> users = userMapper.selectAllUser(id, name);
        pageDTO.setRecords(users.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(users.size());
        return pageDTO;
    }

    public User findById(Integer id) {
        return userMapper.selectById(id);
    }

    public Integer addOneUser(User user) {
        return userMapper.insert(user);
    }

    public Integer updateOneUser(User user) {
        return userMapper.updateById(user);
    }

    public Integer deleteOneUser(Integer id) {
        return userMapper.deleteById(id);
    }

    public Integer deleteMultipleUser(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneUser(id);
        }
        return res;
    }

    public void download(HttpServletResponse response) {
        List<User> list = userMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),User.class).autoCloseStream(Boolean.FALSE).sheet("用户列表").doWrite(list);
        } catch (Exception e) {
        }
    }

}
