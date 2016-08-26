package com.ballchen.education.course.service;

import com.ballchen.education.course.entity.CourseAccessory;

import java.util.List;

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

    /**
     * 根据课程id和文件类型获得课程-附件关联
     * @param courseId 课程ID
     * @param fileType 文件类型
     * @return List<CourseAccessory>
     */
    List<CourseAccessory> getCourseAccessoryByCourseIdAndFileType(String courseId,String fileType);

    /**
     * 根据id数组删除
     * @param ids id数组
     * @return int
     */
    int deleteByPrimaryKeys(String [] ids);
}
