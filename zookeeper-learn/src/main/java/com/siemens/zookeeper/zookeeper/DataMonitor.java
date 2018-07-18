package com.siemens.zookeeper.zookeeper;

/**
 * A simple class that monitors the data and existence of a ZooKeeper
 * node. It uses asynchronous ZooKeeper APIs.
 */
import java.util.Arrays;

import org.apache.zookeeper.*;
import org.apache.zookeeper.KeeperException.Code;
import org.apache.zookeeper.data.Stat;

public class DataMonitor implements AsyncCallback.StatCallback {

    ZooKeeper zk;

    String znode;

    Watcher chainedWatcher;

    boolean dead;

    DataMonitorListener listener;

    byte prevData[];

    public DataMonitor(ZooKeeper zk, String znode, Watcher chainedWatcher,
                       DataMonitorListener listener) {
        this.zk = zk;
        this.znode = znode;
        this.chainedWatcher = chainedWatcher;
        this.listener = listener;
        // Get things started by checking if the node exists. We are going
        // to be completely event driven
        zk.exists(znode, true, this, null);
    }

    //每次发生修改节点数据啊，删除节点啊等等事件的时候，都首先由Executor里面注册的这个process方法被调用，
    //然后到这里。
    public void processFromExecutor(WatchedEvent event) {
        System.out.println("checkpoint 1");
        String path = event.getPath();
        if (event.getType() == Watcher.Event.EventType.None) {
            // We are being told that the state of the
            // connection has changed
            switch (event.getState()) {
                case SyncConnected:
                    System.out.println("checkpoint 2");
                    // In this particular example we don't need to do anything
                    // here - watches are automatically re-registered with
                    // server and any watches triggered while the client was
                    // disconnected will be delivered (in order of course)
                    break;
                case Expired:
                    System.out.println("checkpoint 3");
                    // It's all over
                    dead = true;
                    listener.closing(Code.SessionExpired);
                    break;
            }
        } else {
            System.out.println("checkpoint 4");
            if (path != null && path.equals(znode)) {
                // Something has changed on the node, let's find out
                System.out.println("checkpoint 5");
                zk.exists(znode, true, this, null);
            }
        }
        if (chainedWatcher != null) {
            chainedWatcher.process(event);
        }
    }


    //StatCallback的方法，每次调用异步接口中的exists方法时，会执行这个回调方法得到结果。
    //注意，AsyncCallback中的类虽然也是在一些异步方法中注册实例进去，却并不是像Watcher一样由事件触发才
    //调用process方法，而是每一次调用这些异步方法，只要得到了server返回的结果，都会马上执行该回调类中的
    //processResult方法
    @Override
    public void processResult(int rc, String path, Object ctx, Stat stat) {
        System.out.println("checkpoint 6");
        System.out.println("rc = " + rc);
        boolean exists;
        switch (rc) {
            case Code.Ok:
                System.out.println("checkpoint 7");
                exists = true;
                break;
            case Code.NoNode:
                System.out.println("checkpoint 8");
                exists = false;
                break;
            case Code.SessionExpired:
                System.out.println("checkpoint 9");
            case Code.NoAuth:
                System.out.println("checkpoint 10");
                dead = true;
                listener.closing(rc);
                return;
            default:
                System.out.println("checkpoint 11");
                // Retry errors
                zk.exists(znode, true, this, null);
                return;
        }

        byte b[] = null;
        if (exists) {
            System.out.println("checkpoint 12");
            try {
                b = zk.getData(znode, false, null);
            } catch (KeeperException e) {
                // We don't need to worry about recovering now. The watch
                // callbacks will kick off any exception handling
                e.printStackTrace();
            } catch (InterruptedException e) {
                return;
            }
        }
        if ((b == null && b != prevData)
                || (b != null && !Arrays.equals(prevData, b))) {
            System.out.println("checkpoint 13");
            listener.exists(b);
            prevData = b;
        }
    }


}