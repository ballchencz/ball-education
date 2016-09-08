package com.ballchen.education.course.service;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.course.entity.Course;
import com.ballchen.education.course.entity.KnowledgePoint;
import org.junit.Test;

import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/8/15.
 */
public interface ICourseService {

    /**
     * 根据id删除课程
     * @param id 课程ID
     * @return int
     */
    int deleteByPrimaryKey(String id);

    /**
     * 添加课程
     * @param record 课程实体类
     * @return int
     */
    int insert(Course record);

    /**
     * 根据数据添加课程
     * @param record 课程实体类
     * @return int
     */
    int insertSelective(Course record,Accessory accessory,String [] userBasicIds);

    /**
     * 根据id获得课程
     * @param id 课程ID
     * @return Course
     */
    Course selectByPrimaryKey(String id);

    /**
     * 根据数据修改课程
     * @param record 课程实体类
     * @return int
     */
    int updateByPrimaryKeySelective(Course record,Accessory accessory,String [] userBasicIds);

    /**
     * 根据数据修改课程（带description）
     * @param record 课程实体类
     * @return int
     */
    int updateByPrimaryKeyWithBLOBs(Course record);

    /**
     * 根据课程ID修改课程
     * @param record 课程实体类
     * @return int
     */
    int updateByPrimaryKey(Course record);

    /**
     * 获得课程分页数据
     * @param course 课程实体类
     * @param pageHelper 分页数据
     * @return List<Course>
     */
    List<Course> getCoursePagination(Course course, PageHelper pageHelper);

    /**
     * 获得课程分页数据总数
     * @param course 课程实体类
     * @return long
     */
    long getCoursePaginationCount(Course course);

    /**
     * 根据课程ID获得课程和课程附件
     * @param id 课程ID
     * @return Course
     */
    Course selectCourseAccessoryByCourseId(String id);

    /**
     *根据课程ID获得课程和课程用户
     * @param id 课程ID
     * @return Course
     */
    Course selectCourseUserBasicByCourseId(String id);


    void testTransactional(List<KnowledgePoint> knowledgePoints);

}
