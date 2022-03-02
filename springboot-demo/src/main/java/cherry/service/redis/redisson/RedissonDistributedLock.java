package cherry.service.redis.redisson;

import org.redisson.api.RLock;
import org.redisson.api.RReadWriteLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author lee
 * @date 7/21/21
 */
@Component
public class RedissonDistributedLock{
    @Autowired
    private RedissonClient redissonClient;

    public void lock(String lockKey){
        RLock redissonLock = redissonClient.getLock(lockKey);
        redissonLock.lock();
    }

    public void readWriteLock(String lockKey){
        RReadWriteLock rwLock = redissonClient.getReadWriteLock(lockKey);
        RLock rLock = rwLock.readLock();
        rLock.lock();
    }
}
