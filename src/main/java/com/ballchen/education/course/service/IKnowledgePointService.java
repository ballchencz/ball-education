package com.ballchen.education.course.service;

import com.ballchen.education.course.entity.KnowledgePoint;

import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/8/26.
 */
public interface IKnowledgePointService {
    int deleteByPrimaryKey(String id);

    int insert(KnowledgePoint record);

    int insertSelective(KnowledgePoint record);

    KnowledgePoint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(KnowledgePoint record);

    int updateByPrimaryKeyWithBLOBs(KnowledgePoint record);

    int updateByPrimaryKey(KnowledgePoint record);

    int deleteByPrimaryKeys(String [] ids);

    /**
     * 根据课程ID获得课程知识点
     * @param id 课程ID
     * @return List<KnowledgePoint> 课程知识点
     */
    List<KnowledgePoint> getKnowledgePointByCourseId(String id);
}
