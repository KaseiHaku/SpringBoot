package kasei.springboot.repository.slaver.dao;


import kasei.springboot.repository.slaver.entity.Person;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Repository;

@Qualifier("slaverSqlSessionFactory")
public interface PersonMapper {
    Person selectById(String id);
}
