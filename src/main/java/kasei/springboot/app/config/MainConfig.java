package kasei.springboot.app.config;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

@Configuration
@EnableConfigurationProperties
@Import({
    //PrimaryDataSourceConfig.class,
    SlaverDataSourceConfig.class
})
public class MainConfig {
}
