package kasei.boot.security.config;

import kasei.boot.security.config.web.CustomFilter;
import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;

@Configuration
public class WebConfig {

    /** TODO Boot 中往原生 Servlet 容器中添加自定义 Filter : 方式一
     * 1. 实现 Filter 接口
     * 2. 放入到 IOC 容器中
     * 3. @Order 配置当前自定义 Filter 的顺序
     *      SecurityProperties.DEFAULT_FILTER_ORDER 是 Security 的 FilterChainProxy 过滤器的顺序
     *      值越小，优先级越高
     * 4. 自定义 Filter 次序值不能  >=  OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER
     * */
    @Bean
    @Order(SecurityProperties.DEFAULT_FILTER_ORDER - 1)
    public CustomFilter customFilter(){
        return new CustomFilter();
    }
}
