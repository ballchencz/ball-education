package com.ballchen.education.course.service.impl;

import com.ballchen.education.accessory.entity.Accessory;
import com.ballchen.education.accessory.service.IAccessoryService;
import com.ballchen.education.admin.entity.PageHelper;
import com.ballchen.education.admin.utils.AdminUtils;
import com.ballchen.education.consts.PublicConsts;
import com.ballchen.education.course.consts.CourseConsts;
import com.ballchen.education.course.dao.ICourseDAO;
import com.ballchen.education.course.entity.Course;
import com.ballchen.education.course.entity.CourseAccessory;
import com.ballchen.education.course.entity.CourseUserBasic;
import com.ballchen.education.course.service.ICourseAccessoryService;
import com.ballchen.education.course.service.ICourseService;
import com.ballchen.education.course.service.ICourseUserBasicService;
import com.ballchen.education.utils.PublicUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by ballchen on 2016/8/15.
 */
@Service
@Transactional
public class CourseServiceImpl implements ICourseService {

    @Autowired
    private ICourseDAO courseDAO;
    @Autowired
    private IAccessoryService accessoryService;
    @Autowired
    private ICourseAccessoryService courseAccessoryService;
    @Autowired
    private ICourseUserBasicService courseUserBasicService;

    @Override
    public int deleteByPrimaryKey(String id) {
        return courseDAO.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Course record) {
        return courseDAO.insert(record);
    }

    @Override
    public int insertSelective(Course record,Accessory accessory,String [] userBasicIds) {
        //课程添加结果
        int courseInsertResult = -1;
        courseInsertResult = courseDAO.insertSelective(record);
        if(accessory!=null){//如果附件不为空，说明上传了课程图片，添加附件，并和Course做关联
            this.accessoryService.insertSelective(accessory);
            CourseAccessory courseAccessory = new CourseAccessory();
            courseAccessory.setId(UUID.randomUUID().toString());
            courseAccessory.setAccessoryId(accessory.getId());
            courseAccessory.setCourseId(record.getId());
            courseAccessoryService.insertSelective(courseAccessory);
        }
        if(userBasicIds!=null && userBasicIds.length>0){
            insertCourseUserBasic(record,userBasicIds);
        }
        return courseInsertResult;
    }

    @Override
    public Course selectByPrimaryKey(String id) {
        return courseDAO.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Course record,Accessory accessory,String [] userBasicIds) {
        int i;
        /*------------------------------------------------------------------------*/
        /*---------------解除课程与附件的关系，添加新的关联（附件不为空的情况下）-------------*/
        if(record!=null && accessory!=null){
            List<CourseAccessory> courseAccessories = this.courseAccessoryService.getCourseAccessoryByCourseIdAndFileType(record.getId(), CourseConsts.COURSE_FILE_TYPE_COURE_PICTURE);
            //首先把课程图片附件ID设置为空,修改课程
            if(courseAccessories!=null && courseAccessories.size()>0){
                String [] ids = new String[courseAccessories.size()];
                String [] accessoryIds = new String[courseAccessories.size()];
                for(int j=0;j<courseAccessories.size();j++){
                    ids[j] = courseAccessories.get(j).getId();
                    accessoryIds[j] = courseAccessories.get(j).getAccessoryId();
                }
                /*删除课程附件关联*/
                courseAccessoryService.deleteByPrimaryKeys(ids);

                /*删除附件*/
                accessoryService.deleteByPrimaryKeys(accessoryIds);

                //再然后添加附件
                accessoryService.insertSelective(accessory);

                //然后在添加课程附件关联
                CourseAccessory courseAccessory = new CourseAccessory();
                courseAccessory.setId(UUID.randomUUID().toString());
                courseAccessory.setCourseId(record.getId());
                courseAccessory.setAccessoryId(accessory.getId());
                courseAccessoryService.insertSelective(courseAccessory);
            }else{
                //然后在添加课程附件关联
                accessoryService.insertSelective(accessory);

                CourseAccessory courseAccessory = new CourseAccessory();
                courseAccessory.setId(UUID.randomUUID().toString());
                courseAccessory.setCourseId(record.getId());
                courseAccessory.setAccessoryId(accessory.getId());
                courseAccessoryService.insertSelective(courseAccessory);
            }
        }
        if(userBasicIds!=null && userBasicIds.length>0){
                /*首先删除课程和用户的关联*/
            courseUserBasicService.deleteByPrimaryKeys(new String[]{record.getId()},userBasicIds);
                /*然后添加课程用户关联*/
            insertCourseUserBasic(record,userBasicIds);
        }
        i=courseDAO.updateByPrimaryKeySelective(record);

        /*------------------------------------------------------------------------------------*/
        return  i;
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
        Map<String,Object> queryMap = PublicUtils.parsePOJOtoMap(course);
        queryMap.putAll(PublicUtils.parsePOJOtoMap(pageHelper));
        List<Course> courses = this.courseDAO.getCoursePagination(queryMap);
        return courses;
    }

    @Override
    public long getCoursePaginationCount(Course course) {
        Map<String,Object> queryMap = PublicUtils.parsePOJOtoMap(course);
        Long count = this.courseDAO.getCoursePaginationCount(queryMap);
        return count;
    }

    @Override
    public Course selectCourseAccessoryByCourseId(String id) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("courseId",id);
        Map<String,Object> fileServerProperties = PublicUtils.getUseableFileServerProperties(this.getClass().getClassLoader().getResource("/").getPath());
        queryMap.put("fileServerType",fileServerProperties.get("type"));
        queryMap.put("fileType",CourseConsts.COURSE_FILE_TYPE_COURE_PICTURE);
        List<Course> courses = this.courseDAO.selectCourseAccessoryByQueryParam(queryMap);
        if(courses!=null && courses.size()>0){
            return courses.get(0);
        }else{
            return null;
        }
    }

    @Override
    public Course selectCourseUserBasicByCourseId(String id) {
        Map<String,Object> queryMap = new HashMap<>();
        queryMap.put("courseId",id);
        List<Course> courses = this.courseDAO.selectCourseUserBasicByQueryParam(queryMap);
        if(courses!=null && courses.size()>0){
            return courses.get(0);
        }else{
            return null;
        }
    }


    private void insertCourseUserBasic(Course course,String [] userBasicIds){
        for(String userBasicId : userBasicIds){
            CourseUserBasic courseUserBasic = new CourseUserBasic();
            courseUserBasic.setId(UUID.randomUUID().toString());
            courseUserBasic.setCourseId(course.getId());
            courseUserBasic.setUserBasicId(userBasicId);
            courseUserBasicService.insertSelective(courseUserBasic);
        }
    }
}
