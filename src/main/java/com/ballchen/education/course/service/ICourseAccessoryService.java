package com.ballchen.education.course.service;

import com.ballchen.education.course.entity.CourseAccessory;

/**
 * Created by ballchen on 2016/8/15.
 */
public interface ICourseAccessoryService {
    int deleteByPrimaryKey(String id);

    int insert(CourseAccessory record);

    int insertSelective(CourseAccessory record);

    CourseAccessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CourseAccessory record);

    int updateByPrimaryKey(CourseAccessory record);
}
