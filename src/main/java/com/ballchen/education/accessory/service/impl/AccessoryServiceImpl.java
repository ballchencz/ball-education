package com.ballchen.education.accessory.service.impl;

import com.ballchen.education.accessory.dao.IAccessoryDAO;
import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.accessory.service.IAccessoryService;
import com.ballchen.education.consts.PublicConsts;
import com.ballchen.education.utils.PublicUtils;
import com.ballchen.education.utils.QiniuCloudUtils;
import com.ballchen.education.utils.SftpUtils;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import com.qiniu.common.QiniuException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ballchen on 2016/7/19.
 */
@Service
@Transactional
public class AccessoryServiceImpl implements IAccessoryService {

    @Autowired
    private IAccessoryDAO accessoryDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return accessoryDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Accessory record) {
        return accessoryDAO.insert(record);
    }

    @Override
    public int insertSelective(Accessory record) {
        return accessoryDAO.insertSelective(record);
    }

    @Override
    public Accessory selectByPrimaryKey(String id) {
        return accessoryDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Accessory record) {
        return accessoryDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Accessory record) {
        return accessoryDAO.updateByPrimaryKey(record);
    }

    @Override
    public Accessory getAccessoryByMultipartFile(MultipartFile imgFile,String fileType) throws IOException, SftpException, JSchException,QiniuException{
        boolean flag = false;
        Accessory accessory = null;
        if(imgFile!=null){
            //获得文件名
            String fileName = imgFile.getOriginalFilename().substring(0, imgFile.getOriginalFilename().lastIndexOf("."));
            //获得文件扩展名
            String ext = imgFile.getOriginalFilename().substring(imgFile.getOriginalFilename().lastIndexOf("."),imgFile.getOriginalFilename().length());
            //获得保存到服务器的文件名
            String saveFileServeName = UUID.randomUUID().toString();
            Map<String,Object> fileServerProperties = PublicUtils.getUseableFileServerProperties(this.getClass().getClassLoader().getResource("/").getPath());
            String type = (String)fileServerProperties.get("type");
            if(type.equals("sftp")){//使用sftp上传
                String filePath = (String)this.getSftpUtils().get("filePath");
                SftpUtils sftpUtils = (SftpUtils)this.getSftpUtils().get("sftpUtils");

                sftpUtils.connect();
                //上传文件至服务器
                sftpUtils.uploadFile(imgFile.getInputStream(),filePath+"/"+saveFileServeName+ext);
                flag = true;

                sftpUtils.disconnect();
            }else{//使用七牛云存储
                QiniuCloudUtils qiniuCloudUtils = getQiniuCloudUtils(fileServerProperties);
                qiniuCloudUtils.simpleUpload(imgFile.getBytes(),saveFileServeName+ext);
                flag = true;
            }
            if(flag){//上传成功，实例化附件
                accessory = new Accessory();
                accessory.setId(UUID.randomUUID().toString());
                accessory.setUrl(saveFileServeName+ext);
                accessory.setFileName(fileName);
                accessory.setExt(ext);
                accessory.setSaveName(saveFileServeName);
                accessory.setAccessoryName(fileName);
                accessory.setFileType(fileType);
                accessory.setFileSize(imgFile.getSize());
            }
        }
        return accessory;
    }

    @Override
    public byte[] getAccessoryByteByAccessoryId(String id) {
        Accessory accessory = this.selectByPrimaryKey(id);
        byte [] bytes = null;
        if(accessory!=null){
            String fileUrl = accessory.getUrl();
            String filePath = (String)this.getSftpUtils().get("filePath");
            SftpUtils sftpUtils = (SftpUtils)this.getSftpUtils().get("sftpUtils");
            Map<String,Object> fileServerProperties = PublicUtils.getUseableFileServerProperties(this.getClass().getClassLoader().getResource("/").getPath());
            if(fileServerProperties.get("type").equals("sftp")){//sftp文件服务器
                try {
                    sftpUtils.connect();
                    bytes = sftpUtils.getFileByteArrayByFileArray(filePath.endsWith("/")?filePath:filePath+"/"+fileUrl);
                } catch (JSchException e) {
                    e.printStackTrace();
                } catch (SftpException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                } finally {
                    sftpUtils.disconnect();
                }
            }else{//七牛云存储
                QiniuCloudUtils qiniuCloudUtils = getQiniuCloudUtils(fileServerProperties);
                String saveName = accessory.getSaveName()+accessory.getExt();
                try {
                    bytes = qiniuCloudUtils.getFileBytesBySaveName(saveName);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return bytes;
    }

    private Map<String,Object> getSftpUtils(){
        String fileServiceFilePath = this.getClass().getClassLoader().getResource("/").getPath()+ PublicConsts.SFTP_FILE_SERVER_PROPERTIES_NAME;
        Map<String,Object> fileServeProperties = PublicUtils.getAllProperties(fileServiceFilePath);
        String userName = (String)fileServeProperties.get("userName");
        String password = (String)fileServeProperties.get("password");
        String host = (String)fileServeProperties.get("host");
        Integer port = Integer.valueOf((String)fileServeProperties.get("port"));
        String filePath = (String)fileServeProperties.get("filePath");
        SftpUtils sftpUtils = new SftpUtils(userName,password,host,port);
        Map<String,Object> returnMap = new HashMap<>();
        returnMap.put("filePath",filePath);
        returnMap.put("sftpUtils",sftpUtils);
        return returnMap;
    }
    private QiniuCloudUtils getQiniuCloudUtils(Map<String,Object> properties){
        String accessKey = (String)properties.get("accessKey");
        String secretKey = (String)properties.get("secretKey");
        String bucketName = (String)properties.get("bucketName");
        String filePath = (String)properties.get("filePath");
        QiniuCloudUtils qiniuCloudUtils = new QiniuCloudUtils(accessKey,secretKey,bucketName,filePath);
        return qiniuCloudUtils;
    }
}
