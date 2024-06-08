package com.fdzc.springboot01.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.entity.Share;
import com.fdzc.springboot01.mapper.ShareMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

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

    public String addOneShare(Share share) {
        Integer res = shareMapper.insert(share);
        return String.format("添加成功：%d，添加失败：%d", res, 1 - res);
    }

    public String updateOneShare(Share share) {
        Integer res = shareMapper.updateById(share);
        return String.format("修改成功：%d，修改失败：%d", res, 1 - res);
    }

    public String deleteOneShare(Integer id) {
        Integer res = shareMapper.deleteById(id);
        return String.format("删除成功：%d，删除失败：%d", res, 1 - res);
    }

}
