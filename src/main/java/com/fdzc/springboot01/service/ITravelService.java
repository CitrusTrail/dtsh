package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.vo.ModeVo;
import com.fdzc.springboot01.entity.Travel;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

public interface ITravelService {

    PageDTO<Travel> findAllTravel(int page, int pagesize, Integer id, Integer userId, String mode);

    Travel findById(Integer id);

    Integer addOneTravel(Travel travel);

    Integer updateOneTravel(Travel travel);

    Integer deleteOneTravel(Integer id);

    Integer deleteMultipleTravel(IdDTO idDTO);

    void download(HttpServletResponse response);

    List<ModeVo> findAllMode();

}
