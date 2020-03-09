package com.lzb.demo3.controller;

import com.lzb.demo3.mapper.UserMapper;
import com.lzb.demo3.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;


@Controller
public class indexController {
    @Autowired
    UserMapper userMapper;
    @GetMapping("/")
    public String hello(HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        for (Cookie cookie:cookies){
            if (cookie.getName().equals("token")){
                String token=cookie.getValue();
                User user=userMapper.findByToken(token);
                if (user != null){
                    request.getSession().setAttribute("gitHubUser", user);
                }
                break;
            }
        }
        return "index";
    }
}