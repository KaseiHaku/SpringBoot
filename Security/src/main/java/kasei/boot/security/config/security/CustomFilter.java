package kasei.boot.security.config.security;

import org.springframework.boot.autoconfigure.security.SecurityProperties;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.boot.web.servlet.filter.OrderedFilter;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import java.io.IOException;


/** TODO Boot 中往原生 Servlet 容器中添加自定义 Filter : 方式一
 * 1. 实现 Filter 接口
 * 2. 放入到 IOC 容器中
 * 3. @Order 配置当前自定义 Filter 的顺序
 *      SecurityProperties.DEFAULT_FILTER_ORDER 是 Security 的 FilterChainProxy 过滤器的顺序
 *      值越小，优先级越高
 * 4. 自定义 Filter 次序值不能  >=  OrderedFilter.REQUEST_WRAPPER_FILTER_MAX_ORDER
 * */
//@Component
@Order(SecurityProperties.DEFAULT_FILTER_ORDER - 1)
public class CustomFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        System.out.println("CustomFilter Init");
    }

    @Override
    public void destroy() {
        System.out.println("CustomFilter Destroy");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        System.out.println("CustomFilter Filter");
        chain.doFilter(request, response);
    }
}
