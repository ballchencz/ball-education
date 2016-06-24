package com.ballchen.education.security.consts;

import com.ballchen.education.annotation.RoleCode;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ballchen on 2016/5/28.
 */
public class SecurityConsts {
    public static final Map<String,String> roleMap = new LinkedHashMap<>();
    static{
        roleMap.put(RoleCode.STUDENT.name(),"学生");
        roleMap.put(RoleCode.TEACHER.name(),"老师");
        roleMap.put(RoleCode.ORGANIZATION.name(),"组织");
        roleMap.put(RoleCode.ADMIN.name(),"管理员");
    }
}
