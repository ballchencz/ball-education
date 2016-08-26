package com.ballchen.education.course.service.impl;

import com.ballchen.education.course.dao.ICourseAccessoryDAO;
import com.ballchen.education.course.entity.CourseAccessory;
import com.ballchen.education.course.service.ICourseAccessoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/8/15.
 */
@Service
@Transactional
public class CourseAccessoryServiceImpl implements ICourseAccessoryService {
    @Autowired
    private ICourseAccessoryDAO courseAccessoryDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return courseAccessoryDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(CourseAccessory record) {
        return courseAccessoryDAO.insert(record);
    }

    @Override
    public int insertSelective(CourseAccessory record) {
        return courseAccessoryDAO.insertSelective(record);
    }

    @Override
    public CourseAccessory selectByPrimaryKey(String id) {
        return courseAccessoryDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(CourseAccessory record) {
        return courseAccessoryDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(CourseAccessory record) {
        return courseAccessoryDAO.updateByPrimaryKey(record);
    }

    @Override
    public List<CourseAccessory> getCourseAccessoryByCourseIdAndFileType(String courseId, String fileType) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("courseId",courseId);
        queryMap.put("fileType",fileType);
        return courseAccessoryDAO.getCourseAccessoryByCourseIdAndFileType(queryMap);
    }

    @Override
    public int deleteByPrimaryKeys(String[] ids) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("ids",ids);
        return courseAccessoryDAO.deleteByPrimaryKeys(queryMap);
    }
}
