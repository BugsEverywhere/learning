package indi.simon.learning.leetcode.gogo20230814;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Chen Zhuo on 2023/8/20.
 */
public class Quiz2682 {

    public static void main(String[] args) {
        Quiz2682 quiz2682 = new Quiz2682();
        int[] res = quiz2682.circularGameLosers(5, 3);
        System.out.println(res);
    }

    public int[] circularGameLosers(int n, int k) {
        boolean[] turned = new boolean[n + 1];
        int index = 1;
        int round = 1;
        while (!turned[index]) {
            turned[index] = true;
            index += round * k;
            round++;
            //todo: 要注意这里的处理，如果index加完能整除n，也就是index%n==0时，index就应该是n
            if (index > n) {
                if(index % n == 0){
                    index = n;
                } else {
                    index = index % n;
                }
            }
        }

        List<Integer> answerList = new ArrayList<>();
        for (int i = 1; i < turned.length; i++) {
            if (!turned[i]) {
                answerList.add(i);
            }
        }

        int[] arr = new int[answerList.size()];
        for (int i = 0; i < arr.length; i++) {
            arr[i] = answerList.get(i);
        }

        return arr;
    }
}
