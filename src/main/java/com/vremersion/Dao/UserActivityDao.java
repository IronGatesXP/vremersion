package com.vremersion.Dao;

import com.vremersion.Entity.Reset;
import com.vremersion.Entity.User;
import org.apache.ibatis.annotations.Param;

/**
 * @author XP
 * @date 2018/5/4 16:37
 */
public interface UserActivityDao {
    User selectUser(String userName);
    void register(User user);
    void updatePassword(@Param("userName")String userName, @Param("newPassword")String newPassword);
    User selectUpdatePasswordUser(@Param("userName")String userName, @Param("password")String password);
    Reset selectUserByEmail(String email);
    void saveReset(@Param("email")String email, @Param("signature")String signature, @Param("outtime")long outtime);
    Reset verifyReset(String email);
    void resetTable(@Param("password")String password,@Param("email")String email);
    Reset selectResetByEmail(String email);
    void deleteResetByEmail(String email);
}
