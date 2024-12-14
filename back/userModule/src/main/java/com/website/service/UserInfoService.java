package com.website.service;

import com.website.dto.UserInfo;
import com.website.entity.User;
import com.website.mapper.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class UserInfoService {

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
     * 根据用户ID获取用户信息
     * @param id
     * @return
     */
    public UserInfo getUserInfoById(String id){
        User user = userMapper.selectUserById(id);
        if(user == null) return null;
        else{
            return setUserToUserInfo(user);
        }
    }

    public String getUserName(String id){
        return userMapper.selectNameById(id);
    }

    public UserInfo updateUserInfo(String id, UserInfo userInfo){
        int i = userMapper.updateUser(id, userInfo.getName(), userInfo.getPassword());
        if ( i > 0) return userInfo;
        else return null;
    }
}
