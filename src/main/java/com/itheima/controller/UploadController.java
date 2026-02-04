package com.itheima.controller;

import com.itheima.pojo.Result;
import com.itheima.utils.AliyunOSSOperator;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.AbstractList;
import java.util.UUID;

@Slf4j
@RestController
public class UploadController {
    @Autowired
    private AliyunOSSOperator aliyunOSSOperator;
//    @PostMapping("/upload")
//    public Result upload(String name, Integer age, MultipartFile file) throws IOException {
//        //upload
//        log.info("接收参数{}.{}.{}",name,age,file);
//        //原始文件名\新的uuid名\获取后缀
//        String originalFilename = file.getOriginalFilename();
//        String extension=originalFilename.substring(originalFilename.lastIndexOf("."));
//        String newFileName= UUID.randomUUID().toString()+extension;
//        //转存到这个路径下了
//        file.transferTo(new File("D:/images/"+newFileName));
//
//        return Result.success();
//    }
    @PostMapping("/upload")
    public Result upload(MultipartFile file) throws Exception {
        log.info("文件上传：{}",file.getOriginalFilename());
        //文件有了，交给oss
        String url = aliyunOSSOperator.upload(file.getBytes(), file.getOriginalFilename());
        log.info("url是：{}",url);
        return Result.success(url);
    }
}

















