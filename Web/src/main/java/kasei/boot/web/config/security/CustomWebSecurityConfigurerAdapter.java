package kasei.boot.web.config.security;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.boot.autoconfigure.security.servlet.StaticResourceRequest;
import org.springframework.boot.autoconfigure.security.servlet.UserDetailsServiceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;
import org.springframework.security.web.util.matcher.RequestMatcher;

import javax.servlet.http.HttpServletRequest;


/**
 * TODO Spring Security's terminology introduce
 * Authenticate: 动词：认证
 * Authentication: 名词：认证方式
 * Principal: 名词：主角，当前认证方式的主角，即用户
 * Credential: 名词：凭证，即密码等能证明身份的东西
 *
 * Authorize: 动词：授权
 * Authority: 名词：权限
 * Authorization: 名词：授权书，即已被授权的证明
 *
 * TODO spring security authenticate procedure
 * PseudoCode
 *      var auth = new Authentication();
 *      var authResult = AuthenticationManager.authenticate(auth); // 内部调用 AuthenticationProvider 执行实际认证过程
 *      SecurityContextHolder.getContext().setAuthentication(authResult​);
 *
 * 认证相关的类
 *  Authentication: 认证方式
 *  AuthenticationProvider: 对一种认证方式进行认证
 *  AuthenticationManager: 将 Authentication 用序列中的所有 Provider 进行认证
 *  SecurityContext: 安全上下文本身，用于保存 Authentication 和 请求权限验证，保存在 ThreadLocal 中
 *  SecurityContextHolder: 用于操作当前安全上下文
 *
 *  UserDetails: 用于表示用户，包含用户的信息，账号/密码/权限/角色等
 *  UserDetailsService: 用于提供 spring security 需要的 UserDetails
 *
 *  AbstractSecurityInterceptor: 拦截器，用于身份认证，鉴权
 *  ExceptionTranslationFilter: 异常转换过滤器，将 spring security 异常转换成 http 码返回给前端，仅 Web
 *  AuthenticationEntryPoint: 认证方式进入点，用于将未认证的用户引导到登陆界面，仅 Web
 *  SecurityContextPersistenceFilter: 用于保持登陆状态，每次请求都通过该类设置 SecurityContext 到 ThreadLocal 中，或者清理请求中的 SecurityContext
 *
 *
 * TODO spring security authorize procedure
 *
 * 鉴权相关的类
 *  GrantedAuthority: 权限
 *  AbstractSecurityInterceptor: 鉴权拦截器
 *
 *

 * TODO spring security fundamental classes

 *

 *
 *
 *
 * AccessDecisionManager: 鉴权过程类
 * AfterInvocationManager: 鉴权成功后调用的类
 * ConfigAttribute: 权限类
 * SecurityMetadataSource：用于配置  AbstractSecurityInterceptor
 * RoleVoter：投票者
 * RunAsManager: 相当于 linux 中的 sudo
 * PasswordEncoder：密码加密
 *
 *
 *
 * TODO What spring boot do when Spring Security's dependencies in classpath
 * 1. 关闭 SecurityAutoConfiguration ：添加一个自定义的 WebSecurityConfigurerAdapter bean 到 IOC
 * 2. 关闭 UserDetailsServiceAutoConfiguration：
 *      添加一个自定义的 UserDetailsService, AuthenticationProvider, AuthenticationManager bean 到 IOC
 *
 *
 *
 * 创建一个名为  springSecurityFilterChain 的 Bean 放入到 ioc 容器中，且该 bean 时一个 servlet filter 实例，并绑定到所有 servlet 请求中
 * 创建一个类型为 UserDetailsService 的 bean 放入到 ioc 容器中，该 bean 中包含一个用户名为 user ，密码为一个随机数的用户
 *
 *
 *
 * */
@Configuration // 当有一个自定义的 WebSecurityConfigurerAdapter 类型的 bean 存在于 IOC 容器中时，会关闭 SecurityAutoConfiguration 的自动配置
@EnableWebSecurity // spring mvc 整合 spring security 必要的注解
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {


    @Bean
    public AnonymousAuthenticationFilter anonymousAuthenticationFilter(){
        AnonymousAuthenticationFilter anonymousAuthenticationFilter = new AnonymousAuthenticationFilter("foobar");
        return anonymousAuthenticationFilter;
    }

    @Bean
    public AnonymousAuthenticationProvider anonymousAuthenticationProvider(){
        return new AnonymousAuthenticationProvider("foobar");
    }


    @Override
    protected void configure(HttpSecurity http) throws Exception {

        http
            .authorizeRequests()
                .antMatchers("/", "/home", "/h2-console/**")
                .permitAll()
                .anyRequest().authenticated()
            .and()
                .formLogin()
                .permitAll()
            .and()
                .logout()
                .permitAll();


        http.csrf().disable();
        http.headers().frameOptions().disable();
    }
}
