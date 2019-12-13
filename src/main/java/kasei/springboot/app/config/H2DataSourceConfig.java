package kasei.springboot.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.h2")
@Data
public class H2DataSourceConfig {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;






}
