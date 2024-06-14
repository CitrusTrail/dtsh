package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.Share;
import com.fdzc.springboot01.mapper.ShareMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ShareService {

    @Resource
    ShareMapper shareMapper;

    public List<Share> findAllShare() {
        return shareMapper.selectList(null);
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
