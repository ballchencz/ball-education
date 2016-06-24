package com.ballchen.education.account.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.ballchen.education.account.entity.Account;
import com.ballchen.education.account.service.IAccountService;
import com.ballchen.education.annotation.AuthorizationAnno;
import com.ballchen.education.annotation.RoleCode;
import org.omg.CORBA.Request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping("accountController")
public class AccountController {

    @Autowired
    private IAccountService accountService;

    @RequestMapping(value = "getAllAccountJSON",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = RoleCode.ADMIN)
    @ResponseBody
    public String getAllAccountJSON(){
        JSONArray jsonArray = new JSONArray();
        List<Account> accounts = this.accountService.getAllAccount();
        for(Account account : accounts){
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("id",account.getId());
            jsonObject.put("accountName",account.getAccountName());
            jsonArray.add(jsonObject);
        }
        return jsonArray.toJSONString();
    }
}
