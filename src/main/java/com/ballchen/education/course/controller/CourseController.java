package com.ballchen.education.course.controller;

import com.ballchen.education.annotation.AuthorizationAnno;
import com.ballchen.education.annotation.RoleCode;
import com.ballchen.education.course.consts.CourseConsts;
import com.ballchen.education.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by ballchen on 2016/8/16.
 */
@Controller
@RequestMapping(value="/courseController")
public class CourseController {

    @Autowired
    private ICourseService courseService;

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

}
