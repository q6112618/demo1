package com.lzb.demo3.model;

import lombok.Data;

@Data
public class Question {
    private Integer id;
    private String title;
    private  String description;
    private  Long gmtCreate;
    private Long gmtModified;
    private String tag;
    private Integer creator;
    private Integer communityCount;
    private Integer viewCount;
    private  Integer likeCount;

}
