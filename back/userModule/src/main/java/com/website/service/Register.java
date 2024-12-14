package com.website.service;

import com.website.dto.UserInfo;
import com.website.entity.User;
import com.website.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Register {

    UserMapper userMapper;

    UserInfo setUserToUserInfo(User user){
        UserInfo userInfo = new UserInfo();
        userInfo.setUserId(user.getUserId());
        userInfo.setName(user.getName());
        userInfo.setPassword(user.getPassword());
        userInfo.setEmail(user.getEmail());
        userInfo.setPhone(user.getPhone());
        return userInfo;
    }

    /**
     * 账号密码注册
     * @param userInfo_request
     * @return
     */
    public UserInfo register1(UserInfo userInfo_request){
        String name = userInfo_request.getName();
        String password = userInfo_request.getPassword();
        User user = userMapper.selectUserByNameAndPassword(name, password);
        if(user != null){
            return null;
        }else{
            long currentTimeMillis = System.currentTimeMillis();
            String id = "u" + currentTimeMillis;
            int i = userMapper.insertUserInfo(id,userInfo_request.getName(), userInfo_request.getPassword());
            if(i > 0){
                userInfo_request.setUserId(id);
                return userInfo_request;
            }else{
                return null;
            }
        }
    }

    /**
     * 其他方式注册
     * @param type
     * @param unionid
     * @param userInfo_request
     * @return
     */
    public UserInfo register2(String type, String unionid, UserInfo userInfo_request){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(type);
        return userInfo;
    }
}
