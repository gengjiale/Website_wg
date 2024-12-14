package com.website.service;

import com.website.dto.UserInfo;
import com.website.entity.User;
import com.website.mapper.UserMapper;
import lombok.AllArgsConstructor;

import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class Login {

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
     * 登录 通过用户名和密码方式
     * @param name
     * @param password
     * @return
     */
    public UserInfo loginByNP(String name, String password){
        User user = userMapper.selectUserByNameAndPassword(name, password);
        if(user == null) return null;
        else {
            return  setUserToUserInfo(user);
        }
    }

    /**
     * 通过第三方方式登录
     * @param type
     * @param unionid
     * @return
     */
    public UserInfo loginByTU(String type, String unionid){
        UserInfo userInfo = new UserInfo();
        userInfo.setName(type);
        return userInfo;
    }
}
