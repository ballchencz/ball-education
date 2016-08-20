package com.ballchen.education.course.service.impl;

import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.course.dao.ICourseDAO;
import com.ballchen.education.course.entity.Course;
import com.ballchen.education.course.service.ICourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;

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

    @Override
    public List<Course> getCoursePagination(Course course, PageHelper pageHelper) {
        Map<String,Object> queryMap = AdminUtils.parsePOJOtoMap(course);
        queryMap.putAll(AdminUtils.parsePOJOtoMap(pageHelper));
        List<Course> courses = this.courseDAO.getCoursePagination(queryMap);
        return courses;
    }

    @Override
    public long getCoursePaginationCount(Course course) {
        Map<String,Object> queryMap = AdminUtils.parsePOJOtoMap(course);
        Long count = this.courseDAO.getCoursePaginationCount(queryMap);
        return count;
    }
}
