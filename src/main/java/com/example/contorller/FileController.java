package com.example.contorller;

import com.example.service.UploadService;
import com.example.util.ToutiaoUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);
    @Autowired
    UploadService uploadService;
    @RequestMapping(path = "/upload",method = {RequestMethod.POST})
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile file){
            try{
                System.out.println(file.getName());
                String fileUrl = uploadService.saveFile(file);
                if(fileUrl==null)
                    return ToutiaoUtil.getJSONString(1,"上传2失败");
                return fileUrl;
            } catch (Exception e ){
                logger.error("上传失败");
                return ToutiaoUtil.getJSONString(1,"上传1失败");
            }
    }
}
