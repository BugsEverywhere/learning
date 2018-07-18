package indi.simon.redislearn.basicoperation;

import redis.clients.jedis.Jedis;

import java.util.List;

public class RedisListJava {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.22.130");
        jedis.ping();
        System.out.println("连接成功");
        //存储数据到列表中
        jedis.lpush("site-list", "Runoob","Google");
        jedis.lpush("site-list", "Microsoft");
        jedis.lpush("site-list", "Alibaba");
        // 获取存储的数据并输出
        List<String> list = jedis.lrange("site-list", 0 ,2);
        for(int i=0; i<list.size(); i++) {
            System.out.println("列表项为: "+list.get(i));
        }
    }

}
