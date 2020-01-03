package kasei.boot.security.config.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AnonymousAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.web.authentication.AnonymousAuthenticationFilter;

import javax.sql.DataSource;


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
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10) // 为当前自定义的过滤器链设置顺序
public class CustomWebSecurityConfigurerAdapter extends WebSecurityConfigurerAdapter {

    /************************************ Authenticate Configuration *****************************************/
    /** TODO 覆盖 SpringBoot 自动配置的全局 AuthenticationManager，一般情况下不用配置，使用自动配置的即可 */
    @Autowired
    private CustomAuthenticationProvider customAuthenticationProvider;
    // @Bean
    // public AuthenticationManager authenticationManager() {
    //
    //
    //     /** TODO AuthenticationProvider
    //      * 作用：作为 ProviderManager 的认证链的节点
    //      * */
    //     List<AuthenticationProvider> providers = new ArrayList<>();
    //     providers.add(customAuthenticationProvider);
    //     providers.add(new AnonymousAuthenticationProvider("foobar")); // 添加匿名认证方式的认证
    //
    //
    //     /** TODO AuthenticationManager Introduce
    //      * 作用： 用于对一个不完整的 Authenticate 进行认证，
    //      *      如果认证成功，则返回一个完整的 Authenticate，
    //      *      如果认证出错，则抛出 AuthenticationException 异常，
    //      *      如果返回 null，则说明当前 AuthenticationManager 无法解析当前的 Authentication
    //      * ProviderManager:
    //      *      是 SpringSecurity 自带的 AuthenticationManager 接口的实现类
    //      *      作用：逐个使用 AuthenticationProvider 对不完整的 Authenticate 进行认证
    //      *      可以配置一个 父 ProviderManager，当链中所有 AuthenticationProvider 都返回 null，那么会调用 parent 中的链进行认证
    //      * */
    //     AuthenticationManager manager = new ProviderManager(providers, null);
    //
    //     return manager;
    // }


    /** TODO 自定义全局（父） AuthenticationManager，和上面的 authenticationManager() 方法效果是一样的
     * 由于此处的 builder 是通过 @Autowired 注入的，
     * 所以此处 builder 构建出来的 AuthenticationManager 是全局的，
     * 或者说是其他 AuthenticationManager 的父 AuthenticationManager
     * */
    // @Autowired
    // public void initialize(AuthenticationManagerBuilder builder, DataSource dataSource) {
    //     builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
    //         .password("secret").roles("USER");
    // }

    /** TODO 自定义局部（子） AuthenticationManager
     * 由于此处的 builder 是覆写方法得到的，
     * 所以此处的 builder 只能构建出局部（子） AuthenticationManager
     * 全局 AuthenticationManager 可以使用 @Autowired 注入到其他 @Bean 中，
     * 但是子 AuthenticationManager 不行，因为子 AuthenticationManager 不再 IOC 容器中
     * */
    @Autowired
    private DataSource dataSource;
    // @Override
    // public void configure(AuthenticationManagerBuilder builder) {
    //     builder.jdbcAuthentication().dataSource(dataSource).withUser("dave")
    //         .password("secret").roles("USER");
    // }


    /************************************ Authorize Configuration *****************************************/
    /** TODO 自定义 AccessDecisionManager */
    // @Bean
    // public AccessDecisionManager accessDecisionManager() {
    //
    //     /** TODO AccessDecisionVoter
    //      * 作用：作为 AccessDecisionManager 鉴权链的节点，执行实际鉴权动作
    //      * AccessDecisionVoter#vote 返回 1 表示通过鉴权，0 表示弃权， -1 表示鉴权失败
    //      *
    //      * AccessDecisionVoter#vote 方法参数：
    //      *      Authentication authentication: 一个完全的 认证方式实例
    //      *      S object: 受权限控制的对象，比如一个方法，一个类等
    //      *      Collection<ConfigAttribute> attributes: object 相关的权限配置信息，经常使用 SpEL 表达式来计算
    //      * */
    //     List<AccessDecisionVoter<?>> decisionVoters = new ArrayList<>();
    //     decisionVoters.add(new RoleVoter()); // 有层级角色投票器，适用于角色嵌套
    //
    //
    //     /** TODO AccessDecisionManager Introduce
    //      * 作用：鉴权
    //      *
    //      * AffirmativeBased: 一票通过
    //      *      是 SpringSecurity 自带的 AccessDecisionManager 接口的实现类，内部维护一个链式的 AccessDecisionVoter ，用于执行实际的鉴权过程
    //      *      作用：只要有任何一个 AccessDecisionVoter 通过鉴权，则表明鉴权成功
    //      * UnanimousBased: 全票通过
    //      *
    //      * ConsensusBased: 多数通过
    //      * */
    //     AccessDecisionManager manager = new AffirmativeBased(decisionVoters); // 一票通过
    //     return manager;
    // }


    /************************************ Filter Configuration *****************************************/
    /** TODO 往 Servlet 容器中添加 Filter 的方法
     * 1. 在 IOC 容器中配置一个实现 Filter 接口的 Bean，该 Bean 带有 @Order 注解，或者实现 Order 接口
     * 2. 在 FilterRegistrationBean 中配置
     *
     * TODO Spring Security Filter
     * Spring Security 只在 Servlet 容器中添加了一个过滤器，该过滤是 FilterChainProxy 的实例，并存放在 IOC 容器中
     *
     * FilterChainProxy 内部包含多个过滤器链，每一个链匹配一个 URL 路径，每一个链包含多个 DelegatingFilterProxy 过滤器
     * Spring Boot 默认添加 6 条过滤器，1-5 条匹配静态资源路径；最后一条匹配所有 URL 路径，用于处理 身份认证/权限管理/会话等操作
     * */
    //@Bean
    @Order(Ordered.LOWEST_PRECEDENCE -10)
    public CustomFilter customFilter(){

        return new CustomFilter();
    }

    /** TODO 自定义过滤器链：
     * 添加一个 WebSecurityConfigurerAdapter 或 WebSecurityConfigurer 类型的 Bean 到 IOC 中
     * 每一组匹配都有一个独立的 WebSecurityConfigurerAdapter Bean
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        /** TODO 关闭对 h2 数据库控制台的 CSRF 保护
         * CSRF: Cross-site request forgery 跨站请求伪造
         * */
        http.csrf().ignoringAntMatchers("/h2-console/**");

        /** TODO 允许同源网站使用 iframe 嵌入当前页面
         * Spring Security 默认会在 HTTP Response Header 中添加 X-Frame-Options:DENY
         * 该头表示不允许当前页面放在 iframe 标签中展示，会导致 h2 控制台显示空白页面
         * */
        http.headers().frameOptions().sameOrigin();

        http.authorizeRequests().antMatchers("/**").hasRole("USER").and().formLogin();
    }




    // @Bean
    // public AnonymousAuthenticationFilter anonymousAuthenticationFilter(){
    //     AnonymousAuthenticationFilter anonymousAuthenticationFilter = new AnonymousAuthenticationFilter("foobar");
    //     return anonymousAuthenticationFilter;
    // }
    //
    // @Bean
    // public AnonymousAuthenticationProvider anonymousAuthenticationProvider(){
    //     return new AnonymousAuthenticationProvider("foobar");
    // }



}
