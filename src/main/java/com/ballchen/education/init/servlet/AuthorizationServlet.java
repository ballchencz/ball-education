package com.ballchen.education.init.servlet;

import com.ballchen.education.annotation.AuthorizationAnno;
import com.ballchen.education.annotation.RoleCode;
import com.ballchen.education.security.consts.SecurityConsts;
import com.ballchen.education.security.dao.IAuthorizationDAO;
import com.ballchen.education.security.dao.IRoleAuthorizationDAO;
import com.ballchen.education.security.dao.IRoleDAO;
import com.ballchen.education.security.entity.Authorization;
import com.ballchen.education.security.entity.Role;
import com.ballchen.education.security.entity.RoleAuthorization;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.*;

/**
 * Created by ballchen on 2016/5/28.
 */
public class AuthorizationServlet extends HttpServlet {

    @Autowired
    IRoleDAO roleDAO;

    @Autowired
    IAuthorizationDAO authorizationDAO;

    @Autowired
    IRoleAuthorizationDAO roleAuthorizationDAO;

    @Override
    public void init(ServletConfig config) throws ServletException {
        SpringBeanAutowiringSupport.processInjectionBasedOnServletContext(this,config.getServletContext());
        System.out.println("------------------------------------------------------");
        //删除角色和权限
        this.roleAuthorizationDAO.deleteAll();
        this.roleDAO.deleteAll();
        this.authorizationDAO.deleteAll();
        //获得项目路径
        String path = this.getClass().getClassLoader().getResource("/").getPath();
        File file = new File(path+"com/ballchen/education");
        File[] files = file.listFiles();//获得模块包
        List<Class> controllerClasses = new ArrayList<>();
        //获得controller类
        for(File f : files){
            File [] fs = f.listFiles();
            for(File i : fs){
                if(i.getName().equals("controller")){
                    File [] controllerFiles = i.listFiles();
                    for(File controllerFile : controllerFiles){
                        String controllerFileName = controllerFile.getName();
                        controllerFileName = controllerFileName.substring(0,controllerFileName.lastIndexOf("."));
                        try {
                            controllerClasses.add(Class.forName("com.ballchen.education."+f.getName()+".controller."+controllerFileName));
                        } catch (ClassNotFoundException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
        //获得controller中的权限注解
        for(Class c : controllerClasses){
            //获得类RequestMapping中的值
             RequestMapping classRm =  (RequestMapping)c.getAnnotation(RequestMapping.class);
             String classControllerName = classRm.value()[0];
            //获得类当中方法的RequestMapping值
            List<Method> methods = new ArrayList<>();
            for(Method method : c.getMethods()){
                AuthorizationAnno authorizationAnno = method.getAnnotation(AuthorizationAnno.class);
                RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                if(authorizationAnno!=null){
                    //判断是否定义了值
                    //RoleName [] roleNames = authorizationAnno.roleName();
                    RoleCode [] roleCodes = authorizationAnno.roleCode();
                    String authorizationCode = authorizationAnno.authorizationCode();
                    if(authorizationCode.equals("")){
                        authorizationCode = c.getName().toUpperCase()+"_"+method.getName().toUpperCase();
                    }
                    String authorizationName = authorizationAnno.authorizationName();
                    if(authorizationName.equals("")){
                        authorizationName = c.getName().toUpperCase()+"_"+method.getName().toUpperCase();
                    }
                    String authorizationMark = authorizationAnno.mark();
                    if(authorizationMark.equals("")){
                        authorizationMark = c.getName();
                    }
                    //判断数据库中有没有相同代码的权限数据
                    this.addAuthorizationAndRole(authorizationCode,authorizationName,classControllerName,requestMapping,authorizationMark,roleCodes);
                }
            }
        }
        System.out.println("------------------------------------------------------");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    @Override
    public void destroy() {
        System.out.println("------------------------------------------------");
        System.out.println("AuthorizationAnno servlet destroy!!!!!!!!!!!!!!!!!!!!!!");
        System.out.println("------------------------------------------------");
    }

    /**
     * 添加角色和权限
     * @param authorizationCode 权限代码
     * @param authorizationName 权限名称
     * @param classControllerName controller名称
     * @param requestMapping RequestMapping对象
     * @param authorizationMark 权限备注
     * @param roleCodes 角色代码枚举数组
     */
    private void addAuthorizationAndRole(String authorizationCode,String authorizationName,String classControllerName,RequestMapping requestMapping,String authorizationMark,RoleCode[] roleCodes){
        Authorization authorization = new Authorization();
        String authorizationId = UUID.randomUUID().toString();
        authorization.setId(authorizationId);
        authorization.setAuthorizationCode(authorizationCode);
        authorization.setAuthorizationName(authorizationName);
        authorization.setWorkUrl("/"+classControllerName+requestMapping.value()[0]);
        authorization.setMark(authorizationMark);
        authorizationDAO.insert(authorization);
        //添加角色
        for(int i=0;i<roleCodes.length;i++){
            String roleCode = roleCodes[i].name();
            //判断是否已经存在相同角色代码的角色
            Role role = this.roleDAO.selectByRoleCode(roleCode);
            if(role==null){
                String roleId = UUID.randomUUID().toString();
                role = new Role();
                role.setId(roleId);
                role.setRoleCode(roleCode);
                role.setRoleName(SecurityConsts.roleMap.get(roleCode));
                role.setMark(SecurityConsts.roleMap.get(roleCode)+"_"+roleCode);
                //向数据库添加角色
                roleDAO.insert(role);
                //向数据库添加角色_权限关联
                RoleAuthorization roleAuthorization = new RoleAuthorization();
                roleAuthorization.setId(UUID.randomUUID().toString());
                roleAuthorization.setAuthorizationId(authorizationId);
                roleAuthorization.setRoleId(roleId);
                roleAuthorizationDAO.insert(roleAuthorization);
            }else{//只添加角色跟权限关联数据
                RoleAuthorization roleAuthorization = new RoleAuthorization();
                roleAuthorization.setId(UUID.randomUUID().toString());
                roleAuthorization.setAuthorizationId(authorizationId);
                roleAuthorization.setRoleId(role.getId());
                roleAuthorizationDAO.insert(roleAuthorization);
            }
        }
    }
}
