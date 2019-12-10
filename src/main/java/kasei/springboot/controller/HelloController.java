package kasei.springboot.controller;

import kasei.springboot.repository.primary.dao.mapper.Ksftemplate1TestMapper;
import kasei.springboot.repository.primary.entity.Ksftemplate1Test;
import kasei.springboot.repository.slaver.dao.example.PersonExample;
import kasei.springboot.repository.slaver.dao.mapper.PersonMapper;
import kasei.springboot.repository.slaver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Hello")
@CrossOrigin // 跨域访问配置
public class HelloController {

    @Autowired private PersonMapper personMapper;
    @Autowired private Ksftemplate1TestMapper ksftemplate1TestMapper;


    @GetMapping("/test")
    public String test(){

        return "aa";
    }


    @GetMapping("/user")
    public Ksftemplate1Test getPerson(){
        return ksftemplate1TestMapper.selectByPrimaryKey("1");
    }

    @GetMapping("/person")
    public int getPerson2(){
        Person person = new Person();
        person.setId("1234");
        person.setName("miku");
        person.setAge(45);
        return personMapper.insert(person);
    }


}
