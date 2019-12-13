package kasei.springboot.app.datasource;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.data.mongodb.MongoDbFactory;
import org.springframework.data.mongodb.core.SimpleMongoClientDbFactory;

@Configuration
@Profile({"exclude"})
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
