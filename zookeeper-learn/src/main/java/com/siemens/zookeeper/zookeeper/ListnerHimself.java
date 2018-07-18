package com.siemens.zookeeper.zookeeper;

import java.io.FileOutputStream;
import java.io.IOException;

/**
 * Created by Chen Zhuo on 2017/12/15.
 */
public class ListnerHimself implements DataMonitorListener {

    Process child;

    String filename;

    String exec[];

    Object lockObject;


    public ListnerHimself(Process inputChild, String inputFilename, String[] inputExec, Object inputLock) {

        child = inputChild;
        filename = inputFilename;
        exec = inputExec;
        lockObject = inputLock;

    }

    //Listener methods
    //此方法在
    public void closing(int rc) {
        synchronized (lockObject) {
            lockObject.notifyAll();
        }
    }

    public void exists(byte[] data) {
        if (data == null) {
            if (child != null) {
                System.out.println("Killing process");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                }
            }
            child = null;
        } else {
            if (child != null) {
                System.out.println("Stopping child");
                child.destroy();
                try {
                    child.waitFor();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            try {
                FileOutputStream fos = new FileOutputStream(filename);
                fos.write(data);
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                //只是示例，可以在所监视的节点发生改变的时候触发某子程序
                System.out.println("Starting child");
                    child = Runtime.getRuntime().exec(exec);
                //打印输入和错误
                new StreamWriter(child.getInputStream(), System.out);
                new StreamWriter(child.getErrorStream(), System.err);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
