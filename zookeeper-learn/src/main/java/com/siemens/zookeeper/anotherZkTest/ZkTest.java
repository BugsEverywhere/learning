package com.siemens.zookeeper.anotherZkTest;

import org.apache.zookeeper.*;

import java.io.IOException;

/**
 * Created by Chen Zhuo on 2017/12/8.
 */
public class ZkTest {

    public static void main(String[] args) throws Exception {
        //模拟app1通过zk1提交x1,app2通过zk2提交x2,app3通过zk3提交x3
        doAction(1);
        doAction(2);
        doAction(3);
    }


    //以此集群的3台机器上加入某成员
    public static void doAction(int client) throws Exception {
        String host1 = "zookeeperServer1:2181";
        String host2 = "zookeeperServer2:2181";
        String host3 = "zookeeperServer3:2181";
        ZooKeeper zk = null;
        switch (client) {
            case 1:
                zk = connection(host1);
                initQueue(zk);
                joinQueue(zk, 1);
                break;
            case 2:
                zk = connection(host2);
                initQueue(zk);
                joinQueue(zk, 2);
                break;
            case 3:
                zk = connection(host3);
                initQueue(zk);
                joinQueue(zk, 3);
                break;
        }
    }

    // 创建一个与服务器的连接
    public static ZooKeeper connection(String host) throws IOException {
        ZooKeeper zk = new ZooKeeper(host, 60000, new Watcher() {
            // 监控所有被触发的事件
            public void process(WatchedEvent event) {
                if (event.getType() == Event.EventType.NodeCreated && event.getPath().equals("/queue/start")) {
                    System.out.println("Queue has Completed.Finish testing!!!");
                }
            }
        });
        return zk;
    }

    //初始化队列
    public static void initQueue(ZooKeeper zk) throws KeeperException, InterruptedException {

        System.out.println("WATCH => /queue/start");

        //当这个znode节点被改变时，将会触发当前Watcher
        zk.exists("/queue/start", true);

        //如果/queue目录为空，创建此节点
        if (zk.exists("/queue", false) == null) {
            System.out.println("create /queue task-queue");
            zk.create("/queue", "task-queue".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        } else {
            System.out.println("/queue is exist!");
        }
    }

    //成员加入队列
    public static void joinQueue(ZooKeeper zk, int x) throws KeeperException, InterruptedException {
        System.out.println("create /queue/x" + x + " x" + x);
        zk.create("/queue/x" + x, ("x" + x).getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.EPHEMERAL_SEQUENTIAL);
        isCompleted(zk);
    }

    //判断队列是否已满
    public static void isCompleted(ZooKeeper zk) throws KeeperException, InterruptedException {
        //规定队列大小
        int size = 3;
        //查询成员数
        int length = zk.getChildren("/queue", true).size();
        System.out.println("Queue Complete:" + length + "/" + size);
        if (length >= size) {
            System.out.println("create /queue/start start");
            zk.create("/queue/start", "start".getBytes(), ZooDefs.Ids.OPEN_ACL_UNSAFE, CreateMode.PERSISTENT);
        }
    }
}
