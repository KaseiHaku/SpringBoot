package kasei.springboot.repository.primary.dao.mapper;

import kasei.boot.mybatis.repository.primary.dao.example.Ksftemplate1TestExample;
import kasei.boot.mybatis.repository.primary.entity.Ksftemplate1Test;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface Ksftemplate1TestMapper {
    long countByExample(Ksftemplate1TestExample example);

    int deleteByExample(Ksftemplate1TestExample example);

    int deleteByPrimaryKey(String wfOrunid);

    int insert(Ksftemplate1Test record);

    int insertSelective(Ksftemplate1Test record);

    List<Ksftemplate1Test> selectByExample(Ksftemplate1TestExample example);

    Ksftemplate1Test selectByPrimaryKey(String wfOrunid);

    int updateByExampleSelective(@Param("record") Ksftemplate1Test record, @Param("example") Ksftemplate1TestExample example);

    int updateByExample(@Param("record") Ksftemplate1Test record, @Param("example") Ksftemplate1TestExample example);

    int updateByPrimaryKeySelective(Ksftemplate1Test record);

    int updateByPrimaryKey(Ksftemplate1Test record);
}
