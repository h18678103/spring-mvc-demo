package redis;

import redis.clients.jedis.Jedis;

/**
 * @author huqinsong
 * @date 2018/9/19
 */
public class RedisJava {
    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.1.118");
        System.out.println("连接成功");
        //查看服务是否运行
        jedis.keys("");
        System.out.println("服务正在运行: "+jedis.ping());
    }
}
