package kasei.springboot.app.config;


import org.neo4j.driver.v1.AuthTokens;
import org.neo4j.driver.v1.Config;
import org.neo4j.driver.v1.Driver;
import org.neo4j.driver.v1.GraphDatabase;
import org.neo4j.ogm.config.ClasspathConfigurationSource;
import org.neo4j.ogm.config.ConfigurationSource;
import org.neo4j.ogm.session.SessionFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.neo4j.transaction.Neo4jTransactionManager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Properties;

@Configuration
@PropertySource("classpath:neo4j.properties") // 暂时不使用该文件中的配置，而使用 yml 里面的
public class Neo4jDataSourceConfig {

    @Value("${spring.datasource.neo4j.url}")  // 使用了 @Value 注解的类，在其他地方使用时，只能使用 spring 注入，不能直接 new ，否则字段没有注入的值
    private String url;
    @Value("${spring.datasource.neo4j.username}")
    private String username;
    @Value("${spring.datasource.neo4j.password}")
    private String password;
    @Value("${spring.datasource.neo4j.connection.pool.size}")
    private String connectionPoolSize;

    /** todo Neo4j 连接配置 */
    @Bean("neo4jConfiguration")
    public org.neo4j.ogm.config.Configuration getConfiguration(){
        org.neo4j.ogm.config.Configuration configuration = new org.neo4j.ogm.config.Configuration.Builder()
            .uri(this.url)
            .credentials(this.username, this.password)
            .build();
        return configuration;
    }

    /** todo Neo4j OGM SessionFactory 会话配置 */
    @Bean(name = {"neo4jSessionFactory", "sessionFactory"})
    public SessionFactory neo4jSessionFactory() {
        return new SessionFactory(this.getConfiguration(), "kasei.springboot.repository.neo4j.entity");
    }

    /** todo Neo4j 事务配置 */
    @Bean("neo4jTransactionManager")
    public Neo4jTransactionManager neo4jTransactionManager(){
        return new Neo4jTransactionManager(this.neo4jSessionFactory());
    }

    /** todo 添加非 OGM 的 Neo4j 驱动类 */
    @Bean("neo4jNotOgmDriver")
    public Driver neo4jNotOgmDriver() throws IOException {
        InputStream is = this.getClass().getClassLoader().getResourceAsStream("neo4j.properties");
        Properties props = new Properties();
        props.load(is);
        String url = props.getProperty("spring.datasource.neo4j.url");
        String username = props.getProperty("spring.datasource.neo4j.username");
        String password = props.getProperty("spring.datasource.neo4j.password");
        String connectionPoolSize = props.getProperty("spring.datasource.neo4j.connection.pool.size");
        Config config = Config.build()
            .withMaxConnectionPoolSize(Integer.valueOf(connectionPoolSize))
            .toConfig();

        Driver driver = GraphDatabase.driver(url, AuthTokens.basic(username, password), config);
        return driver;
    }



}
