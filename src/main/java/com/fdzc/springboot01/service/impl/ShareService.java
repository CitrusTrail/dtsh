package com.fdzc.springboot01.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.Share;
import com.fdzc.springboot01.mapper.ShareMapper;
import com.fdzc.springboot01.service.IShareService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ShareService implements IShareService {

    @Resource
    ShareMapper shareMapper;

    public PageDTO<Share> findAllShare(int page, int pagesize, Integer id, Integer userId, String content) {
        int offset = (page - 1) * pagesize;
        com.fdzc.springboot01.entity.dto.PageDTO<Share> pageDTO = new com.fdzc.springboot01.entity.dto.PageDTO<>();
        List<Share> shares = shareMapper.selectAllShare(id, userId, content);
        pageDTO.setRecords(shares.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(shares.size());
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

    public void download(HttpServletResponse response) {
        List<Share> list = shareMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),Share.class).autoCloseStream(Boolean.FALSE).sheet("分享列表").doWrite(list);
        } catch (Exception e) {
        }
    }

    public Long countNum() {
        return shareMapper.selectCount(null);
    }
}
