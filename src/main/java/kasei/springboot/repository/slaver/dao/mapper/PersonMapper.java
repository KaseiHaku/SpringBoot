package kasei.springboot.repository.slaver.dao.mapper;

import java.util.List;
import kasei.springboot.repository.slaver.dao.example.PersonExample;
import kasei.springboot.repository.slaver.entity.Person;
import org.apache.ibatis.annotations.Param;

public interface PersonMapper {
    long countByExample(PersonExample example);

    int deleteByExample(PersonExample example);

    int insert(Person record);

    int insertSelective(Person record);

    List<Person> selectByExample(PersonExample example);

    int updateByExampleSelective(@Param("record") Person record, @Param("example") PersonExample example);

    int updateByExample(@Param("record") Person record, @Param("example") PersonExample example);
}