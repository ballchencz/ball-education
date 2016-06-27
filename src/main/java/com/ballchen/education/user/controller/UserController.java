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
 * Created by ChenZhao on 2016/6/27.
 */
@Controller
@RequestMapping("userController")
public class UserController {
    @Autowired
    private IUserService userService;

    @RequestMapping(value = "/validRepeatIdNumber",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
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
