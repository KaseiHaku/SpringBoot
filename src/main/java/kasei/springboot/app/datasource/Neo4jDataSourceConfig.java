package kasei.springboot.app.datasource;


import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.repository.config.EnableNeo4jRepositories;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import java.util.Properties;

@Configuration
@Profile({"exclude"})
@PropertySource("classpath:neo4j.properties")
@EnableNeo4jRepositories(basePackages = {"kasei.springboot.repository.neo4j.dao"}, sessionFactoryRef = "neo4jSessionFactory")
@EntityScan(basePackages = "kasei.springboot.repository.neo4j.entity")
@EnableTransactionManagement
public class Neo4jDataSourceConfig {

    @Value("${spring.datasource.neo4j.url}")  // 使用了 @Value 注解的类，在其他地方使用时，只能使用 spring 注入，不能直接 new ，否则字段没有注入的值
    private String url;
    @Value("${spring.datasource.neo4j.username}")
    private String username;
    @Value("${spring.datasource.neo4j.password}")
    private String password;
    @Value("${spring.datasource.neo4j.connection.pool.size}")
    private Integer connectionPoolSize;

    /** todo Neo4j 连接配置 */
    @Bean("neo4jConfiguration")
    public org.neo4j.ogm.config.Configuration neo4jConfiguration() {
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
            .uri(this.url)
            .credentials(this.username, this.password)
            .connectionPoolSize(connectionPoolSize)
            .build();
        return configuration;
    }


    /** todo Neo4j OGM SessionFactory 会话配置 */
    @Bean(name = {"neo4jSessionFactory"})
    public SessionFactory neo4jSessionFactory() {
        return new SessionFactory(this.neo4jConfiguration(), "kasei.springboot.repository.neo4j.entity");
    }

    /** todo Neo4j 事务配置 */
    @Bean("neo4jTransactionManager")
    public Neo4jTransactionManager neo4jTransactionManager(){
        return new Neo4jTransactionManager(this.neo4jSessionFactory());
    }




}
