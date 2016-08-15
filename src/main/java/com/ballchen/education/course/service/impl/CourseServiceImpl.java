package com.ballchen.education.course.service.impl;

import com.ballchen.education.course.dao.ICourseDAO;
import com.ballchen.education.course.entity.Course;
import com.ballchen.education.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ballchen on 2016/8/15.
 */
@Service
@Transactional
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDAO courseDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return courseDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Course record) {
        return courseDAO.insert(record);
    }

    @Override
    public int insertSelective(Course record) {
        return courseDAO.insertSelective(record);
    }

    @Override
    public Course selectByPrimaryKey(String id) {
        return courseDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Course record) {
        return courseDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(Course record) {
        return courseDAO.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(Course record) {
        return courseDAO.updateByPrimaryKey(record);
    }
}
