package kasei.boot.security;

import kasei.boot.security.service.MessageService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.security.test.context.support.TestExecutionEvent;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.ContextConfiguration;

import java.util.Map;

@SpringBootTest
@ContextConfiguration
@WithMockUser(setupBefore = TestExecutionEvent.TEST_EXECUTION)
class SecurityBootAppTest {


    @Autowired
    ApplicationContext applicationContext;
    @Autowired
    MessageService messageService;

    @Test
    public void test(){
        Map<String, Object> beansOfType = applicationContext.getBeansOfType(Object.class);

        beansOfType.forEach((k, v) -> System.out.println(k));

    }

    @Test
    public void getMessageUnauthenticated() {
        String message = messageService.getMessage();
        System.out.println(message);
    }

}
