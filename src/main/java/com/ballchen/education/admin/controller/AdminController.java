package com.ballchen.education.admin.controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.account.service.IAccountService;
import com.ballchen.education.admin.consts.AdminConsts;
import com.ballchen.education.annotation.AuthorizationAnno;
import com.ballchen.education.annotation.RoleCode;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ChenZhao on 2016/5/19.
 */
@Controller
@RequestMapping("adminController")
public class AdminController {

    @Autowired
    private IAccountService accountService;

    /**
     * 获得后台登录页面
     * @return
     */
    @RequestMapping(value="/getAdminLoginPage",method = RequestMethod.GET)
    public ModelAndView getAdminLoginPage(){
        ModelAndView mv = new ModelAndView("/admin/login");
        return mv;
    }

    /**
     * 验证后台登录
     * @param account 账户名称
     * @return ModelAndView
     */
    @RequestMapping(value = "/validLogin",method = RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> login(Account account){
        ModelAndView mv = new ModelAndView("/admin/index");
        Map<String,Object> returnMap = new HashMap<String,Object>();
        returnMap.put("flag",true);
        returnMap.put("info","我是中文，还是乱码？");
        return returnMap;
    }

    /**
     * 获得管理后台页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getAdminIndexPage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getAdminIndexPage(){
        ModelAndView mv = new ModelAndView("/admin/index");
        return mv;
    }

    /**
     * 获得管理后台右侧首页页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getAdminIndexRightIndexPage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getAdminIndexRightIndexPage(){
        ModelAndView mv = new ModelAndView("/admin/index-right_index");
        return mv;
    }

    /**
     * 获得用户管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getUserManagePage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN,RoleCode.ORGANIZATION})
    public ModelAndView getUserManagePage(){
        ModelAndView mv = new ModelAndView("/admin/user-manage");
        return mv;
    }

    /**
     * 获得账户管理页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/getAccountManagePage",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN})
    public ModelAndView getAccountManagePage(){
        ModelAndView mv = new ModelAndView("/admin/account-manage");
        return mv;
    }


    /**
     * 获得账户分页数据
     * @param account
     * @return
     */
    @RequestMapping(value = "/getAccountPagination",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.ADMIN})
    public String getAccountPagination(Account account){
        List<Account> accounts = this.accountService.getAccountPagination(account);
        JSONObject jsonO = new JSONObject();
        jsonO.put("total",accountService.getAccountPaginationCount(account));
        jsonO.put("rows", JSONArray.parseArray(JSONArray.toJSONStringWithDateFormat(accounts, AdminConsts.DATETIME_FORMAT_STRING)).toArray());
        return jsonO.toJSONString();
    }




}
