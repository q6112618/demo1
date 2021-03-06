package com.lzb.demo3.controller;

import com.lzb.demo3.dto.AccessTokenDTO;
import com.lzb.demo3.dto.GitHubUser;
import com.lzb.demo3.mapper.UserMapper;
import com.lzb.demo3.model.User;
import com.lzb.demo3.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

@Controller
public class AuthorizeController {
    @Autowired
    private GitHubProvider gitHubProvider;
    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String ClienSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;
    @Autowired
    private  UserMapper userMapper;
    @GetMapping("/callback")
    public String callback(@RequestParam(name="code")String code, @RequestParam(name = "state")String state, HttpServletRequest request, HttpServletResponse response){
        AccessTokenDTO accessTokenDTO=new AccessTokenDTO();
        accessTokenDTO.setClient_id(clientId);
        accessTokenDTO.setClient_secret(ClienSecret);
        accessTokenDTO.setCode(code);
        accessTokenDTO.setRedirect_uri(redirectUri);
        accessTokenDTO.setState(state);
        String accessToken= gitHubProvider.getAccessToken(accessTokenDTO);
        GitHubUser gitHubUser=gitHubProvider.getUser(accessToken);
        //System.out.println(gitHubUser);
        if(gitHubUser!=null && gitHubUser.getId()!=null){
            User user = new User();
            String token = UUID.randomUUID().toString();
            user.setToken(token);
            user.setName(gitHubUser.getName());
            user.setAccountId(String.valueOf(gitHubUser.getId()));
            user.setGmtCreate(System.currentTimeMillis());
            user.setGmtModified(user.getGmtCreate());
            user.setAvatarUrl(gitHubUser.getAvatarUrl());
            userMapper.insert(user);
            //登陆成功,写session和cokie
            response.addCookie(new Cookie("token", token));
            return "redirect:/";
        }else {
            //登陆失败
            return "redirect:/";
        }

    }


}
