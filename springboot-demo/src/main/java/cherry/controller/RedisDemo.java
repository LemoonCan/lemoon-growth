package cherry.controller;

import cherry.service.redis.redisson.RedissonDistributedLock;
import cherry.service.redis.redistemplate.DistributedLock;
import cherry.service.redis.redistemplate.StockService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @since 7/15/21
 */
@RestController
@RequestMapping("/redis")
@Tag(name = "redis-测试")
public class RedisDemo {
    @Autowired
    @Qualifier("distributedLock")
    public DistributedLock distributedLock;
    @Autowired
    public StockService stockService;

    @Autowired
    public RedissonDistributedLock redissonDistributedLock;

    @RequestMapping(method = RequestMethod.POST, value = "/lock")
    @Operation(description = "加锁")
    public void lock(@RequestParam @Parameter(name = "key", description = "键值", required = true) String key) {
        distributedLock.lock(key);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/redissonLock")
    @Operation(description = "加锁")
    public void redissonLock(@RequestParam @Parameter(name = "key", description = "键值", required = true) String key) {
        redissonDistributedLock.lock(key);
    }


    @RequestMapping(method = RequestMethod.POST, value = "/stockDeduct")
    @Operation(description = "扣减库存")
    public void stockDeduct() {
        stockService.deduct();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unsafeStockDeduct")
    @Operation(description = "不安全的扣减库存")
    public void unsafeStockDeduct() {
        stockService.unsafeDeduct();
    }
}
