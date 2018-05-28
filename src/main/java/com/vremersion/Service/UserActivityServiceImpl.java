package com.vremersion.Service;

import com.alibaba.fastjson.JSONObject;
import com.vremersion.Dao.UserActivityDao;
import com.vremersion.Entity.User;
// import org.apache.catalina.security.SecurityUtil;
import com.vremersion.Util.SecurityUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author XP
 * @date 2018/5/4 16:25
 */

@Service
public class UserActivityServiceImpl implements UserActivityService{
    @Resource
    UserActivityDao userActivityDao;

    Logger logger = LoggerFactory.getLogger(this.getClass());

    public JSONObject verifyLogin(String userName, String password){
        JSONObject jsonObject = new JSONObject();
        User user = userActivityDao.selectUser(userName);
        if(user == null){
            jsonObject.put("code",200);
            jsonObject.put("success","false");
            jsonObject.put("message","你还没有注册或用户名错误");
            return jsonObject;
        }else{
            try{
//                logger.info("加密后输入的密码" + SecurityUtil.md5(password));
//                logger.info("加密后数据库中的密码" + user.getPassword());
                if(SecurityUtil.md5(password).equals(user.getPassword())){
                    logger.info("密码验证相等");
                    jsonObject.put("code",200);
                    jsonObject.put("success","true");
                    jsonObject.put("message","成功登录");
                    return jsonObject;
                }
                else {
                    jsonObject.put("code",200);
                    jsonObject.put("success","false");
                    jsonObject.put("message","密码错误，登录失败");
                    return jsonObject;
                }
            }catch(NoSuchAlgorithmException e){
                jsonObject.put("code",200);
                jsonObject.put("success","false");
                jsonObject.put("message","MD5加密异常");
                return jsonObject;
            } catch(Exception e){
                jsonObject.put("code",200);
                jsonObject.put("success","false");
                jsonObject.put("message","产生异常");
                return jsonObject;
            }
        }
    }

    public Map<String,Object> registerUser(String userName, String password, String email){
        Map<String,Object> map = new HashMap<>();
        try{
            String encyptPassword = SecurityUtil.md5(password);
            User user = new User(userName,encyptPassword,email);
            // 有bug，因为插入时，数据可以重复，但使用select时，如果有多个重名的用户，由于返回的数据是放入一个User类中，所以只能有一条数据，
            // 不能出现多条数据重名的情况，否则会抛出异常，所以这里相当于有一个隐性条件，那就是用户名不能重复。而且抛出异常的信息填入也有错，
            // 不一定是MD5加密异常
            User isRegistered = userActivityDao.selectUser(userName);
            if(isRegistered != null){
                map.put("success",false);
                map.put("message","此用户名已被注册");
                return map;
            }else{
                userActivityDao.register(user);
                logger.info("插入数据");
            }
            User registerUser = userActivityDao.selectUser(userName);
            logger.info("选择数据");
            if(registerUser != null){
                map.put("success",true);
                map.put("message","注册成功");
                logger.info("注册成功");
                return map;
            }else{
                map.put("success",false);
                map.put("message","注册失败");
                return map;
            }

        }catch(NoSuchAlgorithmException e){
            map.put("success",false);
            map.put("message","MD5加密产生异常");
            logger.info("出现加密异常");
            return map;
        } catch(Exception e){
            map.put("success",false);
            map.put("message","产生异常");
            logger.info("出现异常");
            return map;
        }
    }

}
