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
        userInfo.setId(user.getId());
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
        int i = userMapper.insertUserInfo(userInfo_request.getName(),
                userInfo_request.getPassword(), userInfo_request.getEmail(), userInfo_request.getPhone());
        if(i > 0){
            return userInfo_request;
        }else{
            return null;
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
