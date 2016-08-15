package com.ballchen.education.course.service;

import com.ballchen.education.course.entity.Course;

/**
 * Created by ballchen on 2016/8/15.
 */
public interface ICourseService {
    int deleteByPrimaryKey(String id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKeyWithBLOBs(Course record);

    int updateByPrimaryKey(Course record);
}
