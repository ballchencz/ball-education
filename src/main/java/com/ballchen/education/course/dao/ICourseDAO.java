package com.ballchen.education.course.dao;

import com.ballchen.education.course.entity.Course;

import java.util.List;
import java.util.Map;

public interface ICourseDAO {
    int deleteByPrimaryKey(String id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKeyWithBLOBs(Course record);

    int updateByPrimaryKey(Course record);

    List<Course> getCoursePagination(Map<String,Object> queryMap);

    long getCoursePaginationCount(Map<String,Object> queryMap);

    List<Course> selectCourseAccessoryByQueryParam(Map<String,Object> queryMap);

    List<Course> selectCourseUserBasicByQueryParam(Map<String,Object> queryMap);
}