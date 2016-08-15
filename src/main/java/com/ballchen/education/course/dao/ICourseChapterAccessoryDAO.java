package com.ballchen.education.course.dao;

import com.ballchen.education.course.entity.CourseChapterAccessory;

/**
 * Created by ballchen on 2016/8/15.
 */
public interface ICourseChapterAccessoryDAO {
    int deleteByPrimaryKey(String id);

    int insert(CourseChapterAccessory record);

    int insertSelective(CourseChapterAccessory record);

    CourseChapterAccessory selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CourseChapterAccessory record);

    int updateByPrimaryKey(CourseChapterAccessory record);
}
