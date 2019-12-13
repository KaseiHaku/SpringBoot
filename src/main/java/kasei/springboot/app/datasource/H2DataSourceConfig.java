package kasei.springboot.app.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

@Configuration
@Profile({"exclude"})
@ConfigurationProperties(prefix = "spring.datasource.h2")
@Data
public class H2DataSourceConfig {

    private String jdbcUrl;
    private String username;
    private String password;
    private String driverClassName;






}
