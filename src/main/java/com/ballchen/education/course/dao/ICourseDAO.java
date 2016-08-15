package com.ballchen.education.course.dao;

import com.ballchen.education.course.entity.Course;

public interface ICourseDAO {
    int deleteByPrimaryKey(String id);

    int insert(Course record);

    int insertSelective(Course record);

    Course selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Course record);

    int updateByPrimaryKeyWithBLOBs(Course record);

    int updateByPrimaryKey(Course record);
}