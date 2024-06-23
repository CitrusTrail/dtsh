package com.fdzc.springboot01.service;

import com.fdzc.springboot01.entity.dto.FileDTO;
import org.springframework.web.multipart.MultipartFile;

public interface IFileService {

    FileDTO uploadPicture(MultipartFile file);

}
