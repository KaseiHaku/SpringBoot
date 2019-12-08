package kasei.springboot.app;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties("bean.properties")
public class JavaBeanProperties {
}
