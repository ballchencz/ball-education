package com.ballchen.education.course.service;

import com.ballchen.education.course.entity.CourseChapter;

/**
 * Created by Administrator on 2016/8/15.
 */
public interface ICourseChapterService {
    int deleteByPrimaryKey(String id);

    int insert(CourseChapter record);

    int insertSelective(CourseChapter record);

    CourseChapter selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(CourseChapter record);

    int updateByPrimaryKey(CourseChapter record);
}
