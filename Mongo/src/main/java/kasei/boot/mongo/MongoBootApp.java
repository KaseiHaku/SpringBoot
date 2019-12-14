package kasei.boot.mongo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;

@SpringBootApplication
@EnableMongoRepositories(basePackages = {"kasei.boot.mongo.repository.mongo.dao"})
public class MongoBootApp {

    public static void main(String[] args) {
        SpringApplication.run(MongoBootApp.class, args);
    }
}
