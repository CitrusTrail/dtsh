package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.service.impl.FileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private FileService service;

    @PostMapping("/picture")
    public Result uploadPicture(MultipartFile file) {
        return Result.success(service.uploadPicture(file));
    }

}
