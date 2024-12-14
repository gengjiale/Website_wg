package com.website.controller;

import com.website.dto.ArticleInfo;
import com.website.service.InterFunction;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 用于模块之间的请求
 */
@RequestMapping("/api/article")
@RestController
@AllArgsConstructor
public class InterFunctionController {
    InterFunction interFunction;

    /**
     * 根据用户ID获取点赞文章列表
     * @param id
     * @return
     */
    @GetMapping("/user/{id}/like")
    @ResponseBody
    public List<ArticleInfo> getLikeList(@PathVariable("id") String id){
        return interFunction.getLikeList(id);
    }

    /**
     * 点赞文章
     * @param userid
     * @param articleid
     * @return
     */
    @PostMapping("/user/{userid}/like/{articleid}")
    @ResponseBody
    public ArticleInfo likeArticle(@PathVariable("userid") String userid, @PathVariable("articleid") String articleid){
        return interFunction.likeArticle(userid, articleid);
    }

    /**
     * 取消点赞
     * @param userid
     * @param articleid
     */
    @DeleteMapping("/{userid}/like/{articleid}")
    public void cancelLike(@PathVariable("userid") String userid, @PathVariable("articleid") String articleid){
        interFunction.cancelLike(userid, articleid);
    }

    /**
     * 根据用户ID获取收藏文章列表
     * @param id
     * @return
     */
    @GetMapping("/user/{id}/collect")
    @ResponseBody
    public List<ArticleInfo> getCollectList(@PathVariable("id") String id){
        return interFunction.getCollectList(id);
    }

    /**
     * 收藏文章
     * @param userid
     * @param articleid
     * @return
     */
    @PostMapping("/user/{userid}/collect/{articleid}")
    @ResponseBody
    public ArticleInfo collectArticle(@PathVariable("userid") String userid, @PathVariable("articleid") String articleid){
        return interFunction.collectArticle(userid, articleid);
    }

    /**
     * 取消收藏
     * @param userid
     * @param articleid
     */
    @DeleteMapping("/{userid}/collect/{articleid}")
    public void cancelCollect(@PathVariable("userid") String userid, @PathVariable("articleid") String articleid){
        interFunction.cancelCollect(userid, articleid);
    }

}
