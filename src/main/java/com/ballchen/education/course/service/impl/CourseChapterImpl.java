package com.ballchen.education.course.service.impl;

import com.ballchen.education.course.dao.ICourseChapterDAO;
import com.ballchen.education.course.entity.CourseChapter;
import com.ballchen.education.course.service.ICourseChapterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by Administrator on 2016/8/15.
 */
@Service
@Transactional
public class CourseChapterImpl implements ICourseChapterService {

    @Autowired
    private ICourseChapterDAO courseChapterDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return courseChapterDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseChapter record) {
        return courseChapterDAO.insert(record);
    }

    @Override
    public int insertSelective(CourseChapter record) {
        return courseChapterDAO.insertSelective(record);
    }

    @Override
    public CourseChapter selectByPrimaryKey(String id) {
        return courseChapterDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseChapter record) {
        return courseChapterDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseChapter record) {
        return courseChapterDAO.updateByPrimaryKey(record);
    }
}
