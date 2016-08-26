package com.ballchen.education.course.controller;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.annotation.AuthorizationAnno;
import com.ballchen.education.annotation.RoleCode;
import com.ballchen.education.course.consts.CourseConsts;
import com.ballchen.education.course.entity.Course;
import com.ballchen.education.course.service.ICourseService;
import com.ballchen.education.security.entity.Role;
import com.ballchen.education.user.entity.UserBasic;
import com.ballchen.education.user.service.IUserService;
import com.ballchen.education.utils.PublicUtils;
import com.sun.javafx.webkit.Accessor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/8/16.
 */
@Controller
@RequestMapping(value="/courseController")
public class CourseController {

    @Autowired
    private ICourseService courseService;

    @Autowired
    private IUserService userService;

    /**
     * 获得所有课程类型
     * @param courseType 课程类型
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/getCourseTypeJSON",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.STUDENT,RoleCode.ADMIN},authorizationName = "获得所有课程类型")
    @ResponseBody
    public Map<String,Object> getCourseTypeJSON(String courseType){
        Map<String,Object> courseTypeMap = new HashMap<>();
        if(courseType!=null && !courseType.equals("")){
            courseTypeMap.put(courseType,CourseConsts.COURSE_TYPE_MAP.get(courseType));
        }else{
            courseTypeMap = CourseConsts.COURSE_TYPE_MAP;
        }
        return courseTypeMap;
    }

    /**
     * 获得所有课程章节类型
     * @param courseChapterType 课程章节类型
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/getCourseChapterTypeJSON",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.STUDENT,RoleCode.ADMIN},authorizationName = "获得所有课程章节类型")
    @ResponseBody
    public Map<String,Object> getCourseChapterTypeJSON(String courseChapterType){
        Map<String,Object> courseChapterTypeMap = new HashMap<>();
        if(courseChapterType!=null&&!courseChapterType.equals("")){
            courseChapterTypeMap.put(courseChapterType,CourseConsts.COURSE_CHAPTER_TYPE_MAP.get(courseChapterType));
        }else{
            courseChapterTypeMap = CourseConsts.COURSE_CHAPTER_TYPE_MAP;
        }
        return courseChapterTypeMap;
    }

    /**
     * 根据课程ID获得课程图片
     * @param id 课程ID
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/getCoursePictureByCourseId",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.STUDENT,RoleCode.ADMIN},authorizationName = "根据课程ID获得课程图片")
    @ResponseBody
    public Map<String,Object> getCoursePictureByCourseId(String id){
        Course course = this.courseService.selectCourseAccessoryByCourseId(id);
        Accessory accessory;
        if(course!=null){
            accessory = course.getAccessories().get(0);
            return PublicUtils.parsePOJOtoMap(accessory);
        }else{
            return null;
        }
    }

    /**
     * 根据课程ID获得课程
     * @return Map<String,Object>
     */
    @RequestMapping(value = "/getCourseByCourseId",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.STUDENT,RoleCode.ADMIN},authorizationName = "根据课程ID获得课程")
    @ResponseBody
    public Course getCourseByCourseId(String id){
        Course course = this.courseService.selectByPrimaryKey(id);
        return course;
    }


    /**
     * 根据课程ID获得课程用户
     * @param id 课程ID
     * @return List<UserBasic>;
     */
    @RequestMapping(value = "/getCourseUserBasicByCourseId",method = RequestMethod.GET)
    @AuthorizationAnno(roleCode = {RoleCode.STUDENT,RoleCode.ADMIN},authorizationName = "根据课程ID获得课程图片")
    @ResponseBody
    public List<UserBasic> getCourseUserBasicByCourseId(String id){
        Course course = this.courseService.selectCourseUserBasicByCourseId(id);
        List<UserBasic> userBasics = course.getUserBasics();
        List<UserBasic> userBasicsReturn = new ArrayList<>();
        for(UserBasic userBasic : userBasics){
            UserBasic userBasicTemp = userService.selectUserBasicWithRolesByPrimaryKey(userBasic.getId());
            List<Role> roles = userBasicTemp.getRoles();
            for(Role role : roles){
                if(role.getRoleCode().equals(RoleCode.TEACHER.name()) || role.getRoleCode().equals(RoleCode.ADMIN.name())){
                    userBasicsReturn.add(userBasic);
                }
            }
        }
        return userBasicsReturn;
    }

}
