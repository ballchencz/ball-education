package com.ballchen.education.accessory.controller;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.accessory.service.IAccessoryService;
import com.ballchen.education.admin.consts.AdminConsts;
import com.ballchen.education.consts.PublicConsts;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by ballchen on 2016/7/21.
 * 附件controller
 */
@Controller
@RequestMapping("accessoryController")
public class AccessoryController {

    @Autowired
    private IAccessoryService accessoryService;

    /**
     * 根据附件ID获得附件的字节数组
     * @param id 附件ID
     * @return byte[]
     */
    @RequestMapping(value="/getAccessoryBytesByAccessoryId",method={RequestMethod.GET},produces="image/*;charset=UTF-8")
    @ResponseBody
    public byte[] getAccessoryBytesByAccessoryId(String id){
        byte[] bytes = null;
        bytes = this.accessoryService.getAccessoryByteByAccessoryId(id);
        return bytes;
    }

    /**
     * 根据用户ID和身份证反正面标识获得身份证字节数组
     * @param userBasicId 用户ID
     * @param idCardPictureFileType 身份证反正面标识
     * @return byte[]
     */
    @RequestMapping(value="/getAccessoryByUserBasicIdAndIdCardPictureFileType",method={RequestMethod.GET},produces="image/*;charset=UTF-8")
    @ResponseBody
    public byte[] getAccessoryByUserBasicIdAndIdCardPictureFileType(String userBasicId,String idCardPictureFileType){
        Accessory accessory = null;
        List<Accessory> accessories = null;
        byte [] b = null;
        if(idCardPictureFileType!=null){
            if(idCardPictureFileType.equals("positive")){//正面
                 accessories = this.accessoryService.selectAccessoryByUserIdAndIdCardPicture(userBasicId, PublicConsts.USER_FILE_TYPE_IDCARD_POSITIVE,null);
            }else{//反面
                accessories = this.accessoryService.selectAccessoryByUserIdAndIdCardPicture(userBasicId, null,PublicConsts.USER_FILE_TYPE_IDCARD_NEGATIVE);
            }
        }
        if(accessories!=null && accessories.size()>0){
            accessory = accessories.get(0);
            b = getAccessoryBytesByAccessoryId(accessory.getId());
        }
        return b;
    }
}
