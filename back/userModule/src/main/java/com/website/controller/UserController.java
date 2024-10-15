package com.website.controller;

import com.website.dto.ArticleInfo;
import com.website.dto.TaskInfo;
import com.website.dto.UserInfo;
import com.website.service.UserService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("/api/v1/user")
@RestController
@AllArgsConstructor
public class UserController {

    UserService userService;

    /**
     * 根据用户id获取对应用户的全部信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    @ResponseBody
    public UserInfo getUserInfoById(@PathVariable("id") String id){
        return userService.getUserInfoById(id);
    }

    /**
     * 根据用户id获取对应用户的姓名
     * @param id
     * @return
     */
    @GetMapping("/{id}/name")
    @ResponseBody
    public String getUserName(@PathVariable("id") String id){
        return userService.getUserName(id);
    }

    /**
     * 注册
     * @param type
     * @param unionid
     * @return
     */
    @PostMapping
    @ResponseBody
    public UserInfo regist(@RequestParam(required = false) String type, @RequestParam(required = false) String unionid,
                           @RequestBody UserInfo userInfo){
        return userService.register(type, unionid, userInfo);
    }

    /**
     * 登录
     * @param name
     * @param password
     * @param type
     * @param unionid
     * @return
     */
    @GetMapping
    @ResponseBody
    public UserInfo login(@RequestParam(required = false) String name, @RequestParam(required = false) String password,
                          @RequestParam(required = false) String type, @RequestParam(required = false) String unionid){
        return userService.login(name, password, type, unionid);
    }

    /**
     * 更新用户信息
     * @param id
     * @param userInfo
     * @return
     */
    @PutMapping("/{id}")
    @ResponseBody
    public UserInfo updateUserInfo(@PathVariable("id") String id, @RequestBody UserInfo userInfo){
        return userService.updateUserInfo(id, userInfo);
    }

    /**
     * 根据用户ID获取任务列表
     * @param id
     * @return
     */
    @GetMapping("/{id}/task")
    @ResponseBody
    public List<TaskInfo> getAllTask(@PathVariable("id") String id){
        return userService.getAllTask(id);
    }

    /**
     * 完成任务。
     * @param userid
     * @param taskid
     * @return
     */
    @PutMapping("/{userid}/task/{taskid}")
    @ResponseBody
    public TaskInfo completeTask(@PathVariable("userid") String userid, @PathVariable("taskid") String taskid){
        return userService.completeTask(userid, taskid);
    }

    /**
     * 根据用户ID获取点赞文章列表
     * @param id
     * @return
     */
    @GetMapping("/{id}/like")
    @ResponseBody
    public List<ArticleInfo> getLikeList(@PathVariable("id") String id){
        return userService.getLikeList(id);
    }

    /**
     * 点赞文章
     * @param userid
     * @param taskid
     * @return
     */
    @PostMapping("/{userid}/like/{articleid}")
    @ResponseBody
    public ArticleInfo likeArticle(@PathVariable("userid") String userid, @PathVariable("taskid") String taskid){
        return userService.likeArticle(userid, taskid);
    }


    /**
     * 取消点赞
     * @param userid
     * @param taskid
     */
    @DeleteMapping("/{userid}/like/{articleid}")
    public void cancelLike(@PathVariable("userid") String userid, @PathVariable("taskid") String taskid){

    }

    /**
     * 根据用户ID获取收藏列表
     * @param id
     * @return
     */
    @GetMapping("/{id}/collection")
    @ResponseBody
    public List<ArticleInfo> getCollectionList(@PathVariable("id") String id){
        return userService.getCollectionList(id);
    }

    @PostMapping("/{userid}/collection/{articleid}")
    @ResponseBody
    public ArticleInfo saveArticle(@PathVariable("userid") String userid, @PathVariable("taskid") String taskid){
        return userService.saveArticle(userid, taskid);
    }

    @DeleteMapping("/{userid}/collection/{articleid}")
    public void cancelSave(@PathVariable("userid") String userid, @PathVariable("taskid") String taskid){
        userService.cancelSave(userid, taskid);
    }


}
