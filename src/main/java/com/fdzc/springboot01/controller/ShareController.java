package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.entity.Share;
import com.fdzc.springboot01.service.ShareService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/shares")
public class ShareController {

    @Resource
    ShareService service;

    @GetMapping
    public List<Share> findAllShare() {
        return service.findAllShare();
    }

    @PostMapping
    public String addOneShare(Share share) {
        return service.addOneShare(share);
    }

    @PutMapping
    public String updateOneShare(Share share) {
        return service.updateOneShare(share);
    }

    @DeleteMapping("/{id}")
    public String deleteOneShare(Integer id) {
        return service.deleteOneShare(id);
    }

}
