package com.siemens.zookeeper.zookeeper; /**
 * A simple example program to use DataMonitor to start and
 * stop executables based on a znode. The program watches the
 * specified znode and saves the data that corresponds to the
 * znode in the filesystem. It also starts the specified program
 * with the specified arguments when the znode exists and kills
 * the program if the znode goes away.
 */
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class Executor implements Watcher, Runnable
{
    String znode;

    DataMonitor dm;

    ZooKeeper zk;

    String exec[];

    Process child;

    public Executor(String hostPort, String znode, String filename,
                    String exec[]) throws KeeperException, IOException {
        this.exec = exec;
        zk = new ZooKeeper(hostPort, 3000, this);
        System.out.println("Connected!");
        dm = new DataMonitor(zk, znode, null, new ListnerHimself(child,filename,exec,this));
    }


    /***************************************************************************
     * We do process any events ourselves, we just need to forward them on.
     *
     * //@see org.apache.zookeeper.Watcher#process(org.apache.zookeeper.proto.WatcherEvent)
     */

    //Thread method
    public void run() {
        try {
            synchronized (this) {
                while (!dm.dead) {
                    wait();
                }
            }
        } catch (InterruptedException e) {
        }
    }

    //Watcher method
    public void process(WatchedEvent event) {
        dm.processFromExecutor(event);
    }

}