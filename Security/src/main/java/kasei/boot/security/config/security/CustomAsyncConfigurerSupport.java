package kasei.boot.security.config.security;

import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.security.concurrent.DelegatingSecurityContextExecutorService;

import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

//@Configuration
//@EnableAsync
public class CustomAsyncConfigurerSupport extends AsyncConfigurerSupport {

    /** TODO 使用 Spring @Async 异步执行方法时，传播 Security 的 SecurityContext 到异步线程中的配置
     * */
    @Override
    public Executor getAsyncExecutor() {
        // TODO @Research 值得深入研究的是：能不能使用 spring 自带的 ThreadPoolTaskExecutor ，而不是这里使用的 jdk 自带的
        ExecutorService executorService = Executors.newFixedThreadPool(5);
        return new DelegatingSecurityContextExecutorService(executorService);
    }
}
