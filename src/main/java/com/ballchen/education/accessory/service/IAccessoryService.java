package com.ballchen.education.accessory.service;

import com.ballchen.education.accessory.entity.Accessory;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * Created by ballchen on 2016/7/19.
 */
public interface IAccessoryService {
    int deleteByPrimaryKey(String id);

    int insert(Accessory record);

    int insertSelective(Accessory record);

    Accessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Accessory record);

    int updateByPrimaryKey(Accessory record);

    Accessory getAccessoryByMultipartFile(MultipartFile imgFile,String fileType)throws IOException,SftpException,JSchException;

    byte[] getAccessoryByteByAccessoryId(String id);

    List<Accessory> selectAccessoryByUserIdAndIdCardPicture(String id,String fileTypePositive,String fileTypeNegative);


    int insertIdCardPictureAccessory(Accessory idCardPositive,Accessory idCardNegative,String userId);

    String getAccessoryURLString(String id);


    /*List<Accessory> getUserIdCardPositivePictureAndNegativePicture(MultipartFile idCardPositivePicture,MultipartFile idCardNegativePicture) throws JSchException, IOException, SftpException;*/
}
