package com.ballchen.education.annotation;

import com.ballchen.education.security.entity.Role;

import java.lang.annotation.*;

/**
 * Created by Administrator on 2016/5/28.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface AuthorizationAnno {
    /**
     * 角色代码
     * @return
     */
    RoleCode[] roleCode() default {RoleCode.STUDENT};


    /**
     * 权限名称
     * @return
     */
    String authorizationName() default "";

    /**
     * 权限代码
     * @return
     */
    String authorizationCode() default "";

    /**
     * 权限备注
     * @return
     */
    String mark() default "";
}
