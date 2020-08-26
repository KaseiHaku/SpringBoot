package kasei.boot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/AppArgs")
public class AppArgsController {



    @Autowired ApplicationArguments appArgs; // 获取 shell> java -jar spring-boot.jar 命令的参数

    @GetMapping("/test")
    public void autowiredApplicationArguments(){
        appArgs.getOptionNames().forEach( x -> {
            System.out.println("appArgs = " + x);
        });
    }


}
