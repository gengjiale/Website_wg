package com.website.controller;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.website.dto.ArticleInfo;
import com.website.dto.CommentInfo;
import com.website.entity.Tag;
import com.website.service.ArticleService;
import com.website.service.CommentService;
import com.website.service.LikeCollectService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.sql.Blob;
import java.util.List;

@RequestMapping("/api/v1/article")
@RestController
@AllArgsConstructor
public class ArticleController {
    ArticleService articleService;
    CommentService commentService;
    LikeCollectService likeCollectService;

//    /**
//     * 根据文章id获取文章信息
//     * @param articleId
//     * @return
//     */
//    @GetMapping("/{articleid}")
//    @ResponseBody
//    public ArticleInfo getArticleById(@PathVariable("articleid") String articleId){
//        return articleService.getArticleById(articleId);
//    }

    /**
     * 获取文章内容
     * @param articleId
     * @param addr
     * @return
     */
    @GetMapping("/{articleid}/content")
    public ArticleInfo getContent(@PathVariable("articleid") String articleId,
                                  @RequestParam("addr") String addr,
                                  @RequestParam(required = false) String userid){
        return articleService.getContent(articleId, addr, userid);
    }

    @GetMapping("/{articleid}/picture")
    public ResponseEntity<Resource> getPicture(@PathVariable("articleid") String articleId,
                                               @RequestParam("addr") String addr){
        return articleService.getPicture(articleId, addr);
    }

    /**
     * 获取文章列表
     *  一般获取指定数量的文章
     *  userid : 获取指定用户的文章
     *  tag: 获取指定tag的文章
     * @param current
     * @param num
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<ArticleInfo> getArticles(@RequestParam(required = false) String userid,
                                         @RequestParam(required = false) String tag,
                                         @RequestParam(required = false) String user,
                                         @RequestParam(required = false) String collect,
                                         @RequestParam(required = false) String fav,
            @RequestParam("current") int current, @RequestParam("num") int num){
        if(collect == null && user != null && fav == null && userid != null && !userid.isEmpty() && tag == null){
            //获取用户的文章
            return articleService.getUserArticles(current, num, userid);
        }else if(collect != null && user == null && fav == null && userid != null && !userid.isEmpty() && tag == null){
            //获取用户收藏的文章
            return articleService.getCollectArticles(userid);
        }else if(user == null && fav != null && userid != null && !userid.isEmpty() && tag == null){
            //获取用户登录时的最受欢迎文章
            return articleService.getFavArticles(current, num, userid);
        }else if(user == null && fav == null && userid != null && !userid.isEmpty() &&  tag == null){
            //获取用户登录时的最新文章列表
            return articleService.getArticles(current, num, userid);
        }else if(user == null && fav != null && userid == null && tag == null){
            //获取用户未登录时的最受欢迎文章
            return articleService.getFavArticles(current, num);
        }else if(user == null && fav == null && userid == null && tag == null){
            //获取用户未登录时的最新文章列表
            return articleService.getArticles(current, num);
        }else{
            return null;
        }
    }

    /**
     * 根据关键词搜索文章
     * @param wd
     * @param current
     * @param num
     * @return
     */
    @GetMapping("/s")
    @ResponseBody
    public List<ArticleInfo> searchArticle(@RequestParam String wd,
                                           @RequestParam(required = false) String userid,
                                           @RequestParam int current, @RequestParam int num){
        if(userid == null){
            return articleService.searchArticles(wd, current, num);
        }else {
            return articleService.searchArticles(wd, current, num, userid);
        }
    }


    /**
     * 添加文章（不含封面）
     */
    @PostMapping
    @ResponseBody
    public String publishArticle(@RequestParam("articleinfo") String articleInfoString,
                                 @RequestParam("picture") MultipartFile picture){
        ObjectMapper mapper = new ObjectMapper();
        ArticleInfo articleInfo = null;
        try {
            articleInfo = mapper.readValue(articleInfoString, ArticleInfo.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return articleService.insert(articleInfo.getUserId(), articleInfo.getTitle(),articleInfo.getDescription(),
                articleInfo.getContent(), null,articleInfo.getTagList(), picture);
    }


    /**
     * 添加文章（word,不含封面）
     * @param file
     * @param articleInfo
     * @return
     */
    @PostMapping("/word")
    @ResponseBody
    public String publish(@RequestParam("file") MultipartFile file,
                               @RequestBody ArticleInfo articleInfo){
        return articleService.insert(articleInfo.getUserId(), articleInfo.getTitle(), articleInfo.getDescription(),
                "",file,articleInfo.getTagList(), null);
    }


    /**
     * 获取评论
     * @param id
     * @return
     */
    @GetMapping("/{id}/comment")
    @ResponseBody
    public List<CommentInfo> getCommentsByArticleId(@PathVariable("id") String id){
        return commentService.getCommentsByArticleId(id);
    }

    /**
     * 添加评论
     * @param pid
     * @param comment
     * @return
     */
    @PostMapping("/{id}/comment")
    @ResponseBody
    public CommentInfo comment(@PathVariable("id") String id,
                               @RequestParam String pid, @RequestBody CommentInfo comment){
        return commentService.insertComment(pid, comment.getContent(), id,comment.getUserId());
    }

    @DeleteMapping("/{articleid}/comment/{commentid}")
    public void deleteComment(@PathVariable("articleid") String articleid,
                              @PathVariable("commentid") String commentid){
        commentService.deleteComment(commentid);
    }

    @PostMapping("/{id}/like")
    public void likeArticle(@PathVariable("id") String articleId,
                            @RequestParam("userid") String userId,
                            @RequestParam("likenum") int likeNum){
        if(likeNum == 0){ // 点赞
            likeCollectService.likeArticle(articleId, userId);
        }else if(likeNum == 1){ // 取消点赞
            likeCollectService.cancelLike(articleId, userId);
        }
    }

    @PostMapping("/{id}/collect")
    public void collectArticle(@PathVariable("id") String articleId,
                            @RequestParam("userid") String userId,
                            @RequestParam("collectnum") int collectNum){
        if(collectNum == 0){ // 点赞
            likeCollectService.collectArticle(articleId, userId);
        }else if(collectNum == 1){ // 取消点赞
            likeCollectService.cancelCollect(articleId, userId);
        }
    }

}
