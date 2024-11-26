package com.website.mapper;

import com.website.dto.UserInfo;
import com.website.entity.User;
import org.springframework.stereotype.Repository;

@Repository
public interface UserMapper {
    // 查询
    User selectUserByNameAndPassword(String name, String password); //
    User selectUserById(int id); //
    String selectNameById(int id);

    // 增加
    int insertUserInfo(String name, String password, String email, String phone); //

    //更新
    int updateUser(int id, String name, String password, String email, String phone);

}
