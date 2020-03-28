package com.leyou.upload.service;

import com.leyou.upload.controller.UploadAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class UploadService {
    //private static final List<String> image_types= Arrays.asList("jpg","jpeg");
    private static final List<String> CONTENT_TYPES= Arrays.asList("image/jpg","image/jpeg","image/gif");
    private static final Logger LOGGER= LoggerFactory.getLogger(UploadAction.class);
    public String uploadImage(MultipartFile file){
        String originalFilename = file.getOriginalFilename();
//        String[] split = originalFilename.split(".");
//        image_types.contains(split[split.length-1]);
        String contentType = file.getContentType();
        if(!CONTENT_TYPES.contains(contentType)){
            LOGGER.info("文件上传失败:{},文件类型不合法",originalFilename);
            return null;
        }

        try {
            BufferedImage read = ImageIO.read(file.getInputStream());
            if(read==null){
                LOGGER.info("文件上传失败:{},文件内容不合法",originalFilename);
                return null;

            }
            file.transferTo(new File("E:\\images\\"+originalFilename));
            return "http://image.leyou.com/"+originalFilename;
        } catch (IOException e) {
            LOGGER.info("文件上传失败:{},服务器异常!",originalFilename);
            e.printStackTrace();
        }
        return null;


    }

    }

