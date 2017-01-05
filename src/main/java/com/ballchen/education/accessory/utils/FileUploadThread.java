package com.ballchen.education.accessory.utils;

import com.ballchen.education.utils.QiniuCloudUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Map;

/**
 * Created by ballchen on 2016/12/10.
 */
@Component("fileUploadThread")
public class FileUploadThread implements Runnable {

    /**
     * 文件服务器配置
     */
    private Map<String,Object> fileServerProperties;
    /**
     * 文件
     */
    private MultipartFile file;
    /**
     * 保存文件名
     */
    private String saveFileServeName;

    /**
     * 文件扩展名
     */
    private String ext;

    @Override
    public void run() {
        synchronized (this){
            QiniuCloudUtils qiniuCloudUtils = getQiniuCloudUtils(fileServerProperties);
            try {
                qiniuCloudUtils.simpleUpload(file.getBytes(),saveFileServeName+ext);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static QiniuCloudUtils getQiniuCloudUtils(Map<String,Object> properties){
        String accessKey = (String)properties.get("accessKey");
        String secretKey = (String)properties.get("secretKey");
        String bucketName = (String)properties.get("bucketName");
        String filePath = (String)properties.get("filePath");
        QiniuCloudUtils qiniuCloudUtils = new QiniuCloudUtils(accessKey,secretKey,bucketName,filePath);
        return qiniuCloudUtils;
    }

    public Map<String, Object> getFileServerProperties() {
        return fileServerProperties;
    }

    public void setFileServerProperties(Map<String, Object> fileServerProperties) {
        this.fileServerProperties = fileServerProperties;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getSaveFileServeName() {
        return saveFileServeName;
    }

    public void setSaveFileServeName(String saveFileServeName) {
        this.saveFileServeName = saveFileServeName;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }
}
