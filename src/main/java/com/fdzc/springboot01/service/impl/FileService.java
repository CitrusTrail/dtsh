package com.fdzc.springboot01.service.impl;

import com.alibaba.excel.EasyExcel;
import com.fdzc.springboot01.entity.dto.AvatarDTO;
import com.fdzc.springboot01.entity.Task;
import com.fdzc.springboot01.mapper.TaskMapper;
import com.fdzc.springboot01.service.IFileService;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.List;
import java.util.UUID;

@Service
public class FileService implements IFileService {

    public AvatarDTO uploadPicture(MultipartFile file) {
        AvatarDTO avatarDTO = new AvatarDTO();
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
        System.out.println("你好"+avatarDTO);
        return avatarDTO;
    }
}
