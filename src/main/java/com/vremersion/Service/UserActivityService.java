package com.vremersion.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * @author XP
 * @date 2018/5/4 16:20
 */

@Service
public interface UserActivityService {
    JSONObject verifyLogin(String userName, String password);
    Map<String,Object> registerUser(String userName, String password, String email);
}
