package com.ballchen.education.course.dao;

import com.ballchen.education.course.entity.CourseAccessory;

public interface ICourseAccessoryDAO {
    int deleteByPrimaryKey(String id);

    int insert(CourseAccessory record);

    int insertSelective(CourseAccessory record);

    CourseAccessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CourseAccessory record);

    int updateByPrimaryKey(CourseAccessory record);
}