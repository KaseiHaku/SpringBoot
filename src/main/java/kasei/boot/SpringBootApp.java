package kasei.boot;


import kasei.boot.config.MyExitCodeGenerator;
import kasei.boot.config.listener.CustomApplicationListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.List;


@SpringBootApplication(exclude = {
    DataSourceAutoConfiguration.class
})
//@EnableConfigurationProperties()
public class SpringBootApp {

    public static void main(String[] args) {
        SpringApplication sa = new SpringApplication();
        sa.addPrimarySources(List.of(SpringBootApp.class, MyExitCodeGenerator.class));
        sa.addListeners(new CustomApplicationListener());


        ConfigurableApplicationContext applicationContext = sa.run(args);

        // todo 测试自定义 spring ioc 容器退出代码
        // int exit = SpringApplication.exit(applicationContext);
        // System.exit(exit);
    }
}
