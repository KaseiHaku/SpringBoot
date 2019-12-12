package kasei.springboot.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

@Configuration
//@EnableConfigurationProperties
@ConfigurationProperties(prefix = "spring.datasource.redis")
@Data
public class RedisDataSourceConfig {

    private String server;
    private int port;

    @Bean
    public RedisConnectionFactory redisConnectionFactory() {
        RedisConnectionFactory redisConnectionFactory = new LettuceConnectionFactory(server, port);
        return redisConnectionFactory;
    }
}
