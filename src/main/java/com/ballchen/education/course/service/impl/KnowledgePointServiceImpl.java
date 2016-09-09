package com.ballchen.education.course.service.impl;

import com.ballchen.education.course.dao.IKnowledgePointDAO;
import com.ballchen.education.course.entity.KnowledgePoint;
import com.ballchen.education.course.service.IKnowledgePointService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by ballchen on 2016/8/26.
 */
@Service
@Transactional
public class KnowledgePointServiceImpl implements IKnowledgePointService {

    @Autowired
    private IKnowledgePointDAO knowledgePointDAO;

    @Override
    public int deleteByPrimaryKey(String id) {
        return knowledgePointDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(KnowledgePoint record) {
        return knowledgePointDAO.insert(record);
    }

    @Override
    public int insertSelective(KnowledgePoint record) {
        return knowledgePointDAO.insertSelective(record);
    }

    @Override
    public KnowledgePoint selectByPrimaryKey(String id) {
        return knowledgePointDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(KnowledgePoint record) {
        return knowledgePointDAO.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKeyWithBLOBs(KnowledgePoint record) {
        return knowledgePointDAO.updateByPrimaryKeyWithBLOBs(record);
    }

    @Override
    public int updateByPrimaryKey(KnowledgePoint record) {
        return knowledgePointDAO.updateByPrimaryKey(record);
    }

    @Override
    public int deleteByPrimaryKeys(String [] ids) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("ids",ids);
        return knowledgePointDAO.deleteByPrimaryKeys(queryMap);
    }

    @Override
    public List<KnowledgePoint> getKnowledgePointByCourseId(String id) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("courseId",id);
        return knowledgePointDAO.selectBySelective(queryMap);
    }
}
