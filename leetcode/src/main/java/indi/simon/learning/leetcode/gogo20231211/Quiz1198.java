package indi.simon.learning.leetcode.gogo20231211;

import java.util.PriorityQueue;

/**
 * Created by Chen Zhuo on 2023/12/17.
 */
public class Quiz1198 {

    public static void main(String[] args) {
        Quiz1198 quiz1198 = new Quiz1198();
        int res = quiz1198.smallestCommonElement(new int[][]{
                {1, 2, 3}, {2, 3, 4}, {2, 3, 5}
        });
        System.out.println(res);
    }

    public int smallestCommonElement(int[][] mat) {
        PriorityQueue<Integer> queue = new PriorityQueue<>();
        //先填充第一行
        for (int i = 0; i < mat[0].length; i++) {
            queue.offer(mat[0][i]);
        }

        //再遍历后续行
        for (int i = 1; i < mat.length; i++) {
            if(queue.size() == 0){
                return -1;
            }
            PriorityQueue<Integer> backUp = new PriorityQueue<>();
            for (int j = 0; j < mat[0].length; ) {
                if (queue.size() == 0) {
                    //前一行所有公共元素已经找完了，开始到下一行
                    break;
                }
                if (mat[i][j] < queue.peek()) {
                    //该行该元素比潜在公共元素队列第一个还小，说明肯定不是公共元素，跳过
                    j++;
                } else if (mat[i][j] > queue.peek()) {
                    //该行该元素比潜在公共元素队列第一个还大，说明潜在公共队列第一个元素不是公共元素，吐出
                    queue.poll();
                } else {
                    //找到一个公共元素，加入备用队列
                    backUp.offer(queue.poll());
                    j++;
                }
            }
            queue = backUp;
        }

        if (queue.size() == 0) {
            return -1;
        }

        return queue.poll();
    }

}
