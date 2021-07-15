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

    public void deduct() {
        DistributedLock.LockEntity lockEntity = new DistributedLock.LockEntity("pen_deduct",
                String.valueOf(Thread.currentThread().getId()));
        Boolean locked = distributedLock.lockAdvanced(lockEntity);
        if (!locked) {
            return;
        }
        try {
            deductInner();
        } finally {
            distributedLock.unlockAdvanced(lockEntity);
        }
    }

    public void unsafeDeduct() {
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
