package cherry.bootstrap;

import cherry.redis.redistemplate.DistributedLock;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

/**
 * @author lee
 * @date 7/15/21
 */
@SpringBootTest
public class DistributedLockTest {
    @Autowired
    DistributedLock distributedLock;

    @Test
    @Rollback(false)
    void testLock(){
        distributedLock.lock("lemoon");
    }
}
