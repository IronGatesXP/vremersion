package com.vremersion.Service;

import com.alibaba.fastjson.JSONObject;
import com.vremersion.Dao.CourseDao;
import com.vremersion.Entity.Course;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.nio.file.FileSystem;
import java.util.HashMap;
import java.util.List;


/**
 * @author XP
 * @date 2018/5/2 15:43
 */

@Service
public class CourseServiceImpl implements CourseService{

    Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);
    // ?????
    private static FileSystem fileSystem = null;

    @Resource
    CourseDao courseDao;

    @Override
    public HashMap getCourseInfo(){
        Course res = courseDao.getCourseInfo();
        HashMap map = new HashMap();
        map.put("courseInfo", res);
        return map;
    }

//    @Override
//    public JSONObject getCourseData(long taskId, String jobId, HttpServletResponse response){
//        JSONObject jsonObject = new JSONObject();
//    }


}
