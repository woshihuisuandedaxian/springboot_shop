package com.qf.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.util.UUID;

/**
 * @author ZouXianTao
 * @Date2020/1/2
 */
@Controller
@RequestMapping(value = "/upload")
public class UploadController {
    @Value("${goods.imagePath}")
    private String imagePath;    //D:/picture/image/
    /** 上传的图片的先回显*/
    @RequestMapping(value = "/image1")
    @ResponseBody
    public String uploadImage(@RequestParam(value = "Filedata") MultipartFile file){
        //把图片存到本地或者fastDFS上
        //返回图片的一个可以访问到的路径(返回到前端，先图片回显然后input标签上传图片地址到数据库)
        String fileName = file.getOriginalFilename();
        System.out.println("UploadController.uploadImage"+fileName+"-----"+imagePath);
        String pathName = UUID.randomUUID().toString();
        String path=imagePath+pathName;
        File file1=new File(path);
        //创建一个文件
        if(!file1.exists()){
            file1.mkdirs();
        }

        try{
            FileCopyUtils.copy(file.getInputStream(),new FileOutputStream(file1+File.separator+fileName));

        }catch (Exception e){
            e.printStackTrace();
        }
        String mapperPath="/picture/image/";
        mapperPath=mapperPath+pathName+"/"+fileName;
     return mapperPath;
    }
}
