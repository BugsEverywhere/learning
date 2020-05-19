package indi.simon.learning.leetcode.gogo2020may.week18to22;

import java.util.concurrent.LinkedBlockingDeque;

public class P168_need_review {

    public static void main(String[] args) {
        String res = convertToTitle(727);
        System.out.println(res);
    }

    private static String convertToTitle(int n) {

        LinkedBlockingDeque<Character> queue = new LinkedBlockingDeque<>();
        int remain = n;
        while (remain > 0) {
            if (remain % 26 == 0) {
                queue.addFirst('Z');
                remain = (remain - 26) / 26;
            } else {
                int yu = remain % 26;
                char thisBit = (char) ('A' + yu - 1);
                remain = remain / 26;
                queue.addFirst(thisBit);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (Character singleChar : queue) {
            sb.append(singleChar);
        }
        return sb.toString();
    }

}

//todo 数字转字符串最大的坑在于，先不论是多少进制，十进制也好26进制也罢，设进制为X，在数字不能整除X的时候是一种情况，能整出X的时候是另一种情况，循环迭代的递进条件，两者也不同
// 比如十进制转字母的时候，208不能整除10，则个位数为208对10取余，得8，那么8对应的字母是H，个位就是H。然后208除以10塌方一级，得到20，这时候20可以整除10，则直接给第二位塞一个
// 10对应的字母，即J，都不需要考虑取余的情况，然后20需要减去10再除以10来塌方，进行后续循环。没什么诀窍，画一下数字和字母对应关系就知道了。
