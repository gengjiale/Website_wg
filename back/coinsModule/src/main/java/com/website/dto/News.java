package com.website.dto;

import lombok.Data;

@Data
public class News {
    String id;
    String title;
    String cover;
    String description;
    String published_at;
}
