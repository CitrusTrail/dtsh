package com.fdzc.springboot01.controller;

import com.fdzc.springboot01.common.Result;
import com.fdzc.springboot01.common.dto.AvatarDTO;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadController {

    @PostMapping("/picture")
    public Result uploadPicture(MultipartFile file) {
        AvatarDTO avatarDTO = new AvatarDTO();
        try {
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + "_" + suffixName;
            ClassPathResource classPathResource = new ClassPathResource("static/upload/picture");
            File fileDir = classPathResource.getFile();
            file.transferTo(new File(fileDir.getAbsolutePath() + "/" + newFilename));
            avatarDTO.setUrl("http://localhost:8099/upload/picture/" + newFilename);
            avatarDTO.setSavepath("http://localhost:8099/upload/picture/" + newFilename);
        } catch (Exception e) {
        }
        return Result.success(avatarDTO);
    }
}
