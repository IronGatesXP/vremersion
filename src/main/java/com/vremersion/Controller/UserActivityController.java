package com.vremersion.Controller;


import com.alibaba.fastjson.JSONObject;
import com.vremersion.Service.UserActivityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 *@author XP
 *@date 2018/5/4 15:56
 */

@Controller
public class UserActivityController {
    @Autowired
    UserActivityService userActivityService;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    @RequestMapping(value="/loginHandle", method= RequestMethod.POST)
    @ResponseBody
    public JSONObject loginHandle(String userName, String password,HttpServletResponse response) throws Exception{
        JSONObject jsonObject = userActivityService.verifyLogin(userName,password);
        return jsonObject;
    }

    @RequestMapping(value="/registerHandle", method=RequestMethod.POST)
    @ResponseBody
    public Map<String,Object> register(String userName, String password, String email){
        Map<String,Object> map = userActivityService.registerUser(userName,password,email);
        return map;
    }

//    public String loginHandle(String userName, String password,HttpServletResponse response) throws Exception{
//        JSONObject jsonObject = userActivityService.verifyLogin(userName,password);
//        return "/test";
//    }
}

