package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.entity.Share;
import com.fdzc.springboot01.mapper.ShareMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShareService {

    @Resource
    ShareMapper shareMapper;

    public PageDTO<Share> findAllShare(int page,int pagesize) {
        PageDTO<Share> pageDTO = new PageDTO<>();
        Page<Share> taskPage = shareMapper.selectPage(new Page<>(page, pagesize), null);
        pageDTO.setRecords(taskPage.getRecords());
        pageDTO.setTotal(taskPage.getTotal());
        return pageDTO;
    }

    public Share findById(Integer id) {
        return shareMapper.selectById(id);
    }

    public Integer addOneShare(Share share) {
        return shareMapper.insert(share);
    }

    public Integer updateOneShare(Share share) {
        return shareMapper.updateById(share);
    }

    public Integer deleteOneShare(Integer id) {
        return shareMapper.deleteById(id);
    }

    public Integer deleteMultipleShare(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneShare(id);
        }
        return res;
    }

}
