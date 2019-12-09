package kasei.springboot.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/WebFlux")
public class WebFluxController {

    @GetMapping("/test")
    public Mono<String> test(){
        return Mono.empty();
    }


}
