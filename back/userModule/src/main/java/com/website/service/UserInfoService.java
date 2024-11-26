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
        userInfo.setId(user.getId());
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
    public UserInfo getUserInfoById(int id){
        User user = userMapper.selectUserById(id);
        if(user == null) return null;
        else{
            return setUserToUserInfo(user);
        }
    }

    public String getUserName(int id){
        return userMapper.selectNameById(id);
    }

    public UserInfo updateUserInfo(int id, UserInfo userInfo){
        int i = userMapper.updateUser(id, userInfo.getName(), userInfo.getPassword(), userInfo.getEmail(), userInfo.getPhone());
        if ( i > 0) return userInfo;
        else return null;
    }
}
