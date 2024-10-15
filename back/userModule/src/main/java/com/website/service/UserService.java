package com.website.service;

import com.website.dto.ArticleInfo;
import com.website.dto.TaskInfo;
import com.website.dto.UserInfo;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class UserService {
    Register register = new Register();
    Login login = new Login();
    UserInfoService userInfoService = new UserInfoService();
    TaskService taskService = new TaskService();
    LikeService likeService = new LikeService();
    CollectService collectService = new CollectService();

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
    // 根据用户ID获取用户的任务列表
    public List<TaskInfo> getAllTask(String id){
        return taskService.getAllTask(id);
    }
    // 完成任务
    public TaskInfo completeTask(String userid, String taskid){
        return taskService.completeTask(userid, taskid);
    }
    // 根据用户ID获取点赞列表
    public List<ArticleInfo> getLikeList(String id){
        return likeService.getLikeList(id);
    }
    // 点赞文章
    public ArticleInfo likeArticle(String userid,String articleid){
        return likeService.likeArticle(userid, articleid);
    }
    // 取消点赞
    public void cancelLike(String userid,String articleid){
        likeService.cancelLike(userid, articleid);
    }
    // 根据用户ID获取收藏列表
    public List<ArticleInfo> getCollectionList(String userid){
        return collectService.getCollectionList(userid);
    }
    // 收藏文章
    public ArticleInfo saveArticle(String userid,String articleid){
        return collectService.saveArticle(userid, articleid);
    }
    // 取消收藏
    public void cancelSave(String userid,String articleid){
        collectService.cancelSave(userid, articleid);
    }

}
