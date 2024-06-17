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
    @Resource
    private TaskMapper mapper;

    public AvatarDTO uploadPicture(MultipartFile file) {
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
        return avatarDTO;
    }

    public void download(HttpServletResponse response) {
        List<Task> tasks = mapper.selectList(null);
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
        response.setCharacterEncoding("UTF-8");
        try {
            response.setHeader("Content-disposition", "attachment;filename=" + "测试" + ".xlsx");
            EasyExcel.write(response.getOutputStream(),Task.class).autoCloseStream(Boolean.FALSE).sheet("任务列表").doWrite(tasks);
        } catch (Exception e) {
        }
    }
}
