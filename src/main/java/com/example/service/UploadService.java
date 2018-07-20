package com.example.service;

import com.example.util.ToutiaoUtil;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;

@Service
public class UploadService {
    public String saveFile(MultipartFile file) throws IOException{
        int dotpos = file.getOriginalFilename().lastIndexOf(".");
        if(dotpos<0)
            return null;
        String fileFormat = file.getOriginalFilename().substring(dotpos+1);
        System.out.println(fileFormat);
        if(!ToutiaoUtil.isfileLegal(fileFormat)){
            return null;
        }
        String path = "C:\\Users\\wangxutao_sx\\Pictures\\";
        Files.copy(file.getInputStream(),new File(path+file.getOriginalFilename()).toPath(),StandardCopyOption.REPLACE_EXISTING);
        return ToutiaoUtil.getJSONString(0,"上传成功");
    }
}
