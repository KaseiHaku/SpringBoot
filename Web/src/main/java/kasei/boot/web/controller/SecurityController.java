package kasei.boot.web.controller;

import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.awt.*;


@RestController
@RequestMapping( path = "/Security", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SecurityController {


    @PostMapping
    public void gg(){


        // todo spring security get current username
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        if (principal instanceof UserDetails) {
            String username = ((UserDetails)principal).getUsername();
        } else {
            String username = principal.toString();
        }
    }


    @PostMapping("login")
    public void login(String name, String password){

        // Authentication 接口表示：一切能表明用户身份的信息的东西，以下简称 -- 身份证
        Authentication request = new UsernamePasswordAuthenticationToken(name, password);

        // AuthenticationManager 用于验证 身份证 的真实性
        AuthenticationManager am = new SampleAuthenticationManager();
        Authentication response = am.authenticate(request);  // 返回一个验证过的 身份证，包含认证过的 证书，和 权限

        // 将验证过的 身份证 放入到 安全认证上下文中，SecurityContextHolder 是用于操作 SecurityContext （安全认证上下文）的操作类
        SecurityContextHolder.getContext().setAuthentication(response);


    }


}
