package cherry.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * @author lee
 * @date 7/13/21
 */
public class RedisTemplateDemo {
    @Autowired
    private RedisTemplate redisTemplate;

    public void deleteKey(String key){
        redisTemplate.opsForValue().setIfAbsent(key,1);
    }
}
