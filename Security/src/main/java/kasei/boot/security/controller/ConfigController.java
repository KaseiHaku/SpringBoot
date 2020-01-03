package kasei.boot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping( path = "/Config", produces = {MediaType.APPLICATION_JSON_VALUE})
public class ConfigController {

    @Autowired
    private ApplicationContext applicationContext;


    @GetMapping("/bean")
    public String[] getAllUser(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);
        return beanDefinitionNames;
    }
}
