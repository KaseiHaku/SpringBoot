package kasei.springboot.app.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
@MapperScan(basePackages = {"kasei.springboot.repository.slaver.dao"})
public class SlaverDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.slaver")
    public DataSource slaverDataSource(){
        return DataSourceBuilder.create().build();
    }


    @Bean
    public SqlSessionFactory slaverSqlSessionFactory(@Qualifier("slaverDataSource") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);

        Resource resource = new PathMatchingResourcePatternResolver().getResource("classpath:kasei/springboot/repository/slaver/dao/sqlprovider/*.xml");
        sqlSessionFactoryBean.setMapperLocations(resource);
        sqlSessionFactoryBean.setTypeAliasesPackage("kasei.springboot.repository.slaver.entity");
        return sqlSessionFactoryBean.getObject();
    }


    @Bean
    public DataSourceTransactionManager slaverDataSourceTransactionManager(@Qualifier("slaverDataSource") DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean
    public SqlSessionTemplate slaverSqlSessionTemplate(@Qualifier("slaverSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }



}
