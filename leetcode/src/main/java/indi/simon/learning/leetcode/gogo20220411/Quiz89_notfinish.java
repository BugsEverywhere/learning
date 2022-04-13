package indi.simon.learning.leetcode.gogo20220411;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenzhuo(zhiyue)
 */
public class Quiz89_notfinish {

    public static void main(String[] args) {
        Quiz89_notfinish quiz89 = new Quiz89_notfinish();
        List<Integer> res = quiz89.grayCode(3);
        System.out.println(res);
    }

    private List<Integer> res;

    public List<Integer> grayCode(int n) {
        List<Integer> path = new ArrayList<>();
        path.add(0);
        grayCodeInternal(n, path, 1);
        return res;
    }

    private void grayCodeInternal(int n, List<Integer> path, int start) {
        if (this.res != null) {
            return;
        }

        if (path.size() >= Math.pow(2, n)) {
            this.res = path;
            return;
        }

        if (start > Math.pow(2, n) - 1) {
            //不满足要求，不继续递归，返回
            return;
        }

        int curr = start;
        //分不同情况
        if (path.size() == Math.pow(2, n) - 1) {
            //找这最后一个数
            while (curr <= Math.pow(2, n) - 1) {
                //首先他的二进制表示只能有一个开头的1，其余全是0
                boolean firstCondition = true;
                String binStr = Integer.toBinaryString(curr);
                firstCondition = curr == Math.pow(2, binStr.length() - 1);
                //第二，他必须跟前一个的二进制表示只差一位
                boolean secondCondition = true;
                int backwardAnd = curr & path.get(path.size() - 1);
                secondCondition = backwardAnd == curr || backwardAnd == path.get(path.size() - 1);
                if (firstCondition && secondCondition && !path.contains(curr)) {
                    break;
                } else {
                    curr = curr * 2;
                }
            }
        } else {
            //一般情况
            while (curr <= Math.pow(2, n) - 1) {
                //他必须跟前一个的二进制表示只差一位
                boolean condition = true;
                int backwardAnd = curr & path.get(path.size() - 1);
                condition = backwardAnd == curr || backwardAnd == path.get(path.size() - 1);
                if (condition && !path.contains(curr)) {
                    break;
                } else {
                    curr++;
                }
            }
        }
        path.add(curr);
        int nextInt = curr + 1;
        while (true) {
            if (nextInt > Math.pow(2, n) - 1) {
                //到头了，往回找
                nextInt = nextInt - 1;
                while (nextInt >= 0 && path.contains(nextInt)) {
                    nextInt--;
                }
                break;
            } else if (path.contains(nextInt)) {
                nextInt++;
            } else {
                break;
            }
        }
        grayCodeInternal(n, path, nextInt);
    }
}
