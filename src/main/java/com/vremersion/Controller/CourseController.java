package com.vremersion.Controller;

import com.alibaba.fastjson.JSONObject;
import com.vremersion.Entity.Course;
import com.vremersion.Service.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;

/**
 * @author XP
 * @date 2018/5/2 11:08
 */

@Controller
public class CourseController {
    @Autowired
    CourseService courseService;

    @RequestMapping(value = "/api/course/getCourseInfo", method = RequestMethod.GET)
    public @ResponseBody HashMap getCourseInfo(){
        HashMap map = courseService.getCourseInfo();
        return map;
    }

//    @RequestMapping(value = "/api/course/data", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject getCourseData(long taskId, String jobId, HttpServletResponse response){
//        JSONObject jsonObject = courseService.getCourseData(taskId,jobId,response);
//        return jsonObject;
//    }    @RequestMapping(value = "/api/course/data", method = RequestMethod.GET)
////    @ResponseBody
////    public JSONObject getCourseData(long taskId, String jobId, HttpServletResponse response){
////        JSONObject jsonObject = courseService.getCourseData(taskId,jobId,response);
////        return jsonObject;
////    }

//    @RequestMapping(value = "/api/course/createCourse", method = RequestMethod.GET)
//    @ResponseBody
//    public JSONObject createCourse(Course course){
//        JSONObject jsonObject = courseService.createCourse(course);
//        return jsonObject;
//    }

//    @RequestMapping(value = "/downlaod", method = RequestMethod.GET)
//    // @ResponseBody
//    public void download(long taskId, String jobId, HttpServletResponse response){
//        courseService.download(taskId,jobId,response);
//    }
//
//    @RequestMapping(value = "/downloadResource", method = RequestMethod.GET)
//    public void downloadResource(HttpServletResponse response, String fileName){
//
//    }
}
