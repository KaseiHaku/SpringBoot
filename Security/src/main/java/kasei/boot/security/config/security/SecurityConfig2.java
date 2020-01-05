package kasei.boot.security.config;

import kasei.boot.security.config.security.CustomAuthenticationProvider;
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


/** TODO Security Integrate into Boot
 * 1. 关闭 SecurityAutoConfiguration ：添加一个自定义的 WebSecurityConfigurerAdapter bean 到 IOC
 * 2. 关闭 UserDetailsServiceAutoConfiguration：
 *      添加一个自定义的 UserDetailsService, AuthenticationProvider, AuthenticationManager bean 到 IOC
 * */
//@Configuration
/** TODO 指定 FilterChainProxy 内部 URL Pattern 匹配的过滤器链的顺序
 * 因为 Boot 默认添加的兜底 URL Pattern ( /** ) 的顺序为 @Order(SecurityProperties.BASIC_AUTH_ORDER)
 * 所以自己添加的 URL Pattern 的顺序要效于 /** , 即：高优先级
 * */
@Order(SecurityProperties.BASIC_AUTH_ORDER - 10) // 为 FilterChainProxy 内部过滤器链自定义的过滤器链设置顺序
public class SecurityConfig2 extends WebSecurityConfigurerAdapter {

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
     *      Spring Security 只在 Servlet 容器中添加了一个过滤器，该过滤器是 FilterChainProxy 的实例，并存放在 IOC 容器中，
     *      Bean 名固定为 springSecurityFilterChain
     *      Order 为 SecurityProperties.DEFAULT_FILTER_ORDER
     *
     * FilterChainProxy 内部包含多个过滤器链，每一个链匹配一个 URL 路径，每一个链包含多个 DelegatingFilterProxy 过滤器
     * Boot 一般默认添加 6 条过滤器，1-5 条匹配静态资源路径；
     *      1. /css/**
     *      2. /images/**
     *      3. /error
     *      ...
     *      6. /**              最后一条匹配所有 URL 路径，用于处理 身份认证/权限管理/会话等操作，
     *                          security.basic.enabled=false 关闭自动配置兜底 URL Pattern
     * */

    /** TODO 自定义 Security 过滤器链：
     * 添加一个 WebSecurityConfigurerAdapter 或 WebSecurityConfigurer 类型的 Bean 到 IOC 中
     * 每一组匹配都有一个独立的 WebSecurityConfigurerAdapter Bean
     * */
    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // 配置可以匿名访问的路径
        // http.antMatcher("/foo/**") // 会在 FilterChainProxy 过滤器中创建一个 /foo/** 的过滤器链，该过滤器链在 /** fallback 过滤器之前

        ;

        // 配置登陆界面
        http.formLogin();

        // 配置需要登陆后访问的路径
        http.authorizeRequests()
            // 配置需要额外授权的路径
            .antMatchers("/h2-console/**").hasRole("ANONYMOUS") // 配置 /h2-console/** 允许匿名访问
            .antMatchers("/**").hasRole("USER")  // 配置 /** 路径必须要登陆
            .anyRequest().denyAll()  // 配置其他任何未配置的路径，都禁止访问
        ;


        /** TODO 关闭对 h2 数据库控制台的 CSRF 保护
         * CSRF: Cross-site request forgery 跨站请求伪造
         * */
        http.csrf().ignoringAntMatchers("/h2-console/**");

        /** TODO 允许同源网站使用 iframe 嵌入当前页面
         * Spring Security 默认会在 HTTP Response Header 中添加 X-Frame-Options:DENY
         * 该头表示不允许当前页面放在 iframe 标签中展示，会导致 h2 控制台显示空白页面
         * */
        http.headers().frameOptions().sameOrigin();


        /** TODO HTTP 协议重定向到 HTTPS 协议 */
        // http.requiresChannel(channel -> channel.anyRequest().requiresSecure());

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
