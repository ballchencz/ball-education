package com.ballchen.education.course.service.impl;

import com.ballchen.education.course.dao.ICourseUserBasicDAO;
import com.ballchen.education.course.entity.CourseUserBasic;
import com.ballchen.education.course.service.ICourseUserBasicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by ballchen on 2016/8/15.
 */
@Service
@Transactional
public class CourseUserBasicServiceImpl implements ICourseUserBasicService {

    @Autowired
    private ICourseUserBasicDAO courseUserBasicDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return courseUserBasicDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseUserBasic record) {
        return courseUserBasicDAO.insert(record);
    }

    @Override
    public int insertSelective(CourseUserBasic record) {
        return courseUserBasicDAO.insertSelective(record);
    }

    @Override
    public CourseUserBasic selectByPrimaryKey(String id) {
        return courseUserBasicDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseUserBasic record) {
        return courseUserBasicDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseUserBasic record) {
        return courseUserBasicDAO.updateByPrimaryKey(record);
    }
}
