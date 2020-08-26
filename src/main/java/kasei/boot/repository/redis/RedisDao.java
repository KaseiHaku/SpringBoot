package kasei.boot.repository.redis;


public interface RedisDao {

    String getStringValueByKey(String key);
}
