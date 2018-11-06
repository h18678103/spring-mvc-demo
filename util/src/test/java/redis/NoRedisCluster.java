package redis;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author huqinsong
 * @date 2018/9/19
 */
public class NoRedisCluster {

    public static void main(String[] args) {
        notRedisCluster();
    }


    public static void notRedisCluster() {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(2);
        // 最大空闲数
        poolConfig.setMaxIdle(2);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        JedisPool pool = new JedisPool(poolConfig, "192.168.1.54", 6379, 0);
        Jedis jedis = null;
        try {
            for (int i = 0; i < 5; i++) {
                jedis = pool.getResource();
                jedis.set("foo" + i, "bar" + i);
                System.out.println("第" + (i + 1) + "个连接, 得到的值为" + jedis.get("foo" + i));
                // 用完一定要释放连接
                jedis.close();
            }
        } finally {
            pool.close();
        }
    }
}
