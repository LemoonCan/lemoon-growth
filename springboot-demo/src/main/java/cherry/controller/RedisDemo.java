package cherry.controller;

import cherry.redis.redistemplate.DistributedLock;
import cherry.redis.redistemplate.StockService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author lee
 * @date 7/15/21
 */
@RestController
@RequestMapping("/redisdemo")
@Api(value = "测试")
public class RedisDemo {
    @Autowired
    public DistributedLock distributedLock;
    @Autowired
    public StockService stockService;

    @RequestMapping(method = RequestMethod.POST, value = "/lock")
    @ApiOperation(value = "加锁")
    public void lock(@RequestParam @ApiParam(name = "key", value = "键值", required = true) String key) {
        distributedLock.lock(key);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/stockDeduct")
    @ApiOperation(value = "扣减库存")
    public void stockDeduct() {
        stockService.deduct();
    }

    @RequestMapping(method = RequestMethod.POST, value = "/unsafeStockDeduct")
    @ApiOperation(value = "不安全的扣减库存")
    public void unsafeStockDeduct() {
        stockService.unsafeDeduct();
    }
}
