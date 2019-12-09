package kasei.springboot.controller;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Hello")
@CrossOrigin // 跨域访问配置
public class HelloController {



    @GetMapping("/test")
    public String test(){

        return "aa";
    }


}
