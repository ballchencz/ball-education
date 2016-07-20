package com.ballchen.education.accessory.controller;

import com.ballchen.education.accessory.service.IAccessoryService;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.SftpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

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
}
