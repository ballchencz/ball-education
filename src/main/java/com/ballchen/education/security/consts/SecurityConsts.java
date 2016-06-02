package com.ballchen.education.security.consts;

import com.ballchen.education.annotation.RoleCode;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ballchen on 2016/5/28.
 */
public class SecurityConsts {
    public static final Map<String,String> roleMap = new HashMap<>();
    static{
        for(RoleCode roleCode:RoleCode.values()){
            switch (roleCode){
                case STUDENT:
                    roleMap.put(roleCode.name(),"学生");
                    break;
                case TEACHER:
                    roleMap.put(roleCode.name(),"老师");
                    break;
                case ORGANIZATION:
                    roleMap.put(roleCode.name(),"组织");
                    break;
                case ADMIN:
                    roleMap.put(roleCode.name(),"管理员");
                    break;
            }
        }
    }
}
