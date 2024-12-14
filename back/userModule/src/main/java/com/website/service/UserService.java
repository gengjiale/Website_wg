package com.website.service;

import com.website.dto.ArticleInfo;
import com.website.dto.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@AllArgsConstructor
public class UserService {
    Register register;
    Login login;
    UserInfoService userInfoService;
    LikeService likeService ;
    CollectService collectService ;

    // 根据用户ID获取用户信息
    public UserInfo getUserInfoById(String id){
        return userInfoService.getUserInfoById(id);
    }
    // 根据用户ID获取用户名
    public String getUserName(String id){
        return userInfoService.getUserName(id);
    }
    // 注册
    public UserInfo register(String type, String uniond, UserInfo userInfo){
        if(type == null && uniond == null){
            // 账号密码登录
            return register.register1(userInfo);
        }else if(type != null && uniond != null){
            // 其他方式登录
            return register.register2(type, uniond, userInfo);
        }else {
            // 报错
            return null;
        }
    }
    // 登录
    public UserInfo login(String name, String password, String type, String uniond){
        if(name != null && password != null && type == null && uniond == null){
            // 账号密码登录
            return login.loginByNP(name, password);
        }else if(name == null && password == null && type != null && uniond != null){
            // 其他方式登录
            return login.loginByTU(type, uniond);
        }else {
            // 报错
            return null;
        }
    }
    // 根据用户ID更新用户信息
    public UserInfo updateUserInfo(String id, UserInfo userInfo){
        return userInfoService.updateUserInfo(id, userInfo);
    }






    // 根据用户ID获取点赞列表
    public List<ArticleInfo> getLikeList(int id){
        return likeService.getLikeList(id);
    }
    // 点赞文章
    public ArticleInfo likeArticle(int userid,String articleid){
        return likeService.likeArticle(userid, articleid);
    }
    // 取消点赞
    public void cancelLike(int userid,String articleid){
        likeService.cancelLike(userid, articleid);
    }


    // 根据用户ID获取收藏列表
    public List<ArticleInfo> getCollectionList(int userid){
        return collectService.getCollectList(userid);
    }
    // 收藏文章
    public ArticleInfo saveArticle(int userid,String articleid){
        return collectService.collectArticle(userid, articleid);
    }
    // 取消收藏
    public void cancelSave(int userid,String articleid){
        collectService.cancelCollect(userid, articleid);
    }

}
