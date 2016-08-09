package com.ballchen.education.accessory.service.impl;

import com.ballchen.education.accessory.dao.IAccessoryDAO;
import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.accessory.service.IAccessoryService;
import com.ballchen.education.consts.PublicConsts;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.entity.UserBasicAccessory;
import com.ballchen.education.user.service.IUserBasicAccessoryService;
import com.ballchen.education.user.service.IUserService;
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
import java.util.*;

/**
 * Created by ballchen on 2016/7/19.
 */
@Service
@Transactional
public class AccessoryServiceImpl implements IAccessoryService {

    @Autowired
    private IAccessoryDAO accessoryDAO;
    @Autowired
    private IUserService userService;
    @Autowired
    private IUserBasicAccessoryService userBasicAccessoryService;

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
            Map<String,Object> fileServerProperties = PublicUtils.getUseableFileServerProperties(this.getClass().getClassLoader().getResource("/").getPath());
            String type = (String)fileServerProperties.get("type");
            accessory = this.uploadFile(type,imgFile,flag,fileServerProperties,fileType);
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
            if(fileServerProperties.get("type").equals(PublicConsts.FILE_SERVER_TYPE_SFTP)){//sftp文件服务器
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

    @Override
    public List<Accessory> selectAccessoryByUserIdAndIdCardPicture(String id, String fileTypePositive,String fileTypeNegative) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("id",id);
        queryMap.put("fileTypePositive",fileTypePositive);
        queryMap.put("fileTypeNegative",fileTypeNegative);
        Map<String,Object> fileServerProperties = PublicUtils.getUseableFileServerProperties(this.getClass().getClassLoader().getResource("/").getPath());
        queryMap.put("fileServerType",fileServerProperties.get("type"));
        return accessoryDAO.selectAccessoryByUserIdAndIdCardPicture(queryMap);
    }

    @Override
    public int insertIdCardPictureAccessory(Accessory idCardPositive,Accessory idCardNegative,String userId) {
        int i = 0;
        if(idCardPositive!=null && idCardNegative!=null){
            Map<String,Object> queryMap = new HashMap<>();
            queryMap.put("id",userId);
            queryMap.put("fileTypePositive",PublicConsts.USER_FILE_TYPE_IDCARD_POSITIVE);
            queryMap.put("fileTypeNegative",PublicConsts.USER_FILE_TYPE_IDCARD_NEGATIVE);
            Map<String,Object> fileServerProperties = PublicUtils.getUseableFileServerProperties(this.getClass().getClassLoader().getResource("/").getPath());
            queryMap.put("fileServerType",fileServerProperties.get("type"));
            /*查询出身份证正反图片*/
            List<Accessory> userIdCardPictureAccessories = this.accessoryDAO.selectAccessoryByUserIdAndIdCardPicture(queryMap);
            /*判断是否有身份证正反图片存在,存在，删除和UserBasic的关联，删除附件，然后添加附件，添加关联*/
            if(userIdCardPictureAccessories!=null&&userIdCardPictureAccessories.size()>0){
                //删除和UserBasic的关联，删除附件
                for(Accessory accessory : userIdCardPictureAccessories){
                    //删除和UserBasic的关联
                    UserBasicAccessory userBasicAccessory = userBasicAccessoryService.getUserBasicAccessoryByUserBasicIdAndAccessoryId(userId,accessory.getId());
                    this.userBasicAccessoryService.deleteByPrimaryKey(userBasicAccessory.getId());
                    //删除附件
                    this.deleteByPrimaryKey(accessory.getId());
                }
            }
            //添加附件，添加关联
            this.insertSelective(idCardPositive);
            this.insertSelective(idCardNegative);

            UserBasicAccessory userBasicAccessoryPositive = new UserBasicAccessory();
                userBasicAccessoryPositive.setId(UUID.randomUUID().toString());
                userBasicAccessoryPositive.setAccessoryId(idCardPositive.getId());
                userBasicAccessoryPositive.setUserBasicId(userId);

            UserBasicAccessory userBasicAccessoryNegative = new UserBasicAccessory();
                userBasicAccessoryNegative.setId(UUID.randomUUID().toString());
                userBasicAccessoryNegative.setAccessoryId(idCardNegative.getId());
                userBasicAccessoryNegative.setUserBasicId(userId);

            this.userBasicAccessoryService.insertSelective(userBasicAccessoryPositive);
            i = this.userBasicAccessoryService.insertSelective(userBasicAccessoryNegative);

        }
        return i;
    }


/*    @Override
    public List<Accessory> getUserIdCardPositivePictureAndNegativePicture(MultipartFile idCardPositivePicture, MultipartFile idCardNegativePicture) throws JSchException, IOException, SftpException {
        boolean flag = false;
        List<Accessory> accessories = new ArrayList<>();
        Map<String,Object> fileServerProperties = PublicUtils.getUseableFileServerProperties(this.getClass().getClassLoader().getResource("/").getPath());
        String type = (String)fileServerProperties.get("type");
        Accessory idCardPositive = this.uploadFile(type,idCardPositivePicture,flag,fileServerProperties,PublicConsts.USER_FILE_TYPE_IDCARD_POSITIVE);
        Accessory idCardNegative = this.uploadFile(type,idCardNegativePicture,flag,fileServerProperties,PublicConsts.USER_FILE_TYPE_IDCARD_NEGATIVE);
        accessories.add(idCardPositive);
        accessories.add(idCardNegative);
        return accessories;
    }*/


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

    private Accessory uploadFile(String fileServerType,MultipartFile file,boolean flag,Map<String,Object> fileServerProperties,String fileType) throws JSchException, IOException, SftpException {
        Accessory accessory = null;
        //获得文件名
        String fileName = file.getOriginalFilename().substring(0, file.getOriginalFilename().lastIndexOf("."));
        //获得文件扩展名
        String ext = file.getOriginalFilename().substring(file.getOriginalFilename().lastIndexOf("."),file.getOriginalFilename().length());
        //获得保存到服务器的文件名
        String saveFileServeName = UUID.randomUUID().toString();
        if(fileServerType.equals(PublicConsts.FILE_SERVER_TYPE_SFTP)){//使用sftp上传
            String filePath = (String)this.getSftpUtils().get("filePath");
            SftpUtils sftpUtils = (SftpUtils)this.getSftpUtils().get("sftpUtils");
            sftpUtils.connect();
            //上传文件至服务器
            sftpUtils.uploadFile(file.getInputStream(),filePath+"/"+saveFileServeName+ext);
            flag = true;
            sftpUtils.disconnect();
        }else{//使用七牛云存储
            QiniuCloudUtils qiniuCloudUtils = getQiniuCloudUtils(fileServerProperties);
            qiniuCloudUtils.simpleUpload(file.getBytes(),saveFileServeName+ext);
            flag = true;
        }
        if(flag){
            accessory = new Accessory();
            accessory.setId(UUID.randomUUID().toString());
            accessory.setUrl(saveFileServeName+ext);
            accessory.setFileName(fileName);
            accessory.setExt(ext);
            accessory.setSaveName(saveFileServeName);
            accessory.setAccessoryName(fileName);
            accessory.setFileType(fileType);
            accessory.setFileServerType(fileServerType);
            accessory.setFileSize(file.getSize());
        }
        return accessory;
    }
}
