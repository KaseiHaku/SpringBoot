package kasei.springboot.repository.redis;


public interface RedisDao {

    String getStringValueByKey(String key);
}
