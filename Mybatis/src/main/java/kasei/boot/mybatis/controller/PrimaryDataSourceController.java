package kasei.boot.mybatis.controller;


import kasei.boot.mybatis.repository.primary.dao.mapper.Ksftemplate1TestMapper;
import kasei.boot.mybatis.repository.primary.entity.Ksftemplate1Test;
import kasei.boot.mybatis.repository.slaver.dao.mapper.PersonMapper;
import kasei.boot.mybatis.repository.slaver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Profile;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;



@Profile({"company"})
@RestController
@RequestMapping("/PrimaryDataSource")
@CrossOrigin // 跨域访问配置
public class PrimaryDataSourceController {

    @Autowired private Ksftemplate1TestMapper ksftemplate1TestMapper;


    @GetMapping("/test")
    public String test(){
        return "primary";
    }


    @GetMapping("/Ksftemplate1Test")
    public Ksftemplate1Test oracle(){
        return ksftemplate1TestMapper.selectByPrimaryKey("1");
    }


}
