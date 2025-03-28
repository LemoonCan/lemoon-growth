package cherry.bootstrap.configure;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;

/**
 * @author lee
 * @since 7/14/21
 */
@Configuration
public class RedisTemplateConfigure {
    /**
     * RedisTemplate factory配置
     * @return
     */
    @Bean
    JedisConnectionFactory jedisConnFactory(){
        JedisConnectionFactory jedisConnFactory = new JedisConnectionFactory();
        jedisConnFactory.setUsePool(true);
        return jedisConnFactory;
    }
}
