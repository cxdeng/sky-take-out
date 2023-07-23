package com.sky.controller.admin;

import com.sky.constant.MessageConstant;
import com.sky.exception.FileNotFoundException;
import com.sky.properties.FileProperties;
import com.sky.result.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;

@Slf4j
@RestController
@RequestMapping("/admin/common")
@Api(tags = "通用接口")
public class CommonController {

    @Autowired
    private FileProperties fileProperties;


    @PostMapping("/upload")
    @ApiOperation("文件上传")
    public Result<String> upload(MultipartFile file) throws FileNotFoundException {
        log.info("文件上传: {}", file);

        if (file == null || file.isEmpty()) {
            throw new FileNotFoundException(MessageConstant.EMPTY_FILE);
        }

        try {
            // 获取文件的名字
            String filename = file.getOriginalFilename();
            // 获取上传文件的后缀名
            String suffix = filename.substring(filename.lastIndexOf("."));
            // 获取uuid
            String uuid = UUID.randomUUID().toString();
            // 拼接新的文件名
            filename = uuid + suffix;

            String folderName = fileProperties.getFolderName();
            // 定义文件保存的目录路径
            String directoryPath = folderName + "images";
            // 创建目录，如果不存在
            Path directory = Paths.get(directoryPath);
            if (!Files.exists(directory)) {
                Files.createDirectories(directory);
            }
            String finalFilePath = directoryPath + File.separator + filename;
            file.transferTo(new File(finalFilePath));

            return Result.success(finalFilePath);
        } catch (IOException e) {
            log.error("File upload file: {}", e);
        }

        return Result.error(MessageConstant.UPLOAD_FAILED);
    }
}
