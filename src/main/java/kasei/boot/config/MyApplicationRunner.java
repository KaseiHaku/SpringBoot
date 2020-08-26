package kasei.boot.config;

import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/** todo  在  SpringApplication.run(…​) 方法执行完成之前调用
 * 实现 ApplicationRunner  接口，和 CommandLineRunner 接口效果一样
 * */
@Component
public class MyApplicationRunner implements ApplicationRunner {

    @Override
    public void run(ApplicationArguments args) throws Exception {
        System.out.println("MyApplicationRunner");
    }
}
