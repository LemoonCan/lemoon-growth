package cherry.service.redis.redistemplate;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.concurrent.TimeUnit;

/**
 * @author lee
 * @since 7/13/21
 */
@Component
public class DistributedLock {
    @Autowired
    private RedisTemplate redisTemplate;

    /**
     * 互斥
     * 防死锁(锁加有效期)
     *
     * @param key
     * @return
     */
    public Boolean lock(String key) {
        return redisTemplate.opsForValue().setIfAbsent(key, "happy", 1, TimeUnit.SECONDS);
    }

    public void unlock(String key) {
        redisTemplate.delete(key);
    }

    /**
     * 互斥
     * 防死锁(锁加有效期)
     * 加锁和解锁是同一个线程(通过 requestId 是否相同判断)
     */
    public boolean lockAdvanced(LockEntity lockEntity) {
        //TODO 参数校验
        boolean locked = false;
        int tryCount = 3;
        while (!locked && tryCount > 0) {
            try {
                locked = redisTemplate.opsForValue().setIfAbsent(lockEntity.getKey(), lockEntity.getRequestId(), 1, TimeUnit.MILLISECONDS);
            } catch (Exception e) {
                e.printStackTrace();
            }
            tryCount --;
        }
        return locked;
    }

    /**
     * 非原子性操作，可能会有问题
     * @param lockEntity
     */
    public void unlockAdvanced(LockEntity lockEntity) {
        //TODO 参数校验
        //requestId相同才可解锁
        String requestId = (String) redisTemplate.opsForValue().get(lockEntity.getKey());
        if(lockEntity.getRequestId().equals(requestId)){
            redisTemplate.delete(lockEntity.getKey());
        }
    }

    /**
     * 原子性操作
     * @param lockEntity
     * @return
     */
    public boolean unlockLua(LockEntity lockEntity) {
        DefaultRedisScript<Long> redisScript = new DefaultRedisScript();
        //用于解锁的lua脚本位置
        redisScript.setLocation(new ClassPathResource("unlock.lua"));
        redisScript.setResultType(Long.class);
        //没有指定序列化方式，默认使用上面配置的
        Object result = redisTemplate.execute(redisScript, Arrays.asList(lockEntity.getKey()), lockEntity.getRequestId());
        return result.equals(Long.valueOf(1));
    }

    @Data
    @AllArgsConstructor
    public static class LockEntity{
        private String key;
        private String requestId;
    }
}
