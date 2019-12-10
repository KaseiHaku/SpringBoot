package kasei.springboot;

import com.zaxxer.hikari.HikariDataSource;
import kasei.springboot.repository.slaver.dao.mapper.PersonMapper;
import kasei.springboot.repository.slaver.entity.Person;
import org.apache.ibatis.session.SqlSessionFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.autoconfigure.web.client.AutoConfigureWebClient;
import org.springframework.boot.test.context.SpringBootTest;

import javax.sql.DataSource;
import javax.xml.crypto.Data;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class SpringBootAppTest {

    // @Autowired PersonMapper personMapper;

    @Autowired
        @Qualifier("slaverSqlSessionFactory")
    SqlSessionFactory factory;

    @Test
    public void mainTest() throws SQLException {

        factory.getConfiguration().getMappedStatementNames().forEach(System.out::println);






        //Person person = personMapper.selectById("1");
        //System.out.println(person.getName());
    }

}
