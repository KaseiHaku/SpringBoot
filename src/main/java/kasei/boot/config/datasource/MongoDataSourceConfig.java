package kasei.boot.config.datasource;

import lombok.Data;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.data.mongodb.repository.config.EnableReactiveMongoRepositories;

@Configuration
@EnableReactiveMongoRepositories
//@ConfigurationProperties(prefix = "spring.datasource.mongo")
@EnableMongoRepositories(basePackages = {"kasei.boot.mongo.repository.mongo.dao"})
@Data
public class MongoDataSourceConfig {


}
