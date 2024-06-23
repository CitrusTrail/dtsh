package com.fdzc.springboot01.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.entity.User;
import com.fdzc.springboot01.entity.dto.IdDTO;
import com.fdzc.springboot01.entity.dto.PageDTO;
import com.fdzc.springboot01.entity.vo.ModeVo;
import com.fdzc.springboot01.entity.Travel;
import com.fdzc.springboot01.mapper.TravelMapper;
import com.fdzc.springboot01.mapper.UserMapper;
import com.fdzc.springboot01.service.ITravelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.time.Duration;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TravelService implements ITravelService {

    @Resource
    TravelMapper travelMapper;

    @Resource
    UserMapper userMapper;

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
        LocalDateTime startTime = LocalDateTime.parse(travel.getStartTime(), DateTimeFormatter.ofPattern("yyyy/M/d H:m:s"));
        LocalDateTime endTime = LocalDateTime.parse(travel.getEndTime(), DateTimeFormatter.ofPattern("yyyy/M/d H:m:s"));
        Integer totalTime = (int) Duration.between(startTime,endTime).toMinutes();
        Double carbon = 0.0;
        String mode = travel.getMode();
        if(mode=="步行"||mode=="骑行"){
            carbon = totalTime * 14.4;
        }else{
            carbon = totalTime * 24.4;
        }
        travel.setTotalTime(totalTime);
        travel.setCarbon(carbon);
        int res = travelMapper.insert(travel);
        if(res == 1){
            User user = userMapper.selectById(travel.getUserId());
            user.setCarbon(user.getCarbon()+travel.getCarbon());
            userMapper.updateById(user);
        }
        return res;
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

    public List<ModeVo> findAllMode() {
        return travelMapper.selectAllMode();
    }

}
