package com.lzb.demo3.dto;

import lombok.Data;

@Data
public class GitHubUser {
    private String name;
    private  Long id;
    private String bio;
    private String avatarUrl;

    @Override
    public String toString() {
        return "GitHubUser{" +
                "name='" + name + '\'' +
                ", id=" + id +
                ", bio='" + bio + '\'' +
                ", avatarUrl='" + avatarUrl + '\'' +
                '}';
    }
}
