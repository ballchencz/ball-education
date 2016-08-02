package com.ballchen.education.category.consts;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by ballchen on 2016/7/26.
 */
public class CategoryConst {
    /**
     * 自营课程分类类型
     */
    public static final String CATEGORY_TYPE_SELF_COURSE = "SELF_COURSE";

    /**
     * 学习资源分类类型
     */
    public static final String CATEGORY_TYPE_STUDY_RESOURCE="STUDY_RESOURCE";

    /**
     * 老师课程分类类型
     */
    public static final String CATEGORY_TYPE_TEACHER_COURSE = "TEACHER_COURSE";

    /**
     * 机构课程分类
     */
    public static final String CATEGORY_TYPE_ORGANIZATION_COURSE= "ORGANIZATION_COURSE";

    /**
     * 留学分类
     */
    public static final String CATEGORY_TYPE_STUDY_ABROAD = "STUDY_ABROAD";

    /**
     * 机构留学分类
     */
    public static final String CATEGORY_TYPE_ORGANIZATION_STUDY_ABROAD = "ORGANIZATION_STUDY_ABROAD";

    /**
     * 机构学习资源分类
     */
    public static final String CATEGORY_TYPE_ORGANIZATION_STUDY_RESOURCE="ORGANIZATION_STUDY_RESOURCE";

    public static ArrayList<Map<String,Object>> categoryTypes = new ArrayList<>();
    static{
        Map<String,Object> map = new HashMap<>();
            map.put("label","自营课程");
            map.put("value",CATEGORY_TYPE_SELF_COURSE);
        categoryTypes.add(map);
        map = new HashMap<>();
            map.put("label","老师课程");
            map.put("value",CATEGORY_TYPE_TEACHER_COURSE);
        categoryTypes.add(map);
        map = new HashMap<>();
            map.put("label","机构课程");
            map.put("value",CATEGORY_TYPE_ORGANIZATION_COURSE);
        categoryTypes.add(map);
        map = new HashMap<>();
            map.put("label","学习资源");
            map.put("value",CATEGORY_TYPE_STUDY_RESOURCE);
        categoryTypes.add(map);
        map = new HashMap<>();
            map.put("label","留学分类");
            map.put("value",CATEGORY_TYPE_STUDY_ABROAD);
        categoryTypes.add(map);
        map = new HashMap<>();
            map.put("label","机构留学");
            map.put("value",CATEGORY_TYPE_ORGANIZATION_STUDY_ABROAD);
        categoryTypes.add(map);
        map = new HashMap<>();
            map.put("label","机构学习资源");
            map.put("value",CATEGORY_TYPE_ORGANIZATION_STUDY_RESOURCE);
        categoryTypes.add(map);
    }
}
