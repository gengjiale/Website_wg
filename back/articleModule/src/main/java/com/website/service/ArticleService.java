package com.website.service;

import com.itextpdf.io.font.PdfEncodings;
import com.itextpdf.kernel.font.PdfFont;
import com.itextpdf.kernel.font.PdfFontFactory;
import com.itextpdf.kernel.pdf.*;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.website.dto.ArticleInfo;
import com.website.entity.Article;
import com.website.mapper.ArticleMapper;
import com.website.mapper.CollectMapper;
import com.website.mapper.TagMapper;
import lombok.AllArgsConstructor;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.fit.pdfdom.PDFDomTree;
import org.fit.pdfdom.PDFDomTreeConfig;
import org.springframework.core.io.FileSystemResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.multipart.MultipartFile;

import javax.xml.parsers.ParserConfigurationException;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static com.itextpdf.kernel.pdf.PdfName.L;
import static com.itextpdf.kernel.pdf.PdfName.TrueType;


@Service
@AllArgsConstructor
public class ArticleService {
    private RestTemplate restTemplate;
    ArticleMapper articleMapper;
    TagMapper tagMapper;
    LikeCollectService likeCollectService;
    CollectMapper collectMapper;


    public ArticleInfo setArticleToArticleInfo(Article article, List<String> tagList){
        restTemplate = new RestTemplate(new SimpleClientHttpRequestFactory());
        ArticleInfo articleInfo = new ArticleInfo();

        articleInfo.setArticleId(article.getArticleId());
        articleInfo.setUserId(articleInfo.getUserId());
        articleInfo.setTitle(article.getTitle());
        articleInfo.setContent(article.getContent());
        articleInfo.setDescription(article.getDescription());
        articleInfo.setPublishTime(article.getPublishTime());
        articleInfo.setCommentCount(article.getCommentCount());
        articleInfo.setLikeCount(article.getLikeCount());
        articleInfo.setCollectCount(article.getCollectCount());
        articleInfo.setPicture(article.getPicture());

        String url = "http://localhost:8081/api/v1/user/"+article.getUserId()+"/name";
        String name = restTemplate.getForObject(url, String.class);
        articleInfo.setUserName(name);

        articleInfo.setTagList(tagList);

        return articleInfo;
    }

    public void setLikeCollect(ArticleInfo articleInfo,String userId,String articleId){
        // 查看该文章是否被用户点赞收藏
        if(userId != null && !userId.equals("")){
            boolean isUserLike = likeCollectService.isUserLike(articleId, userId);
            boolean isUserCollect = likeCollectService.isUserCollect(articleId, userId);
            articleInfo.setUserLike(isUserLike);
            articleInfo.setUserCollect(isUserCollect);
        }
    }

    /**
     * 根据文章id获取文章
     * @param id
     * @return
     */
    public ArticleInfo getArticleById(String id){
        Article article = articleMapper.selectById(id);
        List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
        ArticleInfo articleInfo = setArticleToArticleInfo(article,tagList);

        if(article != null){

            return articleInfo;
        }else {
            return null;
        }
    }

    public ArticleInfo getContent(String articleId, String content, String userId){
        try {

            //读pdf
            String[] addrs = content.split("\\.");
            for(int i = 0; i < addrs.length; i++){
                System.out.println(addrs[i]);
            }
            String pdfPath = addrs[0] + ".pdf";
            String htmlPath = addrs[0] + ".html";
            //转html
            String htmlOutput = "";
            PDFDomTreeConfig config = PDFDomTreeConfig.createDefaultConfig();
            List<String> pageList = new ArrayList<>();
            try(PDDocument pdfDocument = PDDocument.load(new File(content))){
                PDFDomTree parser = new PDFDomTree(config);
                parser.setStartPage(0); // 从第一页开始
                parser.setEndPage(Integer.MAX_VALUE); // 结束页面设置为最大整数值，表示转换所有页面
                Writer output = new StringWriter();
                parser.writeText(pdfDocument, output);
                pdfDocument.close();
                htmlOutput = output.toString();
            } catch (ParserConfigurationException e) {
                throw new RuntimeException(e);
            }
            ArticleInfo articleInfo = new ArticleInfo();
            articleInfo.setContent(htmlOutput);
//            // 查看该文章是否被用户点赞收藏
//            if(userId != null && !userId.equals("")){
//                boolean isUserLike = likeCollectService.isUserLike(articleId, userId);
//                boolean isUserCollect = likeCollectService.isUserCollect(articleId, userId);
//                articleInfo.setUserLike(isUserLike);
//                articleInfo.setUserCollect(isUserCollect);
//            }
            return articleInfo;
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 获取文章封面
     * @param articleId
     * @param pictureAddr
     * @return
     */
    public ResponseEntity<Resource> getPicture(String articleId, String pictureAddr){
        try{
            Resource resource = new FileSystemResource(pictureAddr);
            return ResponseEntity.ok().header(HttpHeaders.CONTENT_DISPOSITION,
                    "attachment; filename=\""+resource.getFilename()+"\"").body(resource);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public List<ArticleInfo> getFavArticles(int current, int num){
        List<Article> articles = articleMapper.selectFav();
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (Article article : articles) {
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            ArticleInfo articleInfo = setArticleToArticleInfo(article, tagList);
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }
    public List<ArticleInfo> getFavArticles(int current, int num,String userid){
        List<Article> articles = articleMapper.selectFav();
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (Article article : articles) {
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            ArticleInfo articleInfo = setArticleToArticleInfo(article, tagList);
            setLikeCollect(articleInfo, userid, article.getArticleId());
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }
    public List<ArticleInfo> getUserArticles(int current, int num, String userid){
        List<Article> articles = articleMapper.selectByAuthor(userid, current, num);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (Article article : articles) {
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            ArticleInfo articleInfo = setArticleToArticleInfo(article, tagList);
            setLikeCollect(articleInfo, userid, article.getArticleId());
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }
    public List<ArticleInfo> getCollectArticles(String userid){
        List<String> articleIdList = collectMapper.selectArticleIdListByUserId(userid);
        System.out.println("======================");
        for(int i = 0; i < articleIdList.size(); i++){
            System.out.println(articleIdList.get(i));
        }
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for(String articleId: articleIdList){
            Article article = articleMapper.selectById(articleId);
            List<String> tagList = tagMapper.getTagsByArticleId(articleId);
            ArticleInfo articleInfo = setArticleToArticleInfo(article, tagList);
            setLikeCollect(articleInfo, userid, articleId);
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }
    public List<ArticleInfo> getArticles(int current, int num){
        List<Article> articles = articleMapper.selectByCurrentAndNum(current, num);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (Article article : articles) {
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            ArticleInfo articleInfo = setArticleToArticleInfo(article, tagList);
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }
    public List<ArticleInfo> getArticles(int current, int num,String userid){
        List<Article> articles = articleMapper.selectByCurrentAndNum(current, num);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for (Article article : articles) {
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            ArticleInfo articleInfo = setArticleToArticleInfo(article, tagList);
            setLikeCollect(articleInfo, userid, article.getArticleId());
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }



    /**
     * 通过tag搜索文章
     * @param tag
     * @param current
     * @param num
     * @return
     */
    public List<ArticleInfo> selectByTag(String tag, int current, int num){
        List<String> ids = tagMapper.getArticleIdsByTag(tag);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for(int i = 0; i < ids.size(); i++){
            Article article = articleMapper.selectById(ids.get(i));
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            articleInfoList.add(setArticleToArticleInfo(article, tagList));
        }
        return articleInfoList;
    }

    /**
     * 搜索文章
     * 通过tag搜索文章
     * @param wd
     * @param current
     * @param num
     * @return
     */
    public List<ArticleInfo> searchArticles(String wd, int current, int num){
        wd = "%"+wd+"%";
        List<String> articleIds = tagMapper.getArticleIdsByTagBlur(wd);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for(String id: articleIds){
            Article article = articleMapper.selectById(id);
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            articleInfoList.add(setArticleToArticleInfo(article,tagList));
        }
//        List<Article> articles = articleMapper.selectByWD(wd, current, num);
//        for (Article article : articles) {
//            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
//            articleInfoList.add(setArticleToArticleInfo(article,tagList));
//        }
        return articleInfoList;
    }
    public List<ArticleInfo> searchArticles(String wd, int current, int num, String userid){
        wd = "%"+wd+"%";
        List<String> articleIds = tagMapper.getArticleIdsByTagBlur(wd);
        List<ArticleInfo> articleInfoList = new ArrayList<>();
        for(String id: articleIds){
            Article article = articleMapper.selectById(id);
            List<String> tagList = tagMapper.getTagsByArticleId(article.getArticleId());
            ArticleInfo articleInfo = setArticleToArticleInfo(article,tagList);
            setLikeCollect(articleInfo, userid, id);
            articleInfoList.add(articleInfo);
        }
        return articleInfoList;
    }


    /**
     * 发布文章
     * @param userid
     * @param title
     * @param description
     * @param content
     * @return
     */
    public String insert(String userid, String title, String description,
                              String content, MultipartFile file,
                              List<String> tagList, MultipartFile picture){
        //创建用户文件夹
        String basicFile = "D:/websitedata/articles/";
        String userFile = basicFile + userid;
        File directory = new File(userFile);
        if(!directory.exists()){
            boolean isCreated = directory.mkdirs();
            if(!isCreated){
                return null;
            }
        }
        // articleId
        long currentTimeMillis = System.currentTimeMillis();
        String id = "a" + currentTimeMillis;
        // 创建文章文件夹
        String articleFile = userFile + '/' + id;
        File directory2 = new File(articleFile);
        if(!directory2.exists()){
            boolean isCreated = directory2.mkdirs();
            if(!isCreated){
                return null;
            }
        }
        String fileName = articleFile + '/' + id + ".docx";
        String pdfName = articleFile + '/' + id + ".pdf";
        String htmlName = articleFile + '/' + id + ".html";
        String pictureType = picture.getContentType().split("/")[1];
        String pictureName = articleFile + "/" + id + "." + pictureType;
        String imgFile = articleFile + "/img";
        if(!content.equals("") && file == null){
            try {
                PdfFont pdfFont = PdfFontFactory.createFont("SimSun.ttf", PdfEncodings.IDENTITY_H);
                PdfWriter writer = new PdfWriter(pdfName);
                PdfDocument pdf = new PdfDocument(writer);
                Document document = new Document(pdf);
                Paragraph paragraph = new Paragraph();
                paragraph.setFont(pdfFont).setFontSize(12);
                paragraph.add(content);
                document.add(paragraph);
                document.close();
                writer.close();
                pdf.close();
                Files.copy(picture.getInputStream(), Path.of(pictureName));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }else if(content.equals("") && file != null){
//            try {
//                file.transferTo(new File(fileName));
//                Files.copy(picture.getInputStream(), Path.of(pictureName));
//            } catch (IOException e) {
//                throw new RuntimeException(e);
//            }
        }

        // publicTime
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd-HH-mm-ss");
        String formattedDateTime = now.format(formatter);
        int i = articleMapper.insert(id, userid, title, description, pdfName, formattedDateTime, pictureName);
        if(i > 0){
            //将Tag存起来
            for(int j = 0; j < tagList.size(); j++){
                tagMapper.insert(id, tagList.get(j));
            }
            return id;
        }else {
            return null;
        }
    }

    /**
     * 删除文章
     * @param id
     * @return
     */
    public int delete(String id){
        return articleMapper.delete(id);
    }

}
