package com.fdzc.springboot01.service;

import com.fdzc.springboot01.common.dto.AvatarDTO;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;

public interface IFileService {

    AvatarDTO uploadPicture(MultipartFile file);

    void download(HttpServletResponse response);

}
