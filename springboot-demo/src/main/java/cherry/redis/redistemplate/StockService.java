package cherry.redis.redistemplate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;

/**
 * @author lee
 * @date 7/15/21
 */
@Service
public class StockService {
    @Autowired
    private DistributedLock distributedLock;
    @Autowired
    private StringRedisTemplate stringRedisTemplate;

    /**
     * 加锁逻辑写在 try 块内，无论是否加锁成功，都会执行解锁逻辑
     */
    public void unsafeDeduct() {
        try {
            Boolean locked = distributedLock.lock("pen_deduct");
            if (!locked) {
                return;
            }
            deductInner();
        } finally {
            distributedLock.unlock("pen_deduct");
        }
    }

    /**
     * 线程1执行过长，锁失效后，线程1执行完成后可能会解锁其他线程加的锁
     * 此情况较难复现
     */
    public void unsafeDeduct2() {
        Boolean locked = distributedLock.lock("pen_deduct");
        if (!locked) {
            return;
        }
        try {
            deductInner();
        } finally {
            distributedLock.unlock("pen_deduct");
        }
    }

    public void deduct() {
        DistributedLock.LockEntity lockEntity = new DistributedLock.LockEntity("pen_deduct",
                String.valueOf(Thread.currentThread().getId()));
        boolean locked = distributedLock.lockAdvanced(lockEntity);
        if (!locked) {
            return;
        }
        try {
            deductInner();
        } finally {
            distributedLock.unlockAdvanced(lockEntity);
        }
    }
    private void deductInner(){
        int stock = Integer.parseInt(stringRedisTemplate.opsForValue().get("pen_stock"));
        if (stock > 0) {
            stock--;
            stringRedisTemplate.opsForValue().set("pen_stock", String.valueOf(stock));
            System.out.println("扣减成功，剩余库存" + stock);
        } else {
            System.out.println("扣减失败");
        }
    }
}
