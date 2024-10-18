package com.website.controller;

import com.website.service.ArticleService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/v1/article")
@RestController
@AllArgsConstructor
public class ArticleController {
    ArticleService articleService;
}
