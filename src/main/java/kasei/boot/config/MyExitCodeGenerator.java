package kasei.boot.config;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;


/** todo 自定义 spring ioc 容器退出代码 */
@Component
public class MyExitCodeGenerator implements ExitCodeGenerator {

    @Override
    public int getExitCode() {
        return 128;
    }
}
