package com.sky.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "upload.folder")
@Data
public class FileProperties {

    /**
     * 上传文件的存储地址
     */
    private String folderName;
}
