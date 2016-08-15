package com.ballchen.education.course.service.impl;

import com.ballchen.education.course.dao.ICourseChapterAccessoryDAO;
import com.ballchen.education.course.entity.CourseChapterAccessory;
import com.ballchen.education.course.service.ICourseChapterAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ballchen on 2016/8/15.
 */
@Service
@Transactional
public class CourseChapterAccessoryServiceImpl implements ICourseChapterAccessoryService {

    @Autowired
    private ICourseChapterAccessoryDAO courseChapterAccessoryDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return courseChapterAccessoryDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseChapterAccessory record) {
        return courseChapterAccessoryDAO.insert(record);
    }

    @Override
    public int insertSelective(CourseChapterAccessory record) {
        return courseChapterAccessoryDAO.insertSelective(record);
    }

    @Override
    public CourseChapterAccessory selectByPrimaryKey(String id) {
        return courseChapterAccessoryDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseChapterAccessory record) {
        return courseChapterAccessoryDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseChapterAccessory record) {
        return courseChapterAccessoryDAO.updateByPrimaryKey(record);
    }
}
