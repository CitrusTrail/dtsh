package com.fdzc.springboot01.service;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.common.dto.IdDTO;
import com.fdzc.springboot01.common.dto.PageDTO;
import com.fdzc.springboot01.entity.Travel;
import com.fdzc.springboot01.mapper.TravelMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService {

    @Resource
    TravelMapper travelMapper;

    public PageDTO<Travel> findAllTravel(int page, int pagesize, Integer id, Integer userId, String mode) {
        int offset = (page - 1) * pagesize;
        PageDTO<Travel> pageDTO = new PageDTO<>();
        List<Travel> travels = travelMapper.selectAllTravel(id, userId, mode);
        pageDTO.setRecords(travels.stream().skip(offset).limit(pagesize).collect(Collectors.toList()));
        pageDTO.setTotal(travels.size());
        return pageDTO;
    }

    public Travel findById(Integer id) {
        return travelMapper.selectById(id);
    }

    public Integer addOneTravel(Travel travel) {
        return travelMapper.insert(travel);
    }

    public Integer updateOneTravel(Travel travel) {
        return travelMapper.updateById(travel);
    }

    public Integer deleteOneTravel(Integer id) {
        return travelMapper.deleteById(id);
    }

    public Integer deleteMultipleTravel(IdDTO idDTO) {
        List<Integer> ids = idDTO.getIds();
        Integer res = 0;
        for (Integer id : ids) {
            res += deleteOneTravel(id);
        }
        return res;
    }

    public void download(HttpServletResponse response) {
        List<Travel> list = travelMapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=export.xlsx");
            EasyExcel.write(response.getOutputStream(),Travel.class).autoCloseStream(Boolean.FALSE).sheet("出行列表").doWrite(list);
        } catch (Exception e) {
        }
    }

}
