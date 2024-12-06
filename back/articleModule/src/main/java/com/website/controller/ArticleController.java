package com.website.controller;


import com.website.dto.ArticleInfo;
import com.website.dto.CommentInfo;
import com.website.entity.Category;
import com.website.entity.Tag;
import com.website.service.ArticleService;
import com.website.service.CommentService;
import lombok.AllArgsConstructor;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.core.io.UrlResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.nio.file.Path;
import java.util.List;

@RequestMapping("/api/v1/article")
@RestController
@AllArgsConstructor
public class ArticleController {
    ArticleService articleService;
    CommentService commentService;
    private ResourceLoader resourceLoader;
    public void FileDownloadController(ResourceLoader resourceLoader){
        this.resourceLoader = resourceLoader;
    }

    /**
     * 根据文章id获取文章信息
     * @param articleid
     * @return
     */
    @GetMapping("/{articleid}")
    @ResponseBody
    public ArticleInfo getArticleById(@PathVariable("articleid") String articleid){
        return articleService.getArticleById(articleid);
    }

//    /**
//     * 根据作者id获取文章
//     * @param userid
//     * @return
//     */
//    @GetMapping
//    @ResponseBody
//    public List<ArticleInfo> getArticleByAuthor(@RequestParam int userid,
//                                                @RequestParam int current, @RequestParam int num){
//        return articleService.getArticleByAuthor(userid, current, num);
//    }

    /**
     * 获取文章
     * @param current
     * @param num
     * @return
     */
    @GetMapping
    @ResponseBody
    public List<ArticleInfo> getArticles(@RequestParam(required = false) String userid,
                                         @RequestParam(required = false) String tag,
            @RequestParam int current, @RequestParam int num){
        if(userid != null && !userid.isEmpty() && tag == null ){
            return articleService.getArticles(Integer.parseInt(userid), current, num);
        }else if(userid == null && tag == null){
            return articleService.getArticles(0, current, num);
        }else if(userid == null && !tag.isEmpty()){
            return articleService.selectByTag(tag, current, num);
        }else {
            return null;
        }
    }
//    @GetMapping("/word")
//    public void getWord() throws IOException {
//        String filepath = "C:/Users/g'pai/Desktop/sm.docx";
//        File file = new File(filepath);
//        Resource resource = new UrlResource(file.toURI().toURL());
//        if (resource.exists() || resource.isReadable()) {
//            return ResponseEntity.ok()
//                    .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + resource.getFilename() + "\"")
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(resource);
//        } else {
//            throw new RuntimeException("Could not read the file!");
//        }
//    }

    /**
     * 根据关键词搜索文章
     * @param wd
     * @param current
     * @param num
     * @return
     */
    @GetMapping("/s")
    @ResponseBody
    public List<ArticleInfo> searchArticle(@RequestParam String wd, @RequestParam int current, @RequestParam int num){
        return articleService.searchArticles(wd, current, num);
    }

    /**
     * 获取文章类别
     * @return
     */
    @GetMapping("/categories")
    @ResponseBody
    public List<Category> getCategory(){
        return articleService.getAllCategory();
    }

    /**
     * 添加文章
     */
    @PostMapping
    @ResponseBody
    public ArticleInfo publishArticle(@RequestBody ArticleInfo articleInfo){
        return articleService.insert(articleInfo.getUserid(), articleInfo.getTitle(),articleInfo.getDescription(),
                articleInfo.getContent(), articleInfo.getTag());
    }

    @PostMapping("/word")
    @ResponseBody
    public String publish(@RequestParam("file") MultipartFile file){
        try {
            String filePath = "D:/website/articles/u1/" + file.getOriginalFilename();
            file.transferTo(new File(filePath));
            return "文件上传成功";
        } catch (Exception e) {
            e.printStackTrace();
            return "文件上传失败";
        }
    }

    /**
     * 获取tags
     */
    @GetMapping("/tag")
    @ResponseBody
    public List<Tag> getTags(){
        return articleService.getAllTags();
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
     * @param content
     * @return
     */
    @PostMapping("/{id}/comment")
    @ResponseBody
    public CommentInfo comment(@PathVariable("id") String id,
                               @RequestParam String pid, @RequestBody CommentInfo content){
        return commentService.insertComment(pid, content.getContent(), id,content.getUserid());
    }

    @DeleteMapping("/{articleid}/comment/{commentid}")
    public void deleteComment(@PathVariable("articleid") String articleid,
                              @PathVariable("commentid") String commentid){
        commentService.deleteComment(commentid);
    }
//    @PostMapping("/{id}/comment")
//    @ResponseBody
//    public CommentInfo comment(@RequestParam String pid){
//
//    }
}
