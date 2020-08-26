package kasei.springboot.controller;


import kasei.boot.redis.repository.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/RedisDataSource")
public class RedisDataSourceController {

    @Autowired
    private RedisDao redisDao ;

    @GetMapping("/test")
    public String test(){
        return "redis";
    }


    @GetMapping("/redis")
    public String redis(){
        return redisDao.getStringValueByKey("dd");
    }
}
