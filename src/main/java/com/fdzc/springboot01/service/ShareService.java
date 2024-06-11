package com.fdzc.springboot01.service;

import com.alibaba.excel.EasyExcel;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.plugins.pagination.PageDTO;
import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.entity.Share;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.mapper.ShareMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
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

}
