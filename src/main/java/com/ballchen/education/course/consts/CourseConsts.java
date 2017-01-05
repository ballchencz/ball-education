package com.ballchen.education.course.consts;


import java.util.LinkedHashMap;
import java.util.Map;

/**
 * Created by ballchen on 2016/8/18.
 */
public class CourseConsts {
    /*------------------------课程类型常量开始------------------------------*/
    /**
     * 一对一课程类型
     */
    public static final String COURSE_TYPE_ONE_TO_ONE = "ONE_TO_ONE";

    /**
     * 班课课程类型
     */
    public static final String COURSE_TYPE_BAN_KE = "BAN_KE";

    /**
     * 课程类型map集合
     */
    public static Map<String,Object> COURSE_TYPE_MAP = new LinkedHashMap<>();
    static{
        COURSE_TYPE_MAP.put(COURSE_TYPE_BAN_KE,"班课");
        COURSE_TYPE_MAP.put(COURSE_TYPE_ONE_TO_ONE,"一对一");
    }
    /*------------------------课程类型常量结束------------------------------*/
    /*------------------------课程文件类型常量开始--------------------------*/
    /**
     * 课程图片
     */
    public static final String COURSE_FILE_TYPE_COURE_PICTURE = "COURSE_PICTURE";
    /*------------------------课程文件类型常量开始--------------------------*/
    /*------------------------课程章节常量开始------------------------------*/
    /**
     * 在线直播
     */
    public static final String COURSE_CHAPTER_TYPE_ONLINE = "COURSE_CHAPTER_TYPE_ONLINE";
    public static final String COURSE_CHAPTER_TYPE_OUTLINE = "COURSE_CHAPTER_TYPE_OUTLINE";
    public static final String COURSE_CHAPTER_TYPE_VIDEO = "COURSE_CHAPTER_TYPE_VIDEO";
    public static final String COURSE_CHAPTER_TYPE_OTHER = "COURSE_CHAPTER_TYPE_OTHER";
    public static final Map<String,Object> COURSE_CHAPTER_TYPE_MAP = new LinkedHashMap<>();
    static{
        COURSE_CHAPTER_TYPE_MAP.put(COURSE_CHAPTER_TYPE_VIDEO,"视频课");
        COURSE_CHAPTER_TYPE_MAP.put(COURSE_CHAPTER_TYPE_ONLINE,"在线直播");
        COURSE_CHAPTER_TYPE_MAP.put(COURSE_CHAPTER_TYPE_OUTLINE,"线下课程");
        COURSE_CHAPTER_TYPE_MAP.put(COURSE_CHAPTER_TYPE_OTHER,"其它");
    }
    /*------------------------课程章节常量结束------------------------------*/
}
