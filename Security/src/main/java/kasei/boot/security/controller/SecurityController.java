package kasei.boot.security.controller;

import kasei.boot.security.repository.h2.entity.User;
import kasei.boot.security.utility.UniversalResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.Collection;


@RestController
@RequestMapping( path = "/Security", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SecurityController {


    /** TODO 获取当前用户实例 */
    @GetMapping("/currentUser")
    public String currentUser(){
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        String username = null;
        if (principal instanceof UserDetails) {
            username = ((UserDetails)principal).getUsername();
        } else {
            username = principal.toString();
        }
        return username;
    }

    /** TODO 获取当前执行代码的主角实例
     * 方法参数中有 Principal 类型时，Security 会自动注入
     * */
    @GetMapping("currentPrincipal")
    public Principal currentPrincipal(Principal principal){
        return principal;
    }


    @GetMapping("/test")
    public String test(){
        return "test";
    }


    /** TODO 获取当前的 Security 的 AuthenticationManager */
    // @Autowired
    // private AuthenticationManager authenticationManager;
    // @GetMapping("/authenticationManager")
    // public String authenticationManager(){
    //     String typeName = authenticationManager.getClass().getTypeName();
    //     return typeName;
    // }




    /** TODO spring security authenticate procedure
     * 构造一个 Authentication 实例
     * 将 Authentication 实例传递给 AuthenticationManager 实例进行认证
     * org.springframework.security.authentication.AuthenticationManager 返回一个补充过权限等信息的 Authentication 实例
     * 调用 SecurityContextHolder.getContext().setAuthentication(…​) 将认证过的  Authentication 实例放入到 SecurityContext 中
     * */
    // @PostMapping("/authenticate/pwd")
    // public UniversalResponse authenticateByPwd(@RequestBody User user){
    //     // Authentication 接口表示：一切能表明用户身份的信息的东西，以下简称 -- 身份证
    //     Authentication authRequest = new UsernamePasswordAuthenticationToken(user.getAccount(), user.getPassword());
    //
    //     // AuthenticationManager 用于验证 身份证 的真实性
    //     AuthenticationManager am = new UserPwdAuthenticationManager();
    //     Authentication authResult = null;
    //     try {
    //         authResult = am.authenticate(authRequest); // 返回一个验证过的 身份证，包含认证过的 证书，和 权限
    //     } catch (AuthenticationException e) {
    //         System.out.println("Authentication failed: " + e.getMessage());
    //     }
    //
    //     // 将验证过的 身份证 放入到 安全认证上下文中，SecurityContextHolder 是用于操作 SecurityContext （安全认证上下文）的操作类
    //     SecurityContextHolder.getContext().setAuthentication(authResult);
    //
    //     return null;
    // }

    @PostMapping("/authenticate/jwt")
    public UniversalResponse authenticateByJwt(){
        return null;
    }


    @GetMapping
    public String getSecurityFundamentalClass(){

        // 获取保存在 ThreadLocal 中的 SecurityContext 实例
        SecurityContext securityContext = SecurityContextHolder.getContext();
        // 获取认证方式
        Authentication authentication = securityContext.getAuthentication();
        // 获取当前认证方式的主角
        Object principal = authentication.getPrincipal();

        // 获取权限
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();


        return null;
    }





}
