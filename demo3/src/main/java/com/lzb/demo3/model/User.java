package com.lzb.demo3.model;

import lombok.Data;

@Data
public class User {
    private Integer id;
    private  String name;
    private String avatarUrl;
    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", accountId='" + accountId + '\'' +
                ", token='" + token + '\'' +
                ", gmtCreate=" + gmtCreate +
                ", gmtModified=" + gmtModified +
                '}';
    }
    private String accountId;
    private  String token;
    private Long gmtCreate;
    private long gmtModified;

}
