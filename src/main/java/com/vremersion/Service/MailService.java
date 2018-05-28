package com.vremersion.Service;

import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

/**
 * @author XP
 * @date 2018/5/14 9:47
 */

@Service
public interface MailService {
//    JSONObject findPwd(String email);
    void sendSimpleMail(String from, String to, String subject, String content);
    JSONObject updatePassword(String userName, String password, String newPassword);
    void forgetHandle(String email);
    String resetIndex(String email, String signature);
    void resetHandle(String password, String email);
//    void sendNormalEmail(String title, boolean titleWithName, String content, boolean contentWithName, String email);
//    void sendFindSuc(final String email, String pwd, String url);
}
