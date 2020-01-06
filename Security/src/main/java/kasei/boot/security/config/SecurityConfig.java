package kasei.boot.security.config;

import kasei.boot.security.repository.h2.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import javax.sql.DataSource;


/**
 * TODO Boot 项目依赖中存在 spring-boot-starter-security 时
 * 1. 在 IOC 容器中创建一个 Bean，其中 beanName=springSecurityFilterChain; beanType=FilterChainProxy
 * 2. 往原生的 Servlet 容器（tomcat）中插入一个 Filter ，该 Filter 就是 1 中的 Bean，且 @Order(SecurityProperties.DEFAULT_FILTER_ORDER)
 * 3. 默认使用 ProviderManager 作为 AuthenticationManager
 * */
@EnableWebSecurity // 开启 URL 权限管理；spring mvc 整合 spring security 必要的注解
public class SecurityConfig  {

    // 参照 JdbcDaoImpl 重写 UserDetailService

    // 参照 DaoAuthenticationProvider 写一个 Provider

    // 当有一个自定义的 UserDetailsService 类型的 bean 存在于 IOC 容器中时，会关闭 UserDetailsServiceAutoConfiguration 的自动配置
    @Autowired
    private UserDao userDao;
    @Bean
    public UserDetailsService userDetailsService(){
        return new MyUserDetailsService(userDao);
    }

    // 当有一个自定义的 AuthenticationProvider 类型的 bean 存在于 IOC 容器中时，会关闭 UserDetailsServiceAutoConfiguration 的自动配置
    @Bean
    public AuthenticationProvider authenticationProvider(){
        return new MyAuthenticationProvider(this.userDetailsService());
    }
}
