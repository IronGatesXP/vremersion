package com.vremersion.Service;


import com.alibaba.fastjson.JSONObject;
import com.vremersion.Entity.Course;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author XP
 * @date 2018/5/2 11:16
 */

@Service
public interface CourseService {
    HashMap getCourseInfo();
//    JSONObject getCourseData(long taskId, String jobId, HttpServletResponse response);
//    JSONObject createCourse(Course course);
    // void download(long taskId, String jobId, HttpServletResponse response);
}
