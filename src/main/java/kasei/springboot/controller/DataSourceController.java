package kasei.springboot.controller;

import kasei.springboot.repository.mongo.dao.OrgDao;
import kasei.springboot.repository.mongo.entity.Org;
import kasei.springboot.repository.primary.dao.mapper.Ksftemplate1TestMapper;
import kasei.springboot.repository.primary.entity.Ksftemplate1Test;
import kasei.springboot.repository.redis.RedisDao;
import kasei.springboot.repository.slaver.dao.example.PersonExample;
import kasei.springboot.repository.slaver.dao.mapper.PersonMapper;
import kasei.springboot.repository.slaver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/DataSource")
@CrossOrigin // 跨域访问配置
public class DataSourceController {

    @Autowired private PersonMapper personMapper;
    @Autowired private Ksftemplate1TestMapper ksftemplate1TestMapper;
    @Autowired private RedisDao redisDao ;
    @Autowired private OrgDao orgDao ;


    @GetMapping("/test")
    public String test(){

        return "aa";
    }


    @GetMapping("/oracle")
    public Ksftemplate1Test oracle(){
        return ksftemplate1TestMapper.selectByPrimaryKey("1");
    }

    @GetMapping("/mysql")
    public int mysql(){
        Person person = new Person();
        person.setId("1234");
        person.setName("miku");
        person.setAge(45);
        return personMapper.insert(person);
    }

    @GetMapping("/redis")
    public String redis(){
        return redisDao.getStringValueByKey("dd");
    }

    @GetMapping("/mongo")
    public List<Org> mongo(){
        return orgDao.findAll();
    }


}
