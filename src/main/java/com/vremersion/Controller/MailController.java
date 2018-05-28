package com.vremersion.Controller;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.vremersion.Entity.Reset;
import com.vremersion.Service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;

/**
 * @author XP
 * @date 2018/5/14 9:36
 */

@Controller
public class MailController {
    @Autowired
    MailService mailService;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

//    @RequestMapping(value="/findPwdHandle")
//    public JSONObject findPwd(String email){
//        JSONObject jsonObject = mailService.findPwd(email);
//        return jsonObject;
//    }

    @RequestMapping(value="/updatePassword",method = RequestMethod.GET)
    @ResponseBody
    public JSONObject updatePassword(String userName, String password, String newPassword){
        JSONObject jsonObject = mailService.updatePassword(userName,password,newPassword);
        return jsonObject;
    }
    @RequestMapping(value="/forgetHandle")
    @ResponseBody
    public String  forgetHandle(String email){
//        new Thread(new Runnable(){
//            @Override
//            public void run(){
//                mailService.forgetHandle(email);
//            }
//        }).start();
        new Thread(() -> mailService.forgetHandle(email)).start();
        // mailService.forgetHandle(email);
        return "邮件已经发送";
    }

    @RequestMapping("/resetIndex")
    public String resetIndex(String email, String signature, HttpServletRequest request){
        request.getSession().setAttribute("email",email);
        String emailToReset = request.getSession().getAttribute("email").toString();
        logger.info("Request中的email是" + emailToReset);
        String result =  mailService.resetIndex(email,signature);
        return result;
    }

    @RequestMapping(value="/resetHandle",method=RequestMethod.POST)
    @ResponseBody
    public String resetHandle(String password, String cpassword,HttpServletRequest request){
        if(password.equals(cpassword)){
            String email = request.getSession().getAttribute("email").toString();
            mailService.resetHandle(password, email);
            return "重置密码成功";
        }else{
            return "两次输入密码要一样";
        }
    }
}
