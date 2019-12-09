package kasei.springboot.controller;

import kasei.springboot.repository.slaver.dao.PersonMapper;
import kasei.springboot.repository.slaver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Hello")
@CrossOrigin // 跨域访问配置
public class HelloController {

    @Autowired
    PersonMapper personMapper;


    @GetMapping("/test")
    public String test(){

        return "aa";
    }


    @GetMapping("/person")
    public Person getPerson(){
        return personMapper.selectById("1");
    }


}
