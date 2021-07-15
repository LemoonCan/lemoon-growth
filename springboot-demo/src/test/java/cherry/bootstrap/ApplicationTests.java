package cherry.bootstrap;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 检索顺序是从当前包开始，逐级向上查找被@SpringBootApplication或@SpringBootConfiguration注解的类
 */
@SpringBootTest
class ApplicationTests {

    @Test
    void contextLoads() {
    }
}
