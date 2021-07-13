package redis;

import redis.clients.jedis.Jedis;

/**
 * @author lee
 * @date 7/12/21
 */
public class DistributedLock {
    Jedis jedis = RedisConnect.connect();

    public Boolean lock(String lockKey, Long expireTime) {
        Long flag = jedis.setnx(lockKey, String.valueOf(Thread.currentThread().getId()));
        if(flag == 0){
            return false;
        }
        jedis.expireAt(lockKey, expireTime);
        return true;
    }

    public void unlock(String lockKey) {
        String threadId = jedis.get(lockKey);
        if (String.valueOf(Thread.currentThread().getId()).equals(threadId)) {
            jedis.del(lockKey);
        }
    }
}
