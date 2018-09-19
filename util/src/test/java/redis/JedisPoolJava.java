package redis;

import java.io.IOException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPool;
import redis.clients.jedis.JedisPoolConfig;

/**
 * @author huqinsong
 * @date 2018/9/19
 */
public class JedisPoolJava {
    private static final String key = "age";
    private static final int max = 5; //最大次数
    private static final int len = 14; //单位长度
    private static final int outTime = 10; //second
    private static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        JedisPoolConfig poolConfig = new JedisPoolConfig();
        // 最大连接数
        poolConfig.setMaxTotal(1);
        // 最大空闲数
        poolConfig.setMaxIdle(1);
        // 最大允许等待时间，如果超过这个时间还未获取到连接，则会报JedisException异常：
        // Could not get a resource from the pool
        poolConfig.setMaxWaitMillis(1000);
        Set<HostAndPort> nodes = new LinkedHashSet<>();
        nodes.add(new HostAndPort("192.168.1.118", 7001));
        nodes.add(new HostAndPort("192.168.1.118", 7002));
        nodes.add(new HostAndPort("192.168.1.118", 7003));
        JedisCluster cluster = new JedisCluster(nodes, poolConfig);

        while (true) {
            int input = sc.nextInt();
            if (input == -1) {
                System.out.println("系统退出!");
                break;
            }
            if (validate(cluster, key)) {
                System.out.println("验证通过!");
            } else {
                System.out.println("验证失败!");
            }
        }
        try {
            cluster.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static boolean validate(JedisCluster cluster, String key) {
        String value = cluster.get(key);
        System.out.println("validate value=" + value);
        String append = System.currentTimeMillis()+",";
        if (value == null) {
            value = append;
            System.out.println("验证通过001! times=1, 更新缓存，value=" + value);
            cluster.setex(key, outTime, value);
            return true;
        } else {
            int times = value.length() / len + 1;
            if (times <= max) {
                value += append;
                System.out.println("验证通过002! times=" + times + " ,更新缓存，value=" + value);
                cluster.setex(key, outTime, value);
                return true;
            }

            String[] split = value.split(",");
            StringBuilder sb = new StringBuilder();
            for (String s : split) {
                long l = Long.parseLong(s);
                if (System.currentTimeMillis() - l < outTime * 1000) {
                    sb.append(s + ",");
                }
            }

            times = sb.length() / len;
            if (times < max) {
                sb.append(append);
                System.out.println("验证通过003! times=" + times+" ,更新缓存，value=" + sb.toString());
                cluster.setex(key, outTime, sb.toString());
                return true;
            }
        }
        return false;
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
        JedisPool pool = new JedisPool(poolConfig, "192.168.1.118", 6379, 0, "123");
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
