package com.website.mapper;

import com.website.dto.UserInfo;
import com.website.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    // 查询
    User selectUserByNameAndPassword(String name, String password); //
    User selectUserById(String id); //
    String selectNameById(String id);

    // 增加
    int insertUserInfo(String id, String name, String password); //

    //更新
    int updateUser(String id, String name, String password);

}
