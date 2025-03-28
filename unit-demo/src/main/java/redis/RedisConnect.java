package redis;

import redis.clients.jedis.Jedis;

/**
 * @author lee
 * @since 7/12/21
 */
public class RedisConnect {
    public static void main(String[] args) {
        connect();
    }

    public static Jedis connect(){
        Jedis jedis = new Jedis("localhost");
        System.out.println("连接成功");
        System.out.println("服务正在运行: "+jedis.ping());
        return jedis;
    }
}
