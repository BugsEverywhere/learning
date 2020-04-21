package indi.simon.learning.redislearn.basicoperation;

import redis.clients.jedis.Jedis;

public class ConnectMain {

    public static void main(String[] args) {
        //连接本地的 Redis 服务
        Jedis jedis = new Jedis("192.168.22.130");

        //查看服务是否运行
        System.out.println("服务正在运行: "+jedis.ping());
        System.out.println("连接成功");
    }
}
