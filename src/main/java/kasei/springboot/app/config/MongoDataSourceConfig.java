package kasei.springboot.app.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Configuration
@ConfigurationProperties(prefix = "spring.datasource.mongo")
@Data
public class MongoDataSourceConfig {

    private String uri;


    @Bean
    public MongoDbFactory mongoDbFactory(){
        MongoDbFactory mongoDbFactory = new SimpleMongoClientDbFactory(uri);
        return mongoDbFactory;
    }

}
