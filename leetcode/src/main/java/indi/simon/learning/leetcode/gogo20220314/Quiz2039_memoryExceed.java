package indi.simon.learning.leetcode.gogo20220314;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz2039_memoryExceed {

    public static void main(String[] args) {
        Quiz2039_memoryExceed quiz2039 = new Quiz2039_memoryExceed();
        int[][] edges = new int[][]{{0, 1}, {0, 2}, {1, 2}};
        int[] patience = {0, 10, 10};
        int res = quiz2039.networkBecomesIdle(edges, patience);
        System.out.println(res);
    }

    public int networkBecomesIdle(int[][] edges, int[] patience) {
        if (edges.length == 0) {
            return 0;
        }
        ServerNode[] nodeArr = new ServerNode[patience.length];
        for (int i = 0; i < nodeArr.length; i++) {
            nodeArr[i] = new ServerNode(i);
        }

        //网络构建好
        for (int[] arrJ : edges) {
            nodeArr[arrJ[0]].connections.add(nodeArr[arrJ[1]]);
            nodeArr[arrJ[1]].connections.add(nodeArr[arrJ[0]]);
        }

        //计算每个节点到主服务器的最短路径
        for (ServerNode singleNode : nodeArr) {
            singleNode.messagePath = shortestPathToMainServer(singleNode, new ArrayList<>(), Integer.MAX_VALUE);
        }

        int second = 0;
        //开始计时
        for (; ; second++) {
            //将所有服务器的邮箱搬迁一下
            for (ServerNode singleNode : nodeArr) {
                //然后将nextMailBox的邮件搬到lastMailBox中来，为下一次处理做准备
                singleNode.lastMailBox.addAll(singleNode.nextMailBox);
                singleNode.nextMailBox.clear();
            }

            for (ServerNode singleNode : nodeArr) {
                if (singleNode.index == 0) {
                    //主服务器
                    //检查邮箱
                    Iterator<Message> lastMessageIterator = singleNode.lastMailBox.listIterator();
                    while (lastMessageIterator.hasNext()) {
                        //必然是请求邮件，回复之
                        Message msg = lastMessageIterator.next();
                        msg.isResponse = true;
                        msg.path.get(msg.pathIndex - 1).nextMailBox.add(msg);
                        msg.pathIndex--;
                        //顺便帮下一个人看看是否是他的
                        if (msg.path.get(msg.pathIndex).equals(msg.path.get(0))) {
                            msg.path.get(msg.pathIndex).isResponded = true;
                        }
                        //自己的邮箱删掉这封邮件
                        lastMessageIterator.remove();
                    }
                } else {
                    //其他服务器
                    if (second == 0) {
                        //第0秒先发为敬
                        Message message = new Message(0, singleNode.messagePath, false);
                        singleNode.messagePath.get(0).nextMailBox.add(message);
                        singleNode.lastEmitSecond = 0;
                    } else {
                        //除0以外其他时间
                        //先处理一下自己的邮箱
                        Iterator<Message> messageIterator = singleNode.lastMailBox.listIterator();
                        while (messageIterator.hasNext()) {
                            Message msg = messageIterator.next();
                            if (msg.isResponse) {
                                //是回复邮件，先看一下是不是回复自己的
                                if (msg.path.get(0).equals(singleNode)) {
                                    //是自己的，置位
                                    singleNode.isResponded = true;
                                } else {
                                    //不是自己的，转发
                                    msg.path.get(msg.pathIndex - 1).nextMailBox.add(msg);
                                    msg.pathIndex--;
                                    //顺便帮下一个人看看是否是他的
                                    if (msg.path.get(msg.pathIndex).equals(msg.path.get(0))) {
                                        msg.path.get(msg.pathIndex).isResponded = true;
                                    }
                                }
                            } else {
                                //是请求邮件，无脑转发
                                msg.path.get(msg.pathIndex + 1).nextMailBox.add(msg);
                                msg.pathIndex++;
                            }
                            //自己的邮箱删掉这封邮件
                            messageIterator.remove();
                        }
                        //再看看自己要不要发消息
                        if ((second - singleNode.lastEmitSecond) >= patience[singleNode.index] && !singleNode.isResponded) {
                            //到钟了，且没有收到回复，就发射一条
                            Message message = new Message(0, singleNode.messagePath, false);
                            singleNode.messagePath.get(0).nextMailBox.add(message);
                            singleNode.lastEmitSecond = second;
                        }
                    }
                }
            }

            //等所有节点在本秒状态更新完了，检查他们的状态
            int quietNodeCount = 0;
            for (ServerNode singleNode : nodeArr) {
                if (singleNode.index == 0 && singleNode.nextMailBox.size() == 0) {
                    //主服务器清净了
                    quietNodeCount++;
                } else if (singleNode.index != 0 && singleNode.nextMailBox.size() == 0 && singleNode.isResponded) {
                    //从服务器也清净了
                    quietNodeCount++;
                }
            }

            if (quietNodeCount == nodeArr.length) {
                break;
            }
        }

        return second;
    }

    private List<ServerNode> shortestPathToMainServer(ServerNode currentNode, List<ServerNode> path, int minLengthSoFar) {
        path.add(currentNode);
        if (currentNode.index == 0) {
            return path;
        }

        if (path.size() >= minLengthSoFar) {
            return null;
        }

        int minPathLengthForNow = Integer.MAX_VALUE;
        List<ServerNode> minLengthPath = null;

        for (ServerNode singleConnection : currentNode.connections) {
            //跳过来时的路
            if (path.size() > 0 && singleConnection.index == path.get(path.size() - 1).index) {
                continue;
            }
            List<ServerNode> pathForThisConnection = shortestPathToMainServer(singleConnection, new ArrayList<>(path), minPathLengthForNow);
            //此路不通
            if (pathForThisConnection == null) {
                continue;
            }
            //此路通了，看一看长度
            if (pathForThisConnection.size() < minPathLengthForNow) {
                minPathLengthForNow = pathForThisConnection.size();
                minLengthPath = pathForThisConnection;
            }
        }
        return minLengthPath;
    }


    private class ServerNode {

        public ServerNode(int index) {
            this.index = index;
            connections = new ArrayList<>();
            lastMailBox = new ArrayList<>();
            nextMailBox = new ArrayList<>();
        }

        private boolean isResponded;

        private int lastEmitSecond;

        private int index;

        private List<ServerNode> messagePath;

        private List<ServerNode> connections;

        private List<Message> lastMailBox;

        private List<Message> nextMailBox;
    }

    private class Message {

        private boolean isResponse;

        public Message(int pathIndex, List<ServerNode> path, boolean isResponse) {
            this.pathIndex = pathIndex;
            this.path = path;
        }

        private int pathIndex;

        private List<ServerNode> path;

    }
}
