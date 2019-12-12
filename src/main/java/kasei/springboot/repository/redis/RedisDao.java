package kasei.springboot.repository.redis;

import org.springframework.stereotype.Component;


public interface RedisDao {

    String getStringValueByKey(String key);
}
