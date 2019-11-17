package com.softroad.demo07.controller;

import com.softroad.demo07.dto.AccessTokenDto;
import com.softroad.demo07.dto.GitHubUser;
import com.softroad.demo07.provider.GitHubProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
public class AuthorizeController {

    @Autowired
    private GitHubProvider gitHubProvider;

    @Value("${github.client.id}")
    private String clientId;
    @Value("${github.client.secret}")
    private String clientSecret;
    @Value("${github.redirect.uri}")
    private String redirectUri;

    @RequestMapping("/callback")
    public String callback(@RequestParam(name="code") String code,
                           @RequestParam(name="state") String state,
                           HttpServletRequest request) {
        AccessTokenDto accessTokenDto = new AccessTokenDto();
        accessTokenDto.setClient_id(clientId);
        accessTokenDto.setClient_secret(clientSecret);
        accessTokenDto.setCode(code);
        accessTokenDto.setRedirect_uri(redirectUri);
        accessTokenDto.setState(state);
        String accessToken = gitHubProvider.accessToken(accessTokenDto);
        GitHubUser user = gitHubProvider.getUser(accessToken);
        if (user != null) {
            //获取用户名正确,登陆成功
            request.getSession().setAttribute("user", user);
            return "redirect:/";
        } else {
            //登陆失败
            return "redirect:/";
        }
    }
}
