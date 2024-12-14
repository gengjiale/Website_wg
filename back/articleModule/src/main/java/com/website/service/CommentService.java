package com.website.service;

import com.website.dto.ArticleInfo;
import com.website.dto.CommentInfo;
import com.website.entity.Article;
import com.website.entity.Comment;
import com.website.mapper.ArticleMapper;
import com.website.mapper.CommentMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@AllArgsConstructor
public class CommentService {
    ArticleMapper articleMapper;
    CommentMapper commentMapper;
    private RestTemplate restTemplate;

    public CommentInfo setCommentToCommentInfo(Comment comment){
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        CommentInfo commentInfo = new CommentInfo();
        commentInfo.setCommentId(comment.getCommentId());
        commentInfo.setReplyId(commentInfo.getReplyId());
        commentInfo.setContent(comment.getContent());
        commentInfo.setCreatedTime(comment.getCreatedTime());
        commentInfo.setUserId(comment.getUserId());
        commentInfo.setArticleId(comment.getArticleId());

        String url = "http://localhost:8081/api/v1/user/"+comment.getUserId()+"/name";
        String name = restTemplate.getForObject(url, String.class);
        commentInfo.setUserName(name);


        return commentInfo;
    }

    /**
     * 获取文章的评论
     * @param articleId
     * @return
     */
    public List<CommentInfo> getCommentsByArticleId(String articleId){
        List<Comment> comments = commentMapper.getCommentsByArticleId(articleId);
        // 获取父子map
        Map<String, List<CommentInfo>> map = new HashMap<>();
        for (Comment comment : comments) {
            String parentId = comment.getReplyId();
            CommentInfo commentInfo = setCommentToCommentInfo(comment);
            if (map.containsKey(parentId)) {
                map.get(parentId).add(commentInfo);
            } else {
                List<CommentInfo> commentList = new ArrayList<>();
                commentList.add(commentInfo);
                map.put(parentId, commentList);
            }
        }
        List<CommentInfo> commentInfoList = map.get(articleId);
        setChildToParent(commentInfoList, map);
        return commentInfoList;
    }

    void setChildToParent(List<CommentInfo> commentInfoList, Map<String, List<CommentInfo>> map){
        for (CommentInfo commentInfo : commentInfoList) {
            String id = commentInfo.getCommentId();
            if (map.containsKey(id)) {
                commentInfo.setReplies(map.get(id));
            }
        }
        for (CommentInfo commentInfo : commentInfoList) {
            if (commentInfo.getReplies() != null && commentInfo.getReplies().size() > 0){
                setChildToParent(commentInfo.getReplies(), map);
            }
        }
    }

    /**
     * 添加评论
     * @param pid
     * @param comment
     * @param articleId
     * @param userId
     * @return
     */
    public CommentInfo insertComment(String pid, String comment, String articleId, String userId){
        long currentTimeMillis = System.currentTimeMillis();
        String id = "c" + currentTimeMillis;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = now.format(formatter);
        int i = commentMapper.insertComment(id, pid, comment, formattedDateTime, articleId, userId);
        if(i > 0){
            articleMapper.commentArticle(articleId);
            return setCommentToCommentInfo(commentMapper.getById(id));
        }else {
            return null;
        }
    }

    /**
     * 删除评论
     * @param commentId
     * @return
     */
    public int deleteComment(String commentId){
        return commentMapper.deleteComment(commentId);
    }


}
