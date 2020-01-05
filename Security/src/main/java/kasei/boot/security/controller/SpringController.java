package kasei.boot.security.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

@RestController
@RequestMapping( path = "/Spring", produces = {MediaType.APPLICATION_JSON_VALUE})
public class SpringController {

    @Autowired
    private ApplicationContext applicationContext;


    @GetMapping("/bean")
    public Map<String, String> bean(){
        String[] beanDefinitionNames = applicationContext.getBeanDefinitionNames();
        Arrays.sort(beanDefinitionNames);

        Map<String, String> beanNameTypeMapper = new TreeMap<>();
        Arrays.stream(beanDefinitionNames).forEach(item -> {
            Class<?> type = applicationContext.getType(item, true);
            beanNameTypeMapper.put(item, type.getTypeName());
        });
        return beanNameTypeMapper;
    }
}
