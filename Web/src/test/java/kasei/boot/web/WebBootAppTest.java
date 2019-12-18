package kasei.boot.web;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class WebBootAppTest {


    @Autowired
    ApplicationContext applicationContext;

    @Test
    public void test(){
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);

        beansOfType.forEach((k, v) -> System.out.println(k));

    }

}
