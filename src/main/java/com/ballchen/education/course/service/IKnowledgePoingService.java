package com.ballchen.education.course.service;

import com.ballchen.education.course.entity.KnowledgePoint;

import java.util.Map;

/**
 * Created by ballchen on 2016/8/26.
 */
public interface IKnowledgePoingService {
    int deleteByPrimaryKey(String id);

    int insert(KnowledgePoint record);

    int insertSelective(KnowledgePoint record);

    KnowledgePoint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(KnowledgePoint record);

    int updateByPrimaryKeyWithBLOBs(KnowledgePoint record);

    int updateByPrimaryKey(KnowledgePoint record);

    int deleteByPrimaryKeys(String [] ids);
}
