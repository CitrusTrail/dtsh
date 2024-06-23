package com.fdzc.springboot01.service.impl;

import com.fdzc.springboot01.entity.dto.FileDTO;
import com.fdzc.springboot01.service.IFileService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    public FileDTO uploadPicture(MultipartFile file) {
        FileDTO avatarDTO = new FileDTO();
        try {
            String originalFilename = file.getOriginalFilename();
            String suffixName = originalFilename.substring(originalFilename.lastIndexOf("."));
            String newFilename = UUID.randomUUID().toString() + "_" + suffixName;
            File fileDir = new File("src/main/resources/static/server/image/upload");
            file.transferTo(new File(fileDir.getAbsolutePath() + "/" + newFilename));
            avatarDTO.setUrl("/server/image/upload/" + newFilename);
            avatarDTO.setSavepath("/server/image/upload/" + newFilename);
        } catch (Exception e) {
        }
        return avatarDTO;
    }
}
