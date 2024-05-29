package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.Travel;
import com.fdzc.springboot01.service.TravelService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/travels")
public class TravelController {

    @Resource
    TravelService service;

    @GetMapping
    public List<Travel> findAllTravel() {
        return service.findAllTravel();
    }

    @PostMapping
    public String addOneTravel(Travel travel) {
        return service.addOneTravel(travel);
    }

    @PutMapping
    public String updateOneTravel(Travel travel) {
        return service.updateOneTravel(travel);
    }

    @DeleteMapping("/{id}")
    public String deleteOneTravel(Integer id) {
        return service.deleteOneTravel(id);
    }

}
