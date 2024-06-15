package com.fdzc.springboot01.service;

import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.common.dto.PageDTO;
import com.fdzc.springboot01.entity.Share;

import javax.servlet.http.HttpServletResponse;

public interface IShareService {

    PageDTO<Share> findAllShare(int page, int pagesize, Integer id, Integer userId, String content);

    Share findById(Integer id);

    Integer addOneShare(Share share);

    Integer updateOneShare(Share share);

    Integer deleteOneShare(Integer id);

    Integer deleteMultipleShare(IdDTO idDTO);

    void download(HttpServletResponse response);

}
