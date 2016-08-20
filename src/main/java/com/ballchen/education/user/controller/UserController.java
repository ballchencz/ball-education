package com.ballchen.education.user.controller;

import com.ballchen.education.annotation.AuthorizationAnno;
import com.ballchen.education.annotation.RoleCode;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by ballchen on 2016/6/27.
 */
@Controller
@RequestMapping("userController")
public class UserController {
    @Autowired
    private IUserService userService;

    /**
     * 验证重复的身份证
     * @param idNumber 身份证号码
     * @param id 用户ID
     * @return String
     */
    @RequestMapping(value = "/validRepeatIdNumber",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.STUDENT,RoleCode.ADMIN},authorizationName = "验证重复的身份证")
    @ResponseBody
    public String validRepeatIdNumber(String idNumber,String id){
        UserBasic userBasic = userService.selectUserBasicByIdNumber(idNumber,id);
        if(userBasic!=null) {
            return "false";
        }else {
            return "true";
        }
    }
}
