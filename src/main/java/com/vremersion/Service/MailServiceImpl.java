package com.vremersion.Service;

import com.alibaba.fastjson.JSONObject;
import com.vremersion.Dao.UserActivityDao;
import com.vremersion.Entity.Reset;
import com.vremersion.Entity.User;
import com.vremersion.Util.SecurityUtil;
import com.vremersion.Util.StringUtil;
import com.vremersion.Util.Util;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.security.NoSuchAlgorithmException;
import java.security.Timestamp;

/**
 * @author XP
 * @date 2018/5/14 9:50
 */

@Service
public class MailServiceImpl implements MailService {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender mailSender; // JavaMailSender是java的一个邮件发送工具接口
    @Autowired
    private UserActivityService userActivityService;
    @Resource
    private UserActivityDao userActivityDao;
    @Value("${spring.mail.username}") // 获取对应属性文件中定义的属性值，并将它赋给下面的变量
    private String from;

    @Override
    public void sendSimpleMail(String from, String to, String subject, String content){
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(to);
        message.setSubject(subject);
        message.setText(content);
        try{
            mailSender.send(message);
            logger.info("简单邮件已经发送");
        }catch(Exception e){
            e.printStackTrace();
            e.getMessage();
            logger.info("发送邮件出现异常");
        }
    }

    @Override
    public JSONObject updatePassword(String userName, String password, String newPassword){
        JSONObject jsonObject = new JSONObject();
        if(!StringUtil.isNotEmpty(userName)){
            jsonObject.put("code",200);
            jsonObject.put("success",false);
            jsonObject.put("message","用户名不能为空，请输入你的用户名");
            return jsonObject;
        }
        logger.info("用户名是" + userName);
        if(!StringUtil.isNotEmpty(password)){
            jsonObject.put("code",200);
            jsonObject.put("success",false);
            jsonObject.put("message","密码不能为空，请输入你的密码");
            return jsonObject;
        }
        logger.info(password);
        if(!StringUtil.isNotEmpty(newPassword)){
            jsonObject.put("code",200);
            jsonObject.put("success",false);
            jsonObject.put("message","新密码不能为空，请输入你的新密码");
            return jsonObject;
        }
        logger.info("新密码是" + newPassword);
        User user = userActivityDao.selectUser(userName);
        logger.info("从数据库取出的用户名是" + user.getUserName());
        logger.info("从数据库取出的密码是" + user.getPassword());
        if(user == null){
            jsonObject.put("code",200);
            jsonObject.put("success",false);
            jsonObject.put("message","用户名不存在，请确认您输入的用户名正确");
            return jsonObject;
        }
        try{
            logger.info("进入try块");
            if(SecurityUtil.md5(password).equals(user.getPassword())){
                logger.info("进入if块");
                userActivityDao.updatePassword(userName,SecurityUtil.md5(newPassword));
                User newUser = userActivityDao.selectUpdatePasswordUser(userName,SecurityUtil.md5(newPassword));

                if(newUser == null){
                    jsonObject.put("code",200);
                    jsonObject.put("success",false);
                    jsonObject.put("message","重置密码失败");
                    return jsonObject;
                }
                else{
                    jsonObject.put("code",200);
                    jsonObject.put("success",false);
                    jsonObject.put("message","重置密码成功");
                    return jsonObject;
                }
            }
            else{
                jsonObject.put("code",200);
                jsonObject.put("success",false);
                jsonObject.put("message","用户名和密码不匹配");
                return jsonObject;
            }
        }catch(NoSuchAlgorithmException e){
            jsonObject.put("code",200);
            jsonObject.put("success",false);
            jsonObject.put("message","密码加密出现异常");
            return jsonObject;
        }catch(Exception e){
            e.getMessage();
            e.printStackTrace();
            jsonObject.put("code",200);
            jsonObject.put("success",false);
            jsonObject.put("message","出现异常");
            return jsonObject;
        }
    }


    @Override
    public void forgetHandle(String email){
        String randKey = Util.randString();
        String signature = Util.mdPassword(email + System.currentTimeMillis() + randKey);
//        Reset isRegisteredUser = userActivityDao.selectUserByEmail(email);
//        if(isRegisteredUser == null){
//           logger.info("邮箱不正确");
//           return ;
//        }
        // Long time = 600000L;
        //Timestamp outDate = new Timestamp(System.currentTimeMillis() + (long)(10*60*1000));
        long outtime = System.currentTimeMillis() + (long)(10*60*1000);
        logger.info("进表时的时间" + System.currentTimeMillis());
        logger.info("进表时的过时时间" + outtime);
        Reset isExitedSameEmail = userActivityDao.selectResetByEmail(email);
        if(isExitedSameEmail != null){
            userActivityDao.deleteResetByEmail(email);
        }
        userActivityDao.saveReset(email,signature,outtime);
        logger.info("from is " + from);
        logger.info("to is " + email);
        try {
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(email);
            helper.setSubject("重置密码");
            helper.setText("<html><body>" + "<a href='http://127.0.0.1:8080/resetIndex?signature=" + signature +"&email="
            + email +"'>点击重置</a>" + "</body></html>",true);
            mailSender.send(message);
            logger.info("发送邮件成功");
        }catch(MessagingException me){
            me.getMessage();
            me.printStackTrace();
        }

    }

    @Override
    public String resetIndex(String email, String signature){
        logger.info("email is " + email);
        logger.info("signature is " + signature);
        Reset reset = userActivityDao.selectResetByEmail(email);
        long outtime = reset.getOuttime();
        logger.info("outtime is " + outtime);
        logger.info("currentime is " + System.currentTimeMillis());
        logger.info("reset_signature is " + reset.getSignature());
        if(System.currentTimeMillis() < outtime && reset.getSignature().equals(signature)){

            return "reset";
        }else{
            return "invaild";
        }
    }

    @Override
    public void resetHandle(String password, String email){
        try{
            userActivityDao.resetTable(SecurityUtil.md5(password), email);
        }catch(NoSuchAlgorithmException e){
            e.getMessage();
            e.printStackTrace();
        }

    }



}
