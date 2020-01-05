package kasei.boot.security;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
//@EnableGlobalMethodSecurity(securedEnabled = true) // 开启 Method 权限管理
public class SecurityBootApp {
    public static void main(String[] args) {

        SpringApplication.run(SecurityBootApp.class, args);
    }
}
