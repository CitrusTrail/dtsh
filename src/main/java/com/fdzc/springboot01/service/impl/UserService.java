package com.fdzc.springboot01.service.impl;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.entity.dto.UserDTO;
import com.fdzc.springboot01.mapper.UserMapper;
import com.fdzc.springboot01.service.IUserService;
import com.fdzc.springboot01.utils.TokenUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

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

    public User findByNamePassword(UserDTO userDTO) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("name", userDTO.getUsername());
        queryWrapper.eq("password", userDTO.getPassword());
        User one = null;
        try {
            one = userMapper.selectOne(queryWrapper); // 从数据库查询用户信息
        } catch (Exception e) {
        }
        return one;
    }

    public UserDTO login(UserDTO userDTO) {
        User one = findByNamePassword(userDTO);
        if (one != null) {
            // 设置token
            String token = TokenUtils.genToken(one.getId().toString(), one.getPassword());
            userDTO.setToken(token);
            userDTO.setId(one.getId());
        }
        return userDTO;
    }

    public UserDTO register(UserDTO userDTO) {
        if (findByNamePassword(userDTO) == null) {
            User user = new User();
            user.setName(userDTO.getUsername());
            user.setPassword(userDTO.getPassword());
            addOneUser(user);
        }
        return login(userDTO);
    }

    public Long countNum() {
        return userMapper.selectCount(null);
    }

}
