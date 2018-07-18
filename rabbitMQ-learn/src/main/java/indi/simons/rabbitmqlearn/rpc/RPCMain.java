package indi.simons.rabbitmqlearn.rpc;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class RPCMain {

    public static void main(String[] args) throws IOException, TimeoutException, InterruptedException {

        RPCClient rpcClient = new RPCClient();

        String answer = rpcClient.call("侯霄枭大傻逼");

        System.out.println(answer);

        rpcClient.close();


    }

}
