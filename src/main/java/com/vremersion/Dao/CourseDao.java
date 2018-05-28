package com.vremersion.Dao;

import com.vremersion.Entity.Course;
import org.apache.ibatis.annotations.Mapper;

import java.util.HashMap;
import java.util.List;

/**
 * @author XP
 * @date 2018/5/2 15:47
 */

@Mapper
public interface CourseDao {
    Course getCourseInfo();
    void createCourse(Course course);
    HashMap getCourseByName(HashMap<String,Object> map);
}
