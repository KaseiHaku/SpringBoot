package kasei.boot.mybatis.controller;


import kasei.boot.mybatis.repository.slaver.dao.example.PersonExample;
import kasei.boot.mybatis.repository.slaver.dao.mapper.PersonMapper;
import kasei.boot.mybatis.repository.slaver.entity.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/SlaverDataSource")
@CrossOrigin // 跨域访问配置
public class SlaverDataSourceController {

    @Autowired private PersonMapper personMapper;


    @GetMapping("/test")
    public String test(){
        return "slaver";
    }


    @GetMapping("/Person")
    public List<Person> mysql(){
        PersonExample personExample = new PersonExample();
        return personMapper.selectByExample(personExample);
    }



}
