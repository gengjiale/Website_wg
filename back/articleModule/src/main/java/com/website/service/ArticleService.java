package com.website.service;

import com.website.dto.ArticleInfo;
import com.website.entity.Article;
import com.website.entity.Category;
import com.website.entity.Tag;
import com.website.mapper.ArticleMapper;
import com.website.mapper.CategoryMapper;
import com.website.mapper.TagMapper;
import lombok.AllArgsConstructor;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
@AllArgsConstructor
public class ArticleService {
    private RestTemplate restTemplate;
    ArticleMapper articleMapper;
    TagMapper tagMapper;
    CategoryMapper categoryMapper;

    public ArticleInfo setArticleToArticleInfo(Article article){
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        ArticleInfo articleInfo = new ArticleInfo();
        articleInfo.setId(article.getId());
        articleInfo.setUserid(article.getUserid());
        String url = "http://localhost:8081/api/v1/user/"+article.getUserid()+"/name";
        String name = restTemplate.getForObject(url, String.class);
        articleInfo.setAuthor(name);
        articleInfo.setTitle(article.getTitle());
        articleInfo.setContent(article.getContent());
        articleInfo.setDescription(article.getDescription());
        articleInfo.setPublish_time(article.getPublish_time());
        articleInfo.setDate(article.getPublish_time());
        articleInfo.setState(article.getState());

        articleInfo.setComment_count(article.getComment_count());
        articleInfo.setLike_count(article.getLike_count());
        articleInfo.setCollect_count(article.getCollect_count());
        articleInfo.setShare_count(article.getShare_count());

        articleInfo.setTag(article.getTag());
        return articleInfo;
    }

    /**
     * 根据文章id获取文章
     * @param id
     * @return
     */
    public ArticleInfo getArticleById(String id){
        Article article = articleMapper.selectById(id);
        if(article != null){
            ArticleInfo articleInfo = setArticleToArticleInfo(article);
            return articleInfo;
        }else {
            return null;
        }
    }

    /**
     * 根据当前current和num获取文章列表
     * @param current
     * @param num
     * @return
     */
    public List<ArticleInfo> getArticles(int userid, int current, int num){
        if(userid == 0){
            List<Article> articles = articleMapper.selectByCurrentAndNum(current, num);
            List<ArticleInfo> articleInfoList = new ArrayList<>();
            for (Article article : articles) {
                articleInfoList.add(setArticleToArticleInfo(article));
            }
            return articleInfoList;
        }else{
            List<Article> articles = articleMapper.selectByAuthor(userid, current, num);
            List<ArticleInfo> articleInfoList = new ArrayList<>();
            for (Article article : articles) {
                articleInfoList.add(setArticleToArticleInfo(article));
            }
            return articleInfoList;
        }

    }

    /**
     * 通过tag搜索文章
     * @param tag
     * @param current
     * @param num
     * @return
     */
    public List<ArticleInfo> selectByTag(String tag, int current, int num){
        int id = tagMapper.getIdByName(tag);
        List<Article> articles = articleMapper.selectByTag(id, current, num);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (Article article : articles) {
            articleInfoList.add(setArticleToArticleInfo(article));
        }
        return articleInfoList;
    }

    /**
     * 搜索文章
     * @param wd
     * @param current
     * @param num
     * @return
     */
    public List<ArticleInfo> searchArticles(String wd, int current, int num){
        wd = "%"+wd+"%";
        List<Article> articles = articleMapper.selectByWD(wd, current, num);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (Article article : articles) {
            articleInfoList.add(setArticleToArticleInfo(article));
        }
        return articleInfoList;
    }

    /**
     * 获取所有tags
     * @return
     */
    public List<Tag> getAllTags(){
        return tagMapper.getAllTags();
    }

    /**
     * 发布文章
     * @param authorId
     * @param title
     * @param describe
     * @param content
     * @return
     */
    public ArticleInfo insert(int authorId, String title, String describe, String content, int tag){
        long currentTimeMillis = System.currentTimeMillis();
        String id = "a" + currentTimeMillis;
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = now.format(formatter);
        int i = articleMapper.insert(id, authorId, title, describe, content, formattedDateTime, tag);
        if(i > 0){
            return setArticleToArticleInfo(articleMapper.selectById(id));
        }else {
            return null;
        }
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    public int delete(int id){
        return articleMapper.delete(id);
    }

    /**
     * 获取文章类别
     * @return
     */
    public List<Category> getAllCategory(){
        return categoryMapper.getAll();
    }
}
