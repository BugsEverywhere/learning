package com.siemens.zookeeper.zookeeper;

/**
 * Created by Chen Zhuo on 2017/12/15.
 */
public class MainFunctionClass {

    /**
     * @param args
     */
    public static void main(String[] args) {

        String hostPort = "192.168.22.130:2181";
        String znode = "/zk_testNode/zk_testNode_level2";
        String filename = "C:\\Users\\Simons\\Desktop\\zookeeper_file\\zookeeper.txt";
        String exec[] = new String[args.length];
        //System.arraycopy(args, 3, exec, 0, exec.length);
        try {
            new Executor(hostPort, znode, filename, exec).run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }



}
