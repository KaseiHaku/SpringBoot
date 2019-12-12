package kasei.springboot.repository.redis.impl;

import kasei.springboot.repository.redis.RedisDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.stereotype.Component;

import java.nio.charset.Charset;

@Component
public class RedisDaoImpl implements RedisDao {

    @Autowired
    RedisConnectionFactory redisConnectionFactory;

    @Override
    public String getStringValueByKey(String key) {

        RedisConnection connection = redisConnectionFactory.getConnection();
        byte[] bytes = connection.get(key.getBytes(Charset.forName("utf-8")));
        String result = new String(bytes, Charset.forName("utf-8"));

        return result;
    }
}
