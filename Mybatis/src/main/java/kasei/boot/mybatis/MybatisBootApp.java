package kasei.boot.mybatis;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


/**
 * spring boot 应用进入 debug
 * -Xdebug -agentlib:jdwp=transport=dt_socket,server=y,suspend=n,address=8000
 * */
@SpringBootApplication
public class MybatisBootApp {
    public static void main(String[] args) {
        SpringApplication.run(MybatisBootApp.class, args);
    }
}
