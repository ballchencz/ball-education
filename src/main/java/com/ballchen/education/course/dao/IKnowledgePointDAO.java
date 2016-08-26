package com.ballchen.education.course.dao;

import com.ballchen.education.course.entity.KnowledgePoint;

import java.util.Map;

public interface IKnowledgePointDAO {
    int deleteByPrimaryKey(String id);

    int insert(KnowledgePoint record);

    int insertSelective(KnowledgePoint record);

    KnowledgePoint selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(KnowledgePoint record);

    int updateByPrimaryKeyWithBLOBs(KnowledgePoint record);

    int updateByPrimaryKey(KnowledgePoint record);

    int deleteByPrimaryKeys(Map<String,Object> queryMap);
}