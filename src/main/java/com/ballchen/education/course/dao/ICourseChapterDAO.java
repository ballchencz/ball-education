package com.ballchen.education.course.dao;

import com.ballchen.education.course.entity.CourseChapter;

public interface ICourseChapterDAO {
    int deleteByPrimaryKey(String id);

    int insert(CourseChapter record);

    int insertSelective(CourseChapter record);

    CourseChapter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CourseChapter record);

    int updateByPrimaryKey(CourseChapter record);
}